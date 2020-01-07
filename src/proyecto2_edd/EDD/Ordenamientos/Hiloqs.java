/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.Ordenamientos;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import proyecto2_edd.EDD.ARBOLB.HiloArbolB;

/**
 *
 * @author Christian
 */
public class Hiloqs extends Thread{
    
    int imgNum;
    JLabel mostrar;
    int inicio = 0;

    public Hiloqs(int imgNum, JLabel mostrar) {
        this.imgNum = imgNum;
        this.mostrar = mostrar;
    }

    public void run() {

        while (true) {
            try {

                if (inicio != imgNum) {
                    Image imgArbol = new ImageIcon("qq" + inicio + ".png").getImage();
                    ImageIcon imgArbol1 = new ImageIcon(imgArbol);
                    mostrar.setIcon(imgArbol1);
                    Thread.sleep(1500);
                    inicio++;
                } else {
                    break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloArbolB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
