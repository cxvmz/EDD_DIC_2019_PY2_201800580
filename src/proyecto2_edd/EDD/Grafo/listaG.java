/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.Grafo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Christian
 */
public class listaG {

    nodoG nodog[];
    public int imgNum = 0;
    public int imgNum1 =0;

    public listaG() {
        nodog = null;
    }

    public void LeerJsonArray(String archivoJson) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(archivoJson));
            JSONObject jsonobj = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonobj.get("Graph");
            nodog = new nodoG[array.size()];
            for (int i = 0; i < array.size(); i++) {
                JSONObject user = (JSONObject) array.get(i);
                String nodo = (String) user.get("Node").toString();
                String sig[];
                JSONArray array1 = (JSONArray) user.get("Adjacency");
                sig = new String[array1.size()];
                for (int j = 0; j < array1.size(); j++) {
                    JSONObject f = (JSONObject) array1.get(j);
                    String nodo1 = (String) f.get("Node").toString();
                    sig[j] = nodo1;
                }
                nodoG n = new nodoG(nodo, sig);
                nodog[i] = n;
                pngTA(txtTablaAd(tablaAd()));
                pngGND(grafoND());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrar() {
        for (int i = 0; i < nodog.length; i++) {
            System.out.print("El dato es: " + nodog[i].dato + " Sig:");
            for (int j = 0; j < nodog[i].sig.length; j++) {
                System.out.print(nodog[i].sig[j]);
            }
            System.out.println("\n\n");
        }
    }

    public String tablaAd() {
        String f = "";

        for (int i = 0; i < nodog.length; i++) {
            if (nodog[i] != null) {
                f += "<tr><td>" + nodog[i].dato + "</td><td>" + nodog[i].sig[0] + " ";
                for (int j = 1; j < nodog[i].sig.length; j++) {
                    f += ", " + nodog[i].sig[j];
                }
                f += "</td></tr>\n";
            } else {
                f += "<tr><td> </td><td>  ";
                f += "</td></tr>\n";

            }

        }
        return f;
    }

    public String txtTablaAd(String f) {
        String f2 = "digraph Z {\n"
                + "tble [\n"
                + "shape=plaintext \n"
                + "label=<\n"
                + "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
                + "<tr><td>Nodo</td><td> Siguientes </td></tr>";
        f2 += f;
        f2 += "</table> \n"
                + ">];\n"
                + "}";
        System.out.println(f2);
        return f2;
    }

    public void pngTA(String arbol) {
        FileWriter file = null;
        try {
            file = new FileWriter("TA" + imgNum + ".dot");
            file.write(arbol);
            file.close();
            String F = "dot -Tpng TA" + imgNum + ".dot -o TA" + imgNum + ".png";
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

    public String grafoND() {
        String f = "graph f {";
        for (int i = 0; i < nodog.length; i++) {
            if (nodog[i] != null) {
                for (int j = 0; j < nodog[i].sig.length; j++) {
                    f += nodog[i].dato + " -- " + nodog[i].sig[j] + ";\n";
                }
            }else{
            }
        }
        f += "}";
        System.out.println(f);
        return f;
    }

    public void pngGND(String arbol) {
        FileWriter file = null;
        try {
            file = new FileWriter("GND" + imgNum1 + ".dot");
            file.write(arbol);
            file.close();
            String F = "dot -Tpng GND" + imgNum1 + ".dot -o GND" + imgNum1 + ".png";
            Process rt = Runtime.getRuntime().exec(F);
            rt = Runtime.getRuntime().exec(F);;
            Thread.sleep(1000);
            imgNum1++;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
