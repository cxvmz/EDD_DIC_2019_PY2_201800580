/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.ARBOLAVL;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import proyecto2_edd.EDD.ARBOLB.arbolB;

/**
 *
 * @author Christian
 */
public class arbolAvl {

    public nodoAvl raiz;

    public arbolAvl() {
        this.raiz = null;
    }

    public nodoAvl buscar(int dato, nodoAvl raiz) {
        if (raiz == null) {
            return null;
        } else if (raiz.dato == dato) {
            return raiz;
        } else if (raiz.dato < dato) {
            return buscar(dato, raiz.hijoDerecho);
        } else {
            return buscar(dato, raiz.hijoIzquierdo);

        }
    }

    public int ObtenerFE(nodoAvl raiz) {
        if (raiz == null) {
            return -1;
        } else {
            return raiz.fe;
        }
    }

    public nodoAvl rotacionSIzquierda(nodoAvl raiz) {
        nodoAvl aux = raiz.hijoIzquierdo;
        raiz.hijoIzquierdo = aux.hijoDerecho;
        aux.hijoDerecho = raiz;
        raiz.fe = Math.max(ObtenerFE(raiz.hijoIzquierdo), ObtenerFE(raiz.hijoDerecho)) + 1;
        aux.fe = Math.max(ObtenerFE(aux.hijoIzquierdo), ObtenerFE(aux.hijoDerecho)) + 1;
        return aux;
    }

    public nodoAvl rotacionSDerecha(nodoAvl raiz) {
        nodoAvl aux = raiz.hijoDerecho;
        raiz.hijoDerecho = aux.hijoIzquierdo;
        aux.hijoIzquierdo = raiz;
        raiz.fe = Math.max(ObtenerFE(raiz.hijoIzquierdo), ObtenerFE(raiz.hijoDerecho)) + 1;
        aux.fe = Math.max(ObtenerFE(aux.hijoIzquierdo), ObtenerFE(aux.hijoDerecho)) + 1;
        return aux;
    }

    //R D D
    public nodoAvl rotacionDIzquierda(nodoAvl raiz) {
        nodoAvl aux;
        raiz.hijoIzquierdo = rotacionSDerecha(raiz.hijoIzquierdo);
        aux = rotacionSIzquierda(raiz);
        return aux;
    }

    //RDI
    public nodoAvl rotacionDDerecha(nodoAvl raiz) {
        nodoAvl aux;
        raiz.hijoDerecho = rotacionSIzquierda(raiz.hijoDerecho);
        aux = rotacionSDerecha(raiz);
        return aux;
    }

    public nodoAvl insertarAvl(nodoAvl nuevo, nodoAvl subAr) {
        nodoAvl nuevopadre = subAr;
        if (nuevo.dato < subAr.dato) {
            if (subAr.hijoIzquierdo == null) {
                subAr.hijoIzquierdo = nuevo;
            } else {
                subAr.hijoIzquierdo = insertarAvl(nuevo, subAr.hijoIzquierdo);
                if (ObtenerFE(subAr.hijoIzquierdo) - ObtenerFE(subAr.hijoDerecho) == 2) {
                    if (nuevo.dato < subAr.hijoIzquierdo.dato) {
                        nuevopadre = rotacionSIzquierda(subAr);
                    } else {
                        nuevopadre = rotacionDIzquierda(subAr);
                    }
                }
            }
        } else if (nuevo.dato > subAr.dato) {
            if (subAr.hijoDerecho == null) {
                subAr.hijoDerecho = nuevo;
            } else {
                subAr.hijoDerecho = insertarAvl(nuevo, subAr.hijoDerecho);
                if (ObtenerFE(subAr.hijoDerecho) - ObtenerFE(subAr.hijoIzquierdo) == 2) {
                    if (nuevo.dato > subAr.hijoDerecho.dato) {
                        nuevopadre = rotacionSDerecha(subAr);
                    } else {
                        nuevopadre = rotacionDDerecha(subAr);
                    }
                }
            }
        } else {
            System.out.println("Nodo Duplicado");
        }

        if ((subAr.hijoIzquierdo == null) && (subAr.hijoDerecho != null)) {
            subAr.fe = subAr.hijoDerecho.fe + 1;
        } else if ((subAr.hijoDerecho == null) && (subAr.hijoIzquierdo != null)) {
            subAr.fe = subAr.hijoIzquierdo.dato + 1;
        } else {
            subAr.fe = Math.max(ObtenerFE(subAr.hijoIzquierdo), ObtenerFE(subAr.hijoIzquierdo)) + 1;
        }
        return nuevopadre;
    }

    public void insertar(int dato) {
        nodoAvl nuevo = new nodoAvl(dato);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAvl(nuevo, raiz);
        }
    }

    public void inOrder(nodoAvl recorrido) {
        if (recorrido != null) {
            inOrder(recorrido.hijoIzquierdo);
            System.out.print(recorrido.dato + ",");
            inOrder(recorrido.hijoDerecho);
        }
    }

    public void preOrder(nodoAvl recorrido) {
        if (recorrido != null) {
            System.out.print(recorrido.dato + ",");
            preOrder(recorrido.hijoIzquierdo);
            preOrder(recorrido.hijoDerecho);
        }
    }

    public void postOrder(nodoAvl recorrido) {
        if (recorrido != null) {
            preOrder(recorrido.hijoIzquierdo);
            preOrder(recorrido.hijoDerecho);
            System.out.print(recorrido.dato + ",");
        }
    }

    public void LeerJsonArbol(String archivoJson) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(archivoJson));
            JSONObject jsonobj = (JSONObject) obj;
            JSONArray input = (JSONArray) jsonobj.get("Input");
            for (int i = 0; i < input.size(); i++) {
                JSONObject user = (JSONObject) input.get(i);
                String num = (String) user.get("num").toString();
                insertar(Integer.parseInt(num));
                //Thread.sleep(5000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            //} catch (InterruptedException e) {
            // e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
