/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.TABLAHASH;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import proyecto2_edd.EDD.Usuario;

/**
 *
 * @author Christian
 */
public class TablaHash {

    private int tamanio;
    public static int[] tamanios;
    private int indiceTam;
    private int ocupados;
    private float porcentajeUtil;
    private float factorUtil;
    public Usuario[] vectorHash;
    String codigoGProblemas = "";
    String CGP = "";

    public TablaHash() {
        this.tamanios = new int[]{37, 43, 47, 53, 59, 67, 73, 79, 83, 89, 97, 103, 107, 113, 127, 137, 149, 157, 167, 179, 197, 211, 227, 239, 251, 263, 277, 293, 311, 997};
        this.indiceTam = 0;
        this.factorUtil = 0;
        this.factorUtil = 55.0f;
        this.tamanio = tamanios[indiceTam];
        this.vectorHash = new Usuario[tamanio];
        this.porcentajeUtil = calculoDePorcentajeUtil();
    }

    private float calculoDePorcentajeUtil() {
        return (ocupados * 100) / tamanio;
    }

    private int funcion1(String carnet) {
        int tmp = generarCarnet(carnet);
        return tmp % tamanio;
    }

    private int generarCarnet(String carnet) {
        String codigo = "";
        int tmp = 0;

        for (int i = 0; i < carnet.length(); i++) {
            codigo += carnet.codePointAt(i);
        }
        if (codigo.length() > 9) {
            return reduccion(codigo);
        } else {
            return Integer.parseInt(codigo);
        }
    }

    private int reduccion(String carnet) {
        int tmp = 0;
        while (carnet.length() > 9) {
            String aux = "";
            for (int i = 0; i < carnet.length() / 2; i++) {
                aux += carnet.charAt(i);
            }

            if (aux.length() > 9) {
                tmp = reduccion(aux);
                aux = "";
            } else {
                tmp = Integer.parseInt(aux);
                aux = "";
            }

            for (int i = carnet.length() / 2; i < carnet.length(); i++) {
                aux += carnet.charAt(i);
            }

            if (aux.length() > 9) {
                tmp = reduccion(aux);
                aux = "";
            } else {
                tmp = Integer.parseInt(aux);
                aux = "";
            }
            carnet = tmp + "";
        }
        return tmp;
    }

    private int DobleHashing(String carnet, int i) {
        return ((funcion1(carnet)) + 1) * i;
    }

    public void insertar(String nombre, String apellido, String carnet, String password) throws NoSuchAlgorithmException {
        boolean insertado = false;
        String passSha256 = toHexString(getSHA(password));
        if (password.length() >= 8) {
            if (porcentajeUtil <= 55.00f) {
                int posi = funcion1(carnet);
                if (vectorHash[posi] == null) {
                    vectorHash[posi] = new Usuario(nombre, apellido, carnet, passSha256);
                    ocupados += 1;
                    porcentajeUtil = calculoDePorcentajeUtil();
                    insertado = true;
                } else {
                    if (vectorHash[posi].getCarnet().equals(carnet)) {
                        codigoGProblemas += "<tr><td>" + nombre + "</td><td>" + carnet + "</td><td>" + password + "</td><td> Ya existe el usuario</td></tr> \n";
                        System.out.println("Ya existe el carnet: " + carnet);
                    } else {
                        for (int i = 0; i < tamanio; i++) {
                            int pos = DobleHashing(carnet, i);
                            if (pos > tamanio) {
                                pos = pos % tamanio;
                            }
                            if (vectorHash[pos] == null) {
                                vectorHash[pos] = new Usuario(nombre, apellido, carnet, passSha256);
                                ocupados += 1;
                                porcentajeUtil = calculoDePorcentajeUtil();
                                insertado = true;
                                break;
                            } else {
                                if (vectorHash[pos].getCarnet().equals(carnet)) {
                                    codigoGProblemas += "<tr><td>" + nombre + "</td><td>" + carnet + "</td><td>" + password + "</td><td>Ya existe el usuario</td></tr> \n";
                                    System.out.println("Ya existe el carnet: " + carnet);
                                    break;
                                }
                            }
                        }
                    }
                }

                /**/
                if (insertado == true) {
                    System.out.println("Se inserto correctamente: " + nombre + "con carnet " + carnet);
                } else {
                    System.out.println("No se inserto el dato: " + nombre + "Con carnet: " + carnet);
                }
            } else {
                System.out.println(" hacer rehashing  PU->" + porcentajeUtil);
                reHash();
                insertar(nombre, apellido, carnet, password);
            }
        } else {
            codigoGProblemas += "<tr><td>" + nombre + "</td><td>" + carnet + "</td><td>" + password + "</td><td>Problema con la contraseña</td></tr> \n";
            //System.out.println("La contraseña es menos a 8 caracteres para el carnet: " + carnet);
        }

    }

    public void reHash() throws NoSuchAlgorithmException {
        Usuario[] temp = vectorHash;
        int tamanioTemp = tamanio;
        if (indiceTam < tamanios.length) {
            indiceTam += 1;
            if (indiceTam == tamanios.length - 1) {
                System.out.println("Se alcanzo el tamaño maximo");
            }
        }
        tamanio = tamanios[indiceTam];
        vectorHash = new Usuario[tamanio];
        ocupados = 0;
        porcentajeUtil = calculoDePorcentajeUtil();
        for (int i = 0; i < tamanioTemp; i++) {
            if (temp[i] != null) {
                insertar(temp[i].getNombre(), temp[i].getApellido(), temp[i].getCarnet(), temp[i].getPassword());
            }
        }
        System.out.println("Rehashing realizado correctamente");
    }

    public boolean extraerUser(String carnet, String password) {
        boolean encontrado = false;
        if (password.length() >= 8) {
            for (int i = 0; i < tamanio - 1; i++) {
                if (vectorHash[i] != null) {
                    if (vectorHash[i].getCarnet().equals(carnet) && vectorHash[i].getPassword().equals(password)) {
                        encontrado = true;
                        break;
                    } else {
                        encontrado = false;
                    }
                }
            }
        } else {
            encontrado = false;
        }
        return encontrado;
    }

    public void LeerJsonUsuario(String archivoJson) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(archivoJson));
            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject user = (JSONObject) jsonArray.get(i);
                String Nombre = (String) user.get("Nombre");
                //System.out.println("El nombre es:  " + Nombre);
                String Apellido = (String) user.get("Apellido");
                //System.out.println("El Apellido es:  " + Apellido);
                String C = (String) user.get("Carnet");
                String Carnet = C.replaceAll("-", "");
                //System.out.println("El Carnet es:  " + Carnet);
                String Password = (String) user.get("Password");
                String passSha256 = toHexString(getSHA(Password));
                //System.out.println("El Password es:  " + passSha256 + "\n\n");
                insertar(Nombre, Apellido, Carnet, Password);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String dotProblema() {
        CGP += "digraph Z {\n"
                + "tble [\n"
                + "shape=plaintext \n"
                + "label=<\n"
                + "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
                + "<tr><td>Posicion</td><td> Nombre Completo </td><td> Carnet</td><td> Password</td></tr>";

        CGP += codigoGProblemas;
        CGP += "</table> \n"
                + ">]\n"
                + "}";
        return CGP;
    }

    public String dotTabla() {
        String codigoGTabla = "digraph Z {\n"
                + "tble [\n"
                + "shape=plaintext \n"
                + "label=<\n"
                + "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
                + "<tr><td>Posicion</td><td> Nombre Completo </td><td> Carnet</td><td> Password</td></tr>";
        for (int i = 0; i < vectorHash.length; i++) {
            if (vectorHash[i] != null) {
                codigoGTabla += "<tr><td>" + i + "</td><td>" + vectorHash[i].getNombre() + " " + vectorHash[i].getApellido() + "</td><td>" + vectorHash[i].getCarnet() + "</td><td>" + vectorHash[i].getPassword() + "</td></tr> \n";
            } else {
                codigoGTabla += "<tr><td>" + i + "</td><td> </td><td> </td><td> </td></tr> \n";
            }
        }
        codigoGTabla += "</table> \n"
                + ">];\n"
                + "}";
        return codigoGTabla;
    }

    public void graficarProblemas() {
        String problemas = dotProblema();
        FileWriter file = null;
        try {
            Thread.sleep(100);
            file = new FileWriter("problemasDot.dot");
            file.write(problemas);
            Thread.sleep(100);
            file.close();
            Thread.sleep(100);
            String F = "dot -Tpng problemasDot.dot -o problemasDot.png";
            Thread.sleep(100);
            Process rt1 = Runtime.getRuntime().exec(F);
            Thread.sleep(100);
            rt1 = Runtime.getRuntime().exec(F);;
            Thread.sleep(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetProblemas() {
        CGP = "";
    }

    public void graficarHash() {
        String hash = dotTabla();
        FileWriter file = null;
        try {
            file = new FileWriter("tablaDot.dot");
            file.write(hash);
            file.close();
            String F = "dot -Tpng tablaDot.dot -o tablaDot.png";
            Process rt = Runtime.getRuntime().exec(F);
            rt = Runtime.getRuntime().exec(F);;
            Thread.sleep(500);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //SHA256
    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros 
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
}
