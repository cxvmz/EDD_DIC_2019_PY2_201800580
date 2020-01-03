/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.ARBOLB;

/**
 *
 * @author Christian
 */
public class arbolB {

    nodoB raiz;
    int m;

    public arbolB(int m) {
        this.m = m;
        raiz = null;
    }

    public void insertar(int llave) {

        if (raiz == null) {
            raiz = new nodoB(m, true);
            raiz.esRaiz = true;
            raiz.insertar(llave);
        } else {

            nodoB aux = raiz;
            while (!aux.esHoja) {
                int i = 0;
                while (llave > aux.llaves[i] && i < aux.n) {
                    i++;
                }
                aux = aux.hijos[i];
            }

            aux.insertar(llave);
        }

    }

    public String graficar(nodoB aux) {
        String codigoGunido = "";
        if (aux == null) {
            return codigoGunido;
        }
        codigoGunido = "";

        int i = 0;
        String codigoG;
        codigoG = "" + aux.id;
        if (aux != null || aux != null) {

            for (int d = 0; d <= aux.n; d++) {
                if (!aux.esHoja) {
                    codigoGunido = codigoGunido + graficar(aux.hijos[d]);
                }
            }
            codigoGunido = codigoGunido + "nodo" + codigoG + "[shape = record label = \"";

            while (i < aux.n) {
                String f;
                f = String.valueOf(aux.llaves[i]);
                if (aux.n - i == 1) {
                    codigoGunido = codigoGunido + "{" + f + "}";
                } else {
                    codigoGunido = codigoGunido + "{" + f + "}|";
                }

                i++;
            }

            codigoGunido = codigoGunido + "\"]";// + char(10);

        }

        return codigoGunido;

    }

    public String hacerConexion(nodoB aux) {

        String cadena = "";
        if (aux == null || aux == null) {
            return cadena;
        }
        cadena = "";
        String CodigoG;
        CodigoG = "" + aux.id;
        if (aux != null || aux != null) {

            for (int d = 0; d <= aux.n; d++) {
                if (!aux.esHoja) {
                    cadena = cadena + "nodo" + CodigoG + "->" + hacerConexion(aux.hijos[d]) + "\n";
                }
            }
            cadena = cadena + "nodo" + CodigoG;

        }

        return cadena;

    }
    
    public String generarcadenadotarbolb() {
        
        String resultado = graficar(raiz);
        resultado = resultado + hacerConexion(raiz);
      
        
        String archivo = "";
        
  
         archivo+="digraph G { node[shape = box;] concentrate=true; graph[splines = ortho]; " +resultado + "}";

            
        System.out.println(archivo);
        return archivo;
    
    }
}
