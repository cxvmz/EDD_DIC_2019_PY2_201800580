/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.FileInputStream;
import proyecto2_edd.ARBOLAVL.arbolAvl;
import proyecto2_edd.EDD.ARBOLB.arbolB;
import proyecto2_edd.EDD.Burbuja;
import proyecto2_edd.EDD.TABLAHASH.TablaHash;

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
        home.setVisible(true);
        
        //Admin ad = new Admin();
        //ad.setVisible(true);


        //th.LeerJsonUsuario("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Usuarios.json");
        //th.LeerJsonUsuario("C:\\Users\\Christian\\Downloads\\Usuarios.json");
        //Burbuja bb = new Burbuja();
        //bb.LeerJsonArray("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Arreglos.json");
        //bb.mostrarArray();
        //bb.ordenamientoBurbuja();
        //arbolB nuevo = new arbolB(5);
        //nuevo.LeerJsonArbol("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Arbols.json", nuevo);
        
        arbolAvl avl= new arbolAvl();
        avl.LeerJsonArbol("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Arbols.json");
        System.out.println("in");
        avl.inOrder(avl.raiz);
        System.out.println("\n post");
        avl.postOrder(avl.raiz);
        System.out.println("\n pre");
        avl.preOrder(avl.raiz);
    }

}
