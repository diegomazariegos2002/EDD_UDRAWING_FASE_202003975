package app;

import java.io.*;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import estructuras.ListaEnlazada;

import javax.swing.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static boolean carga_Masiva = false;
    static boolean carga_Ventanillas = false;
    static ListaEnlazada<Cliente> lista_Clientes;
    static int num_Ventanillas = 0;

    public static void main(String[] args) {
        while (true) {
            try {
                int menu;
                String Menu = "MENU \n"
                        + "1. Parametros iniciales \n"
                        + "2. Ejecutar paso \n"
                        + "3. Estado de memoria de las estructuras \n"
                        + "4. Reportes \n"
                        + "5. acerca de Diego Mazariegos \n"
                        + "6. Salir ";
                System.out.println(Menu);
                System.out.print("Ingrese una opción para continuar: ");
                menu = Integer.valueOf(sc.nextLine());

                switch (menu) {
                    case 1:
                        boolean regresar = false;
                        while(true) {
                            String submenu;
                            System.out.println("Escogio la opción 1. Parametros iniciales");
                            System.out.println("a. Carga masiva de clientes \n"
                                    + "b. Cantidad de ventanillas \n"
                                    + "c. Regresar");
                            System.out.print("Ingrese una opción para continuar: ");
                            submenu = sc.nextLine();
                            switch (submenu) {
                                case "a":
                                    System.out.println("Escogió la opción \"a\". Carga masiva de clientes");
                                    try {
                                        lista_Clientes = new ListaEnlazada<>();
                                        String txt = leerFichero();
                                        leerJSON(txt);
                                        lista_Clientes.crearFicheroDot_ListaSimple("prueba");
                                        System.out.println("Archivo JSON cargado con éxito!!!!");
                                    } catch (Exception e) {
                                        System.out.println("Soy un error en la lectura del fichero.");
                                    }
                                    System.out.println("Presione Enter para continuar....");
                                    sc.nextLine();
                                    break;
                                case "b":
                                    System.out.println("Escogió la opción \"b\". Cantidad de ventanillas");
                                    try{
                                        System.out.println("Ingrese el número de ventanillas que desea: ");
                                        num_Ventanillas = Integer.valueOf(sc.nextLine());
                                        System.out.println("Número de ventanillas ingresado con éxito!!!");
                                    }catch (Exception e){
                                        System.out.println("Soy un error en el número de ventanillas.");
                                    }
                                    System.out.println("Presione Enter para continuar....");
                                    sc.nextLine();
                                    break;
                                case "c":
                                    System.out.println("Escogió la opción \"c\". Regresar");
                                    regresar = true;
                                    System.out.println("Presione Enter para continuar....");
                                    sc.nextLine();
                                    break;
                                default:
                                    System.out.println("Ingrese una opción válida.");
                                    sc.nextLine();
                            }
                            if(regresar == true){
                                break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Escogio la opción 2. \n");
                        if(lista_Clientes.getlength() == 0 || num_Ventanillas == 0){
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        }else{

                        }
                        break;
                    case 3:
                        System.out.println("Escogio la opción 3. \n");
                        if(lista_Clientes.getlength() == 0 || num_Ventanillas == 0){
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        }
                        else{

                        }
                        break;
                    case 4:
                        System.out.println("Escogio la opción 4. \n");
                        if(lista_Clientes.getlength() == 0 || num_Ventanillas == 0){
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        }
                        else{

                        }
                        break;
                    case 5:
                        System.out.println("Escogio la opción 5. \n");
                        if(lista_Clientes.getlength() == 0 || num_Ventanillas == 0){
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        }
                        else{

                        }
                        break;
                    case 6:
                        System.out.println("Escogio la opción 6. \n");
                        if(lista_Clientes.getlength() == 0 || num_Ventanillas == 0){
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        }
                        else{

                        }
                        break;
                    default:
                        System.out.println("Ingrese un numero valido. \n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ingrese un caracter valido. \n");
            }
        }
    }

    //Método para leer un fichero y pasar el fichero a un String.
    public static String leerFichero() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser (); // Crear un objeto para seleccionar un archivo
        String txt = "";
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile (); // Crea un objeto de archivo, que es el archivo seleccionado
            InputStream ins = new FileInputStream(file);//Crea un objeto de contenido de archivo leído
            Scanner input = new Scanner(ins); //  lee el contenido del archivo seleccionado

            while(input.hasNextLine()){
                txt += input.nextLine();
            }
            input.close (); // Cierra el archivo de entrada y libera los recursos ocupados por el archivo
            return txt;
        }else{
            System.out.println ("¡Ningún archivo seleccionado!");
            return txt;
        }
    }

    //Método para leer un JSON recibe un String y lo convierte a objetos.
    public static void leerJSON(String txt){
        JSONArray arrayJson = new JSONArray(txt); //se crea el array Json del txt
        for (int i = 0; i < arrayJson.length(); i++) {
            JSONObject clienteJson = arrayJson.getJSONObject(i); //Se selecciona solo un objeto del array

            int id = Integer.valueOf(clienteJson.getString("id_cliente"));
            String nombre = clienteJson.getString("nombre_cliente");
            int img_color = Integer.valueOf(clienteJson.getString("img_color"));
            int img_bw = Integer.valueOf(clienteJson.getString("img_bw"));

            Cliente cliente = new Cliente(id, nombre, img_color, img_bw);
            lista_Clientes.insertElement_AtEnding(cliente);
        }

    }


}
