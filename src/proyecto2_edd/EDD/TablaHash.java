/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD;

/**
 *
 * @author Christian
 */

class NodoHash{
     String Nombre;
     String Apellido;
     String Carnet;
     String Password;

    public NodoHash(String Nombre, String Apellido, String Carnet, String Password) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Carnet = Carnet;
        this.Password = Password;
    }
}
public class TablaHash {
    
    private int tamanio;
    public static int[] tamanios;
    private int indiceTam;
    private int ocupados;
    private float porcentajeUtil;
    private float factorUtil;
    private Usuario [] vectorHash;
    
    public TablaHash(){
    this.tamanios = new int[] {23,29,37,43,47,53,59,67,73,79,83,89,97,103,107,113,127,137,149,157,167,179,197,211,227,239,251,263,277,293,311,997};
    this.indiceTam =0;
    this.factorUtil=0;
    this.factorUtil = 55.0f;
    this.tamanio= tamanios[indiceTam];
    this.vectorHash = new  Usuario[tamanio];
    this.porcentajeUtil = calculoDePorcentajeUtil();
}
    
    private float calculoDePorcentajeUtil(){
        return (ocupados*100)/tamanio;
    }
    
    private int  funcion1(int carnet){
        return carnet%tamanio;
    }
    
    /*private int funcion2(int carnet){
        return  ((carnet%7)+1)*;
    }*/

    private int generarCarnet(String carnet){
        String codigo="";
        int  tmp = 0;
        
        for(int i = 0; i<carnet.length();i++){
            codigo += carnet.codePointAt(i);
        }
        if(codigo.length()>9){
            return reduccion(codigo);
        }else{
            return Integer.parseInt(codigo);
        }
    }
    
    private int reduccion(String carnet){
        int tmp =0;
        while(carnet.length()>9){
            String aux="";
            for(int i=0;i<carnet.length()/2;i++){
                aux += carnet.charAt(i);
            }
            
            if(aux.length()>9){
                tmp = reduccion(aux);
                aux = "";
            }else{
                tmp = Integer.parseInt(aux);
                aux="";
            }
            
            for(int i= carnet.length()/2;i<carnet.length();i++){
                aux +=carnet.charAt(i);
            }
            
            if(aux.length()>9){
                tmp=reduccion(aux);
                aux="";
            }else{
                tmp = Integer.parseInt(aux);
                aux ="";
            }
            carnet = tmp+"";
        }
        return tmp;
    }

    private int DobleHashing(String carnet,int i){
        int tmp = generarCarnet(carnet);
        return (tmp%7+1)*i;      
    }
    
    public void insetar(String nombre,String apelldo,String carnet,String password){
        boolean insertado = false;
        if(porcentajeUtil<=55.00f){
            for(int i=0;i<tamanio;i++){
                
                
            }
            
        }
        
        
    }
}
