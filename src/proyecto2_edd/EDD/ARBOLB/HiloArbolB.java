/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.ARBOLB;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import proyecto2_edd.ArbolBAutomatico;

/**
 *
 * @author Christian
 */
public class HiloArbolB extends Thread {

    int imgNum;
    JLabel mostrar;
    int inicio = 0;
    ArbolBAutomatico ab = new ArbolBAutomatico();

    public HiloArbolB(int imgNum,JLabel mostrar){
        this.imgNum = imgNum;
        this.mostrar = mostrar;
    }

    public void run() {

        while (true) {
            try {

                if (inicio != imgNum) {
                    Image imgArbol = new ImageIcon("arbol" + inicio + ".png").getImage();
                    ImageIcon imgArbol1 = new ImageIcon(imgArbol);
                    mostrar.setIcon(imgArbol1);
                    Thread.sleep(3000);
                    inicio++;
                }else{
                break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloArbolB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
