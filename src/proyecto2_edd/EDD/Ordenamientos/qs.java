/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.Ordenamientos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Christian
 */
public class qs {

    public int arreglo[];
    public int imgNum = 0;
    int primero, ultimo;

    public void LeerJsonArray(String archivoJson) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(archivoJson));
            JSONObject jsonobj = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonobj.get("Array");
            arreglo = new int[array.size()];
            for (int i = 0; i < array.size(); i++) {
                JSONObject user = (JSONObject) array.get(i);
                String num = (String) user.get("num").toString();
                arreglo[i] = Integer.parseInt(num);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ordenar(int[] array, int primero, int ultimo) {
        int i = primero;
        int j = ultimo;
        int aux;
        int pivote = array[(primero + ultimo) / 2];

        do {
            while (array[i] < pivote) {
                i++;
            }
            while (array[j] > pivote) {
                j--;
            }
            if (i <= j) {
                aux = array[i];

                array[i] = array[j];

                array[j] = aux;

                i++;
                j--;

            }
        } while (i <= j);
        if (primero < j) {
            ordenar(array, primero, j);
        }
        if (i < ultimo) {
            ordenar(array, i, ultimo);
        }
        pngbb(codigoTxt());

    }

    public String codigoTxt() {
        String codigoG = "";
        codigoG += "digraph html {\n"
                + "abc [shape=none, margin=0, label=<\n"
                + "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"4\">\n"
                + "<TR>";
        for (int i = 0; i < arreglo.length ; i++) {
            codigoG += "<TD BGCOLOR=\"lightgrey\" >" + arreglo[i] + "</TD>";
        }
        codigoG += "</TR>\n"
                + "</TABLE>\n"
                + ">];\n"
                + "}";

        return codigoG;
    }

    public void pngbb(String arbol) {
        FileWriter file = null;
        try {
            file = new FileWriter("qq" + imgNum + ".dot");
            file.write(arbol);
            file.close();
            String F = "dot -Tpng qq" + imgNum + ".dot -o qq" + imgNum + ".png";
            Process rt = Runtime.getRuntime().exec(F);
            rt = Runtime.getRuntime().exec(F);;
            Thread.sleep(250);
            imgNum++;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
