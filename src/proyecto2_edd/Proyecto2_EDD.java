/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.FileInputStream;
import proyecto2_edd.EDD.LeerJson;

/**
 *
 * @author Christian
 */
public class Proyecto2_EDD {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Inicio home = new Inicio();
        //home.setVisible(true);
        
        
        LeerJson json = new LeerJson();
        json.LeerJsonUsuario("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Usuarios.json");
        
        
    }
    
    
}

