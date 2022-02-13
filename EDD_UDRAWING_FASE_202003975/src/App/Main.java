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
    static ListaEnlazada<Cliente> cola_Clientes_Recepcion;
    static ListaEnlazada<Ventanilla> lista_ventanillas;
    static ListaEnlazada<Cliente> lista_Clientes_Espera;
    static ListaEnlazada<Cliente>  lista_Clientes_Atendidos;
    static ListaEnlazada<Imagen> cola_Imagenes_Color;
    static ListaEnlazada<Imagen> cola_Imagenes_Bw;

    static int num_Ventanillas = 0;

    public static void main(String[] args) {
        while (true) {
            try {
                int menu;
                String Menu = """
                        MENU\s
                        1. Parametros iniciales\s
                        2. Ejecutar paso\s
                        3. Estado de memoria de las estructuras\s
                        4. Reportes\s
                        5. acerca de Diego Mazariegos\s
                        6. Salir\s""";
                System.out.println(Menu);
                System.out.print("Ingrese una opción para continuar: ");
                menu = Integer.valueOf(sc.nextLine());

                switch (menu) {
                    case 1 -> {
                        boolean regresar = false;
                        while (true) {
                            String submenu;
                            System.out.println("Escogio la opción 1. Parametros iniciales");
                            System.out.println("""
                                    a. Carga masiva de clientes\s
                                    b. Cantidad de ventanillas\s
                                    c. Regresar""");
                            System.out.print("Ingrese una opción para continuar: ");
                            submenu = sc.nextLine();
                            switch (submenu) {
                                case "a" -> {
                                    reiniciarPrograma();
                                    System.out.println("Escogió la opción \"a\". Carga masiva de clientes");
                                    try {
                                        cola_Clientes_Recepcion = new ListaEnlazada<>();
                                        String txt = leerFichero();
                                        leerJSON(txt);
                                        cola_Clientes_Recepcion.crearFicheroDot_ListaSimple("prueba");
                                        System.out.println("Archivo JSON cargado con éxito!!!!");
                                        carga_Masiva = true;
                                    } catch (Exception e) {
                                        System.out.println("Soy un error en la lectura del fichero.");
                                    }
                                    System.out.println("Presione Enter para continuar....");
                                    sc.nextLine();
                                }
                                case "b" -> {
                                    reiniciarPrograma();
                                    System.out.println("Escogió la opción \"b\". Cantidad de ventanillas");
                                    try {
                                        System.out.println("Ingrese el número de ventanillas que desea: ");
                                        num_Ventanillas = Integer.valueOf(sc.nextLine());
                                        System.out.println("Número de ventanillas ingresado con éxito!!!");
                                        carga_Ventanillas = true;
                                    } catch (Exception e) {
                                        System.out.println("Soy un error en el número de ventanillas.");
                                    }
                                    System.out.println("Presione Enter para continuar....");
                                    sc.nextLine();
                                }
                                case "c" -> {
                                    System.out.println("Escogió la opción \"c\". Regresar");
                                    regresar = true;
                                    System.out.println("Presione Enter para continuar....");
                                    sc.nextLine();
                                }
                                default -> {
                                    System.out.println("Ingrese una opción válida.");
                                    sc.nextLine();
                                }
                            }
                            if (regresar) {
                                break;
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("Escogio la opción 2. Ejecutar paso\n");
                        if (!carga_Masiva || !carga_Ventanillas) {
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        } else {

                        }
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    case 3 -> {
                        System.out.println("Escogio la opción 3. Estado en memoria de las estructuras\n");
                        if (!carga_Masiva || !carga_Ventanillas) {
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        } else {
                            try {
                                crearFicheroDot_ListaSimple("Estado_de_las_estructuras");
                                System.out.println("Se creo el archivo \"Estado_de_las_estructuras\" con éxito!!! ");
                            } catch (Exception e) {
                                System.out.println("Error al imprimir los datos!!! Intente Otra vez :(");
                            }
                        }
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    case 4 -> {
                        System.out.println("Escogio la opción 4. \n");
                        if (!carga_Masiva || !carga_Ventanillas) {
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        } else {

                        }
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    case 5 -> {
                        System.out.println("Escogio la opción 5. \n");
                        if (!carga_Masiva || !carga_Ventanillas) {
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        } else {

                        }
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    case 6 -> {
                        System.out.println("Escogio la opción 6. \n");
                        if (!carga_Masiva || !carga_Ventanillas) {
                            System.out.println("Para seleccionar esta opción debe cumplir con los parametros iniciales.");
                        } else {

                        }
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    default -> System.out.println("Ingrese un numero valido. \n");
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

            int id = Integer.parseInt(clienteJson.getString("id_cliente"));
            String nombre = clienteJson.getString("nombre_cliente");
            int img_color = Integer.parseInt(clienteJson.getString("img_color"));
            int img_bw = Integer.parseInt(clienteJson.getString("img_bw"));

            Cliente cliente = new Cliente(id, nombre, img_color, img_bw);
            cola_Clientes_Recepcion.insertElement_AtEnding(cliente);
        }

    }

    public static void reiniciarPrograma(){
        cola_Clientes_Recepcion = new ListaEnlazada<>();
        lista_ventanillas = new ListaEnlazada<>();
        lista_Clientes_Espera = new ListaEnlazada<>();
        lista_Clientes_Atendidos = new ListaEnlazada<>();
        cola_Imagenes_Bw = new ListaEnlazada<>();
        cola_Imagenes_Color = new ListaEnlazada<>();
    }

    //--------------------------------Métodos para imprimir en un archivo .dot (Graphviz)--------------------------------
    //Nota: Impresión por secciones.
    public static void crearFicheroDot_ListaSimple(String nombreFichero){
        //Parte del String o texto que va a llevar el fichero
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();
        String nombresNodos = "";
        String conexiones = "";
        String rankSame = "";
        dot.append("digraph G { \n");
        // Sección de LISTA CLIENTES ATENDIDOS
        dot.append("subgraph cluster_Clientes_Atendidos{ \n");
        dot.append("label=\"LISTA CLIENTES ATENDIDOS\"; \n");
        dot.append("bgcolor=\"darkseagreen1\"; \n");
        dot.append("Start5 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("node[shape = box];\n");

        ListaEnlazada.Nodo actual = lista_Clientes_Atendidos.getCabezaLista();
        while(actual != null){
            nombresNodos += "Nodo"+ actual.hashCode() + "[Label="+((Cliente)actual.getValor()).getNombre()+"];\n";
            if(actual == lista_Clientes_Atendidos.getCabezaLista()){
                conexiones += String.format("Start5 -> Nodo%d\n", actual.hashCode());
            }
            if(actual.siguiente != null){
                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}"); //FIN LISTA CLIENTES ATENDIDOS

        nombresNodos = "";
        conexiones = "";
        //Sección de LISTA DE CLIENTES EN ESPERA
        dot.append("subgraph cluster_clientes_espera{ \n");
        dot.append("label=\"LISTA DE CLIENTES EN ESPERA\"; \n");
        dot.append("bgcolor=\"mintcream\"; \n");
        dot.append("Start4 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("node[shape = box];\n");

        actual = lista_Clientes_Espera.getCabezaLista();
        while(actual != null){
            nombresNodos += "Nodo"+ actual.hashCode() + "[Label="+((Cliente)actual.getValor()).getNombre()+"];\n";
            if(actual == lista_Clientes_Espera.getCabezaLista()){
                conexiones += String.format("Start4 -> Nodo%d \n", actual.hashCode());
            }
            if(actual.siguiente != null){
                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}"); //FIN LISTA CLIENTES EN ESPERA

        nombresNodos = "";
        conexiones = "";
        //Sección de COLA EN IMPRESORAS
        dot.append("subgraph cluster_impresoras{ \n");
        dot.append("label=\"COLA IMPRESORAS\";\n");
        dot.append("bgcolor=\"mintcream\"; \n");
        dot.append("Start31 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("Start32 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("node[shape = box];\n");

        actual = cola_Imagenes_Color.getCabezaLista();
        while(actual != null){
            Imagen imagenActual = (Imagen) actual.getValor();
            nombresNodos += "Nodo"+ actual.hashCode() + "[Label="+imagenActual.getTipo()+"];\n";
            if(actual == cola_Imagenes_Color.getCabezaLista()){
                conexiones += String.format("Start31 -> Nodo%d\n", actual.hashCode());
            }
            if(actual.siguiente != null){
                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        actual = cola_Imagenes_Bw.getCabezaLista();
        while(actual != null){
            Imagen imagenActual = (Imagen) actual.getValor();
            nombresNodos += "Nodo"+ actual.hashCode() + "[Label="+imagenActual.getTipo()+"];\n";
            if(actual == cola_Imagenes_Bw.getCabezaLista()){
                conexiones += String.format("Start31 -> Nodo%d\n", actual.hashCode());
            }
            if(actual.siguiente != null){
                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}"); //FIN COLA IMPRESORAS

        //Sección de VENTANILLAS
        dot.append("subgraph cluster_ventanillas{ \n");
        dot.append("label=\"LISTA VENTANILLA\";\n");
        dot.append("bgcolor=\"mintcream\";\n");
        dot.append("Start2 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("node[shape = box];\n");

        actual = lista_ventanillas.getCabezaLista();
        while(actual != null){
            nombresNodos += "Nodo"+ actual.hashCode() + "[Label="+((Ventanilla)actual.getValor()).getId_Ventanilla()+"];\n";
            if(actual == lista_ventanillas.getCabezaLista()){
                conexiones += String.format("Start4 -> Nodo%d:\n", actual.hashCode());
            }
            if(actual.siguiente != null){
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(), actual.siguiente.hashCode());
            }
            //Esta parte de aquí es para poder graficar las pilas de imagenes de cada ventanilla.
            //Aquí se obtiene la cola porque en un pila el último que se ingresa es el primero en salir.
            ListaEnlazada.Nodo actual2 = ((Ventanilla)lista_ventanillas.getCabezaLista().getValor()).getPila_Imagenes().getColaLista();
            while(actual2 != null){
                nombresNodos += "Nodo"+ actual2.hashCode() + "[Label="+((Imagen)actual2.getValor()).getTipo()+"];\n";
                if(actual2 == (lista_ventanillas.getCabezaLista().getValor()).getPila_Imagenes().getColaLista()){
                    conexiones += String.format("Nodo%d -> Nodo%d [constraint = false];\n", actual.hashCode(), actual2.hashCode());
                }
                if(actual2.siguiente != null){
                    conexiones += String.format("Nodo%d -> Nodo%d [constraint = false];\n", actual2.hashCode(), actual2.siguiente.hashCode());
                }
                rankSame += String.format("{rank = same; Nodo%d; Nodo%d};\n", actual.hashCode(), actual2.hashCode());
                actual2 = actual2.siguiente;
            }

            actual = actual.siguiente;
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(rankSame); // el atributo rank=same de Graphviz lo utilizo para poder apilar de forma correcta las ventanillas con su pila de imagenes.
        dot.append("}"); //FIN LISTA CLIENTES EN ESPERA

        //Sección de LISTA DE CLIENTES EN ESPERA
        dot.append("subgraph cluster_recepcion{\n");
        dot.append("label=\"COLA RECEPCIÓN\";\n");
        dot.append("bgcolor=\"bisque\"; \n");
        dot.append("Start1 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("node[shape = box];\n");

        actual = cola_Clientes_Recepcion.getCabezaLista();
        while(actual != null){
            nombresNodos += "Nodo"+ actual.hashCode() + "[Label="+((Cliente)actual.getValor()).getNombre()+"];\n";
            if(actual == cola_Clientes_Recepcion.getCabezaLista()){
                conexiones += String.format("Start1 -> Nodo%d \n", actual.hashCode());
            }
            if(actual.siguiente != null){
                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}"); //FIN COLA CLIENTES EN RECEPCIÓN

        dot.append("rankdir = LR;\n");
        dot.append("}"); //FIN DEL DOCUMENTO

        FileWriter fichero = null;
        PrintWriter pw = null;
        //Parte de la creación de un fichero
        try
        {
            fichero = new FileWriter("./"+nombreFichero+".dot");
            pw = new PrintWriter(fichero);

            pw.println(dot);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        dibujar("./"+nombreFichero+".dot", "./"+nombreFichero+".svg");
    }

    //Método para pasar del archivo .dot a Imagen(png, jpg, etc...)
    public static void dibujar( String direccionDot, String direccionSvg ){
        try
        {
            ProcessBuilder pbuilder;
            /*
             * Realiza la construccion del comando
             * en la linea de comandos esto es:
             * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder( "dot", "-Tsvg", "-o", direccionSvg, direccionDot );
            pbuilder.redirectErrorStream( true );
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception e) { e.printStackTrace(); }
    }

}
