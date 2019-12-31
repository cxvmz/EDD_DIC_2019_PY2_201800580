/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Christian
 */
public class LeerJson {

    public void LeerJsonUsuario(String archivoJson) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(archivoJson));
            JSONArray jsonArray = (JSONArray) obj;
            
            for(int i=0;i<jsonArray.size();i++){
            JSONObject user = (JSONObject) jsonArray.get(i);
            String Nombre  = (String) user.get("Nombre"); 
            System.out.println("El nombre es:  " + Nombre);
            String Apellido  = (String) user.get("Apellido"); 
            System.out.println("El Apellido es:  " + Apellido);
            String Carnet  = (String) user.get("Carnet"); 
            System.out.println("El Carnet es:  " + Carnet);
            String Password  = (String) user.get("Password"); 
            System.out.println("El Password es:  " + Password + "\n\n");
            }      
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
