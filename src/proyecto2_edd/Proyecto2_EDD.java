/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.FileInputStream;
import proyecto2_edd.EDD.ARBOLB.arbolB;
import proyecto2_edd.EDD.Burbuja;
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
        Inicio home = new Inicio();
        //home.setVisible(true);

        //LeerJson json = new LeerJson();
        //json.LeerJsonUsuario("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Usuarios.json");
        //Burbuja bb = new Burbuja();
        //bb.LeerJsonArray("C:\\Users\\Christian\\Desktop\\Archivos de Entrada\\Arreglos.json");
        //bb.mostrarArray();
        //bb.ordenamientoBurbuja();

        arbolB nuevo = new arbolB(5);
        nuevo.insertar(2);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(22);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(256);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(2165);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(21);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(2132);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(212);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(221);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(225);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(266);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(244);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(244);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(223);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(215);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(246);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(265);
        nuevo.generarcadenadotarbolb();
        nuevo.insertar(3);
        nuevo.generarcadenadotarbolb();
    }

}
