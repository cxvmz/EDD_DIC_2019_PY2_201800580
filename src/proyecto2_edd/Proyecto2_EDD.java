/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.FileInputStream;
import proyecto2_edd.EDD.ARBOLB.arbolB;
import proyecto2_edd.EDD.Burbuja;
import proyecto2_edd.EDD.TablaHash;

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
        Inicio home = new Inicio();
        //home.setVisible(true);

        TablaHash th = new TablaHash();

        //th.LeerJsonUsuario("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Usuarios.json");
        th.LeerJsonUsuario("C:\\Users\\Christian\\Downloads\\Usuarios.json");

         //Burbuja bb = new Burbuja();
                //bb.LeerJsonArray("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Arreglos.json");
                //bb.mostrarArray();
                //bb.ordenamientoBurbuja();
                //arbolB nuevo = new arbolB(5);
                //nuevo.LeerJsonArbol("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Arbols.json", nuevo);
    }

}
