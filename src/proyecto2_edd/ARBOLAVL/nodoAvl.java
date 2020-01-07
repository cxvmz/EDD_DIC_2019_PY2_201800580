/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.ARBOLAVL;

/**
 *
 * @author Christian
 */
public class nodoAvl {
    int dato,fe;
    nodoAvl hijoDerecho,hijoIzquierdo;
    public nodoAvl(int dato){
        this.dato=dato;
        this.fe =0;
        this.hijoDerecho=null;
        this.hijoIzquierdo=null;
    }
}
