/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.ARBOLAVL;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public int imgNum=0;

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
            return -  1;
        } else {
            return raiz.fe;
        }
    }

    public nodoAvl rotacionIzquierda(nodoAvl r) {
        nodoAvl aux = r.hijoIzquierdo;
        r.hijoIzquierdo = aux.hijoDerecho;
        aux.hijoDerecho = r;
        r.fe = Math.max(ObtenerFE(r.hijoIzquierdo), ObtenerFE(r.hijoDerecho)) + 1;
        aux.fe = Math.max(ObtenerFE(aux.hijoIzquierdo), ObtenerFE(aux.hijoDerecho)) + 1;
        return aux;
    }

    public nodoAvl rotacionDerecha(nodoAvl r) {
        nodoAvl aux = r.hijoDerecho;
        r.hijoDerecho = aux.hijoIzquierdo;
        aux.hijoIzquierdo = r;
        r.fe = Math.max(ObtenerFE(r.hijoIzquierdo), ObtenerFE(r.hijoDerecho)) + 1;
        aux.fe = Math.max(ObtenerFE(aux.hijoIzquierdo), ObtenerFE(aux.hijoDerecho)) + 1;
        return aux;
    }

    //R D D
    public nodoAvl rotacionDIzquierda(nodoAvl r) {
        nodoAvl aux;
        r.hijoIzquierdo = rotacionDerecha(r.hijoIzquierdo);
        aux = rotacionIzquierda(r);
        return aux;
    }

    //RDI
    public nodoAvl rotacionDDerecha(nodoAvl r) {
        nodoAvl aux;
        r.hijoDerecho = rotacionIzquierda(r.hijoDerecho);
        aux = rotacionDerecha(r);
        return aux;
    }

    public nodoAvl insertarAvl(nodoAvl nuevo, nodoAvl subAr) {
        nodoAvl nuevopadre = subAr;
        if (nuevo.dato < subAr.dato) {
            if (subAr.hijoIzquierdo == null) {
                subAr.hijoIzquierdo = nuevo;
            } else {
                subAr.hijoIzquierdo = insertarAvl(nuevo, subAr.hijoIzquierdo);
                if ((ObtenerFE(subAr.hijoIzquierdo)) - (ObtenerFE(subAr.hijoDerecho)) == 2) {
                    if (nuevo.dato < subAr.hijoIzquierdo.dato) {
                        nuevopadre = rotacionIzquierda(subAr);
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
                if ((ObtenerFE(subAr.hijoDerecho)) - (ObtenerFE(subAr.hijoIzquierdo)) == 2) {
                    if (nuevo.dato > subAr.hijoDerecho.dato) {
                        nuevopadre = rotacionDerecha(subAr);
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
            subAr.fe = subAr.hijoIzquierdo.fe + 1;
        } else {
            subAr.fe = Math.max(ObtenerFE(subAr.hijoDerecho), ObtenerFE(subAr.hijoIzquierdo)) + 1;
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
                graficar(raiz);
                pngArbol(graficar(raiz));
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

    public String graficar(nodoAvl r) {
        String codigoGAvl = "";
        codigoGAvl += "digraph G { node[shape = box;] concentrate=true; graph[splines = ortho]; ";
        codigoGAvl += graficarNodo(r);
        codigoGAvl += unirNodos(r);

        codigoGAvl += "}";
        System.out.println(codigoGAvl);
        return codigoGAvl;

    }

    public String graficarNodo(nodoAvl r) {
        String codigoNodo = "";
        if (r != null) {
            codigoNodo += "nodo" + r.dato + "[shape = record label = \"{dato: " + r.dato +"  Altura: "+r.fe + "}\"]nodo" + r.dato + "\n";
            codigoNodo += graficarNodo(r.hijoIzquierdo);
            codigoNodo += graficarNodo(r.hijoDerecho);
        }
        return codigoNodo;
    }

    public String unirNodos(nodoAvl r) {
        String codigoNodo = "";
        if (r != null) {
            if (r.hijoDerecho != null) {
                codigoNodo += "nodo" + r.dato + "->nodo" + r.hijoDerecho.dato + "\n";
            }
            if (r.hijoIzquierdo != null) {
                codigoNodo += "nodo" + r.dato + "->nodo" + r.hijoIzquierdo.dato + "\n";
            }
            codigoNodo += unirNodos(r.hijoIzquierdo);
            codigoNodo += unirNodos(r.hijoDerecho);

        }
        return codigoNodo;
    }
    
    public void pngArbol(String arbol) {
        FileWriter file = null;
        try {
            file = new FileWriter("avl" + imgNum + ".dot");
            file.write(arbol);
            file.close();
            String F = "dot -Tpng avl" + imgNum + ".dot -o avl" + imgNum + ".png";
            Process rt = Runtime.getRuntime().exec(F);
            rt = Runtime.getRuntime().exec(F);;
            Thread.sleep(1000);
            imgNum++;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
