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
public class Insercion {

    public int arreglo[], pos, aux;
    public int imgNum = 0;

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

    public void ordenamiento() {
        for (int i = 0; i < arreglo.length; i++) {
            pos = i;
            aux = arreglo[i];
            while (pos > 0 && (arreglo[pos - 1] > aux)) {
                arreglo[pos] = arreglo[pos - 1];
                pos--;
                pngbb(codigoTxt());

            }
            arreglo[pos] = aux;
            pngbb(codigoTxt());

        }
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
            file = new FileWriter("in" + imgNum + ".dot");
            file.write(arbol);
            file.close();
            String F = "dot -Tpng in" + imgNum + ".dot -o in" + imgNum + ".png";
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
