package app;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray;

import estructuras.ListaEnlazada;

import java.util.Random;

import javax.swing.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static boolean carga_Masiva = false;
    static boolean carga_Ventanillas = false;
    static ListaEnlazada<Cliente> cola_Clientes_Recepcion = new ListaEnlazada<>();
    static ListaEnlazada<Ventanilla> lista_ventanillas = new ListaEnlazada<>();
    static ListaEnlazada<Cliente> lista_Clientes_Espera = new ListaEnlazada<>();
    static ListaEnlazada<Cliente> lista_Clientes_Atendidos = new ListaEnlazada<>();
    static ListaEnlazada<Imagen> cola_Imagenes_Color = new ListaEnlazada<>();
    static ListaEnlazada<Imagen> cola_Imagenes_Bw = new ListaEnlazada<>();
    static int num_Ventanillas = 0;
    static int cont_Pasos = 1;
    static String pasos_Sistema = "";
    static String[] nombres_Clientes = {"Diego", "Megan", "Dannah", "Giovanni", "Melissa", "Liz", "Donald", "Kenai", "Misha"
            , "Poo", "Samurai Jack", "Ben 10", "Matt"};
    static String[] apellidos_Clientes = {"Mazariegos", "Barrientos", "Morales", "Betancourt", "Molina", "Mcklay", "Smith", "Murdock", "Clay", "Flanders"};
    static ListaEnlazada<Cliente> lista_top5_ClientesImagenesColor = new ListaEnlazada<>();
    static ListaEnlazada<Cliente> lista_top5_ClientesImagenesBw = new ListaEnlazada<>();
    static ListaEnlazada<Cliente> lista_MasPasos = new ListaEnlazada<>();


    public static void main(String[] args) {
        boolean salida = false;
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
                                        leerJSON(); //Este método hace toda la magia de crear los objetos y asignarlos a la lista.
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
                                        for (int i = 1; i <= num_Ventanillas; i++) {
                                            Ventanilla new_Ventanilla = new Ventanilla(i);
                                            lista_ventanillas.insertElement_AtEnding(new_Ventanilla);
                                        }
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
                        } else { //Aquí va la lógica de los pasos
                            System.out.println(realizar_Paso());
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
                                abrirarchivo("./Estado_de_las_estructuras.svg");
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
                            boolean regresar = false;
                            while (true) {
                                String submenu;
                                System.out.println("""
                                        a. Reporte top 5 de clientes con mayor cantidad de imágenes.\s
                                        b. Reporte top 5 de clientes con menor cantidad de imágenes en blanco y negro.\s
                                        c. Reporte información del cliente que más pasos estuvo en el sistema.\s
                                        d. Reporte de Datos de un cliente en específico.\s
                                        e. Regresar""");
                                System.out.print("Ingrese una opción para continuar: ");
                                submenu = sc.nextLine();
                                switch(submenu){
                                    case "a" -> {
                                        System.out.println("Escogió la opción \"a\".  Reporte top 5 de clientes con mayor cantidad de imágenes.");
                                        getLista_top5_ClientesImagenesColor();
                                        System.out.println("Se creo el Top 5 de clientes atendidos con mayor cantidad de imagenes a color con éxito!!!");
                                    }
                                    case "b" -> {
                                        System.out.println("Escogió la opción \"b\". Reporte top 5 de clientes con menor cantidad de imágenes en blanco y negro.");
                                        getLista_top5_ClientesImagenesBw();
                                        System.out.println("Se creo el Top 5 de clientes atendidos con menor cantidad de imagenes a blanco y negro con éxito!!!");
                                    }
                                    case "c" -> {
                                        System.out.println("Escogió la opción \"c\". Reporte información del cliente que más pasos estuvo en el sistema.");
                                        getCliente_MasPasos();
                                    }
                                    case "d" -> {
                                        System.out.println("Escogió la opción \"d\". Reporte de Datos de un cliente en específico.");
                                        System.out.print("Ingrese el id del cliente que desea buscar: ");
                                        int id_Cliente = Integer.parseInt(sc.nextLine());
                                        getCliente(id_Cliente);
                                    }
                                    case "e" -> {
                                        System.out.println("Escogió la opción \"e\". Regresar");
                                        regresar = true;
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
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    case 5 -> {
                        System.out.println("Escogio la opción 5. Acerca del estudiante\n");
                        System.out.println("""
                                Diego André Mazariegos Barrientos
                                Carnet: 202003975
                                CUI: 3016552860101
                                5to. Semestre Universidad de San Carlos de Guatemala
                                Trabajo: Proyecto 1 fase 1 
                                Curso: Laboratorio de estructuras de datos
                                Sección: A""");
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    case 6 -> {
                        System.out.println("Escogio la opción 6. Salir\n");
                        salida = true;
                        System.out.println("Que tenga feliz día :DDD");
                        System.out.println("Presione Enter para continuar....");
                        sc.nextLine();
                    }
                    default -> System.out.println("Ingrese un numero valido. \n");
                }
                if (salida == true){
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ingrese un caracter valido. \n");
            }
        }
    }

    //-------------------------------------Método con el algoritmo de los pasos en el programa--------------------------
    public static String realizar_Paso() {
        boolean ingreso_Cliente_Ventanilla = true; //Esta variable es la que controla el ingreso único de un solo cliente a una ventanilla por paso.
        boolean cliente_Dejo_Ventanilla = false;
        pasos_Sistema += "======================PASO " + cont_Pasos + "======================\n";
        pasos_Sistema += "==================================================\n";
        //Agregar clientes al sistema después del primer paso
        if (cont_Pasos != 1) {
            agregarClientesRandom();
        }
        //PRIMERA SECCIÓN DEL ALGORITMO DE PASOS
        //arriba de este analisis de la impresora va el analisis de la lista de clientes. Ya que el envío de las impresiones a la lista de espera consumen un paso.
        ListaEnlazada.Nodo actual_ClientesEspera = lista_Clientes_Espera.getCabezaLista();
        while (actual_ClientesEspera != null) {
            Cliente cliente = (Cliente) actual_ClientesEspera.getValor();
            if (cliente.getCont_img_color() == cliente.getNum_Img_Color() && cliente.getCont_img_bw() == cliente.getNum_Img_Bw()) {
                pasos_Sistema += "* El cliente " + cliente.getId() + " ya posee todas sus imágenes impresas y sale de la empersa registrando el tiempo total dentro de ella (" + cliente.getCont_Pasos_Sistema() + " pasos)\n";
                //Se añade al cliente a la lista de clientes atendidos.
                lista_Clientes_Atendidos.insertElement_AtEnding(cliente);
                //Se elimina al cliente de la lista de espera.
                lista_Clientes_Espera.deleteElement_AtPosition(lista_Clientes_Espera.getPosition(actual_ClientesEspera));

            } else {
                cliente.sumarPaso();         //Aquí realizo la condición de sumarle la cantidad de pasos que estuvieron en la lista de espera los clientes.
            }
            actual_ClientesEspera = actual_ClientesEspera.siguiente;
        }

        //Lo primero sería analizar la cola de impresiones. Ya que el envío de las impresiones también es un paso.
        //en esta parte se va a analizar la cola en la impresiones y los clientes en espera.
        ListaEnlazada.Nodo actual_Color = cola_Imagenes_Color.getCabezaLista();
        Imagen imagen_Actual;
        if (actual_Color != null) {
            imagen_Actual = (Imagen) actual_Color.getValor();
            if (imagen_Actual.getCont_Pasos() == 1) {
                pasos_Sistema += "* Se completa la impresión de una imagen a COLOR y se le entrega al cliente " + imagen_Actual.getCliente().getId() + ". (tiempo: 2 pasos)\n";
                imagen_Actual.getCliente().sumar_Cont_Img_Color();
                cola_Imagenes_Color.deleteElement_AtBeggining();
            } else {
                imagen_Actual.restar_Cont_Pasos();
            }
        }
        ListaEnlazada.Nodo actual_Bw = cola_Imagenes_Bw.getCabezaLista();
        if (actual_Bw != null) {
            imagen_Actual = (Imagen) actual_Bw.getValor();
            if (imagen_Actual.getCont_Pasos() == 1) {
                pasos_Sistema += "* Se completa la impresión de una imagen a Blanco y Negro (B&W) y se le entrega al cliente " + imagen_Actual.getCliente().getId() + ". (tiempo: 1 paso)\n";
                imagen_Actual.getCliente().sumar_Cont_Img_Bw();
                cola_Imagenes_Bw.deleteElement_AtBeggining();
            } else {
                imagen_Actual.restar_Cont_Pasos();
            }
        }
        //Lo primero segundo sería recorrer la lista de ventanilla es la parte principal.
        ListaEnlazada.Nodo actual_Ventanilla = lista_ventanillas.getCabezaLista();
        while (actual_Ventanilla != null) {
            Ventanilla ventanilla = (Ventanilla) actual_Ventanilla.getValor();
            if (ventanilla.isOcupada()) { //Si esta ocupada la ventanilla porque la ventanilla.isOcupada es true.
                // si ambos tipos de imagen ya fueron entregados por el cliente entonces se retira el cliente de la ventanilla
                // Y se va para la lista de espera. Además, se pasan las imagenes a la cola de impresión.
                if (ventanilla.getCliente().getCont_img_bw() == 0 && ventanilla.getCliente().getCont_img_color() == 0) {
                    Cliente cliente = ventanilla.getCliente();
                    lista_Clientes_Espera.insertElement_AtEnding(cliente);
                    ventanilla.setCliente(null);
                    ventanilla.setOcupada(false);
                    cliente_Dejo_Ventanilla = true;
                    pasos_Sistema += "* El cliente " + cliente.getId() + " es atendido e ingresa a la lista de espera.\n";
                    //Envío de la pila de imagenes en forma clasificada a la cola de impresiones.
                    ListaEnlazada.Nodo actual_Imagen = ((Ventanilla) actual_Ventanilla.getValor()).getPila_Imagenes().getColaLista();
                    while (actual_Imagen != null) {
                        Imagen imagen = (Imagen) actual_Imagen.getValor();
                        if (imagen.getTipo() == "COLOR") {
                            cola_Imagenes_Color.insertElement_AtEnding(imagen);
                            ((Ventanilla) actual_Ventanilla.getValor()).getPila_Imagenes().deleteElement_AtEnding();
                        } else {
                            cola_Imagenes_Bw.insertElement_AtEnding(imagen);
                            ((Ventanilla) actual_Ventanilla.getValor()).getPila_Imagenes().deleteElement_AtEnding();
                        }
                        actual_Imagen = actual_Imagen.anterior;
                    }
                    pasos_Sistema += "* La ventanilla " + ventanilla.getId_Ventanilla() + " envía las imágenes del cliente " + cliente.getId() + " a sus respectivas colas de impresión.\n";
                } else if (ventanilla.getCliente().getCont_img_color() != 0) {
                    //Se ingresa una imagen a la pila de imagenes de la ventanilla correspondiente.
                    ventanilla.getCliente().restar_Cont_Img_Color();
                    Imagen imagen_nueva = new Imagen("COLOR", 2, 2, ventanilla.getCliente());
                    ventanilla.getPila_Imagenes().insertElement_AtEnding(imagen_nueva);
                    pasos_Sistema += "* La ventanilla " + ventanilla.getId_Ventanilla() + " recibe una imagen tipo COLOR del cliente " + ventanilla.getCliente().getId() + ".\n";
                } else {
                    //Se ingresa una imagen a la pila de imagenes de la ventanilla correspondiente.
                    ventanilla.getCliente().restar_Cont_Img_Bw();
                    Imagen imagen_nueva = new Imagen("B&W", 1, 1, ventanilla.getCliente());
                    ventanilla.getPila_Imagenes().insertElement_AtEnding(imagen_nueva);
                    pasos_Sistema += "* La ventanilla " + ventanilla.getId_Ventanilla() + " recibe una imagen tipo B&W del cliente " + ventanilla.getCliente().getId() + ".\n";
                }
            } else { // si no esta ocupada porque ventanilla.isOcupada sería false.
                if (cola_Clientes_Recepcion.getlength() == 0) {
                    //Que no haga nada basicamente para evitar errores con los métodos de la clase ListaEnlazada.
                } else if (ingreso_Cliente_Ventanilla) {//Aquí validamos si no ha ingresado ya otro cliente a una ventanilla en el mismo paso.
                    Cliente cliente = cola_Clientes_Recepcion.extractElement_AtBeggining().getValor();
                    ventanilla.setCliente(cliente);
                    ventanilla.setOcupada(true);
                    ingreso_Cliente_Ventanilla = false;
                    pasos_Sistema += "* El cliente " + cliente.getId() + " ingresa a la ventanilla " + ventanilla.getId_Ventanilla() + " (la primera disponible).\n";
                }
            }
            actual_Ventanilla = actual_Ventanilla.siguiente;
        }
        //SEGUNDA SECCIÓN DEL ALGORITMO DE PASOS
        //Cuando un cliente pasa de la ventanilla a la lista de espera aquí otro cliente en el mismo paso puede pasar a la ventanilla que quedo libre.
        if (cola_Clientes_Recepcion.getlength() != 0) {
            if (cliente_Dejo_Ventanilla) {//Preguntamos si un cliente dejo la ventanilla pues si es así entonces en ese mismo paso un cliente tiene que ocuparla.
                actual_Ventanilla = lista_ventanillas.getCabezaLista();
                while (actual_Ventanilla != null) {
                    Ventanilla ventanilla = (Ventanilla) actual_Ventanilla.getValor();
                    if (!ventanilla.isOcupada()) { //Si la ventanilla no esta ocupada.
                        Cliente cliente = (Cliente) cola_Clientes_Recepcion.getCabezaLista().getValor();
                        ventanilla.setCliente(cliente);
                        cola_Clientes_Recepcion.deleteElement_AtBeggining();
                        pasos_Sistema += "* El cliente " + cliente.getId() + " ingresa a la ventanilla " + ventanilla.getId_Ventanilla() + "(la primera disponible).\n";
                        ventanilla.setOcupada(true);
                    }
                    actual_Ventanilla = actual_Ventanilla.siguiente;
                }
            }
        }

        //TERCERA SECCIÓN DEL ALGORITMO DE PASOS
        //aquí vendría siendo el ingreso de clientes nuevos generados de forma aleatoria.

        //Parte de actualización de datos.


        cont_Pasos++;
        return pasos_Sistema;
    }

    public static void reiniciarPrograma() {
        lista_ventanillas = new ListaEnlazada<>();
        lista_Clientes_Espera = new ListaEnlazada<>();
        lista_Clientes_Atendidos = new ListaEnlazada<>();
        cola_Imagenes_Bw = new ListaEnlazada<>();
        cola_Imagenes_Color = new ListaEnlazada<>();
    }

    public static void agregarClientesRandom() {
        int index1 = (int) (Math.random() * nombres_Clientes.length);
        int index2 = (int) (Math.random() * apellidos_Clientes.length);

        String nombreCliente = nombres_Clientes[index1] + " " + apellidos_Clientes[index2];

        Random random = new Random();

        int num_Clientes = random.nextInt(3 + 0) + 0;

        while (num_Clientes != 0) {
            int img_Color = random.nextInt(4 + 1) + 1;
            int img_Bw = random.nextInt(4 + 1) + 1;
            int num_Random = random.nextInt();
            Cliente cliente = new Cliente(nombreCliente.hashCode()+num_Random, nombreCliente, img_Color, img_Bw);
            cola_Clientes_Recepcion.insertElement_AtEnding(cliente);
            System.out.println("* El cliente " + cliente.getId() + " ingreso a la cola de recepción.");
            num_Clientes--;
        }

    }


    //-----------------------------------------Método para leer ficheros e interpretarlo----------------------------------

    //Método para leer un fichero y pasar el fichero a un String.
    public static File leerFichero() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser("./"); // Crear un objeto para seleccionar un archivo
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile(); // Crea un objeto de archivo, que es el archivo seleccionado
            return file;
        } else {
            System.out.println("¡Ningún archivo seleccionado!");
            return null;
        }
    }

    //Método para leer un JSON recibe un String y lo convierte a objetos.
    public static void leerJSON() {
        JSONParser file = new JSONParser();
        try(FileReader reader = new FileReader(leerFichero());) {
            Object obj = new JSONParser().parse(reader);
            JSONObject json = (JSONObject) obj;
            Object cliente = json.get("Cliente1");
            int i = 1;
            while(cliente != null){
                JSONObject datos_Cliente = (JSONObject) cliente;
                //iNGRESO DE DATOS
                int id = Integer.parseInt(datos_Cliente.get("id_cliente").toString());
                String nombre = datos_Cliente.get("nombre_cliente").toString();
                int img_color = Integer.parseInt(datos_Cliente.get("img_color").toString());
                int img_bw = Integer.parseInt(datos_Cliente.get("img_bw").toString());

                Cliente nuevo_cliente = new Cliente(id, nombre, img_color, img_bw);
                cola_Clientes_Recepcion.insertElement_AtEnding(nuevo_cliente);
                i++;
                cliente = json.get("Cliente"+i);
            }
        }catch (Exception e){

        }
    }


    //--------------------------------Métodos para imprimir en un archivo .dot (Graphviz)--------------------------------
    //Nota: Impresión por secciones.
    public static void crearFicheroDot_ListaSimple(String nombreFichero) {
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
        while (actual != null) {
            Cliente cliente_Actual = ((Cliente) actual.getValor());
            nombresNodos += "Nodo" + actual.hashCode() + "[shape=folder label=\"Cliente " + cliente_Actual.getId() + "\\n" + cliente_Actual.getNombre() + " \\n Color: " + cliente_Actual.getCont_img_color() + "\\n B&W: " + cliente_Actual.getCont_img_bw() + "\"];\n";
            if (actual == lista_Clientes_Atendidos.getCabezaLista()) {
                conexiones += String.format("Start5 -> Nodo%d;\n", actual.hashCode());
            }
            if (actual.siguiente != null) {
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(), actual.siguiente.hashCode());
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
        while (actual != null) {
            Cliente cliente_Actual = ((Cliente) actual.getValor());
            nombresNodos += "Nodo" + actual.hashCode() + "[shape=folder label=\"Cliente " + cliente_Actual.getId() + "\\n" + cliente_Actual.getNombre() + " \\n Color: " + cliente_Actual.getCont_img_color() + "\\n B&W: " + cliente_Actual.getCont_img_bw() + "\"];\n";
            if (actual == lista_Clientes_Espera.getCabezaLista()) {
                conexiones += String.format("Start4 -> Nodo%d;\n", actual.hashCode());
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(),lista_Clientes_Espera.getColaLista().hashCode());
            }
            if (actual.siguiente != null) {
                conexiones += String.format("Nodo%d -> Nodo%d; \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            if (actual.anterior != null) {
                conexiones += String.format("Nodo%d -> Nodo%d; \n", actual.hashCode(), actual.anterior.hashCode());
            }
            if (actual == lista_Clientes_Espera.getColaLista()) {
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(),lista_Clientes_Espera.getCabezaLista().hashCode());
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
        dot.append("Start31 [shape=\"Mdiamond\" label=\"COLOR\"];\n");
        dot.append("Start32 [shape=\"Mdiamond\" label=\"B&W\"];\n");
        dot.append("node[shape = box];\n");

        actual = cola_Imagenes_Color.getCabezaLista();
        while (actual != null) {
            Imagen imagenActual = (Imagen) actual.getValor();
            nombresNodos += "Nodo" + actual.hashCode() + "[shape = note label=\"" + imagenActual.getTipo() + "\\n " + imagenActual.getCont_Pasos() + " \"];\n";
            if (actual == cola_Imagenes_Color.getCabezaLista()) {
                conexiones += String.format("Start31 -> Nodo%d;\n", actual.hashCode());
            }
            if (actual.siguiente != null) {
                conexiones += String.format("Nodo%d -> Nodo%d; \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        actual = cola_Imagenes_Bw.getCabezaLista();
        while (actual != null) {
            Imagen imagenActual = (Imagen) actual.getValor();
            nombresNodos += "Nodo" + actual.hashCode() + "[shape = note label=\"" + imagenActual.getTipo() + "\\n " + imagenActual.getCont_Pasos() + "\"];\n";
            if (actual == cola_Imagenes_Bw.getCabezaLista()) {
                conexiones += String.format("Start32 -> Nodo%d;\n", actual.hashCode());
            }
            if (actual.siguiente != null) {
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(), actual.siguiente.hashCode());
            }
            actual = actual.siguiente;
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}"); //FIN COLA IMPRESORAS

        nombresNodos = "";
        conexiones = "";
        //Sección de VENTANILLAS
        dot.append("subgraph cluster_ventanillas{ \n");
        dot.append("label=\"LISTA VENTANILLA\";\n");
        dot.append("bgcolor=\"mintcream\";\n");
        dot.append("node[shape = box];\n");
        String nombresClientes_Ventanillas = "";
        actual = lista_ventanillas.getCabezaLista();
        while (actual != null) {
            //Primero hay que verificar si existe un cliente en la ventanilla en este momento.
            Cliente cliente_Ventanilla = ((Ventanilla) actual.getValor()).getCliente();
            if (cliente_Ventanilla != null) { //Si existe el cliente tiene que apuntar hacia la ventanilla en la que esta.
                nombresClientes_Ventanillas += "Nodo" + cliente_Ventanilla.hashCode() + "[shape = folder label=\" Cliente " + cliente_Ventanilla.getId() + "\\n" + cliente_Ventanilla.getNombre() + "\\n Color: " + cliente_Ventanilla.getCont_img_color() + "\\n B&W: " + cliente_Ventanilla.getCont_img_bw() + "\"];\n";
                conexiones += String.format("Nodo%d -> Nodo%d [constraint = false];\n", cliente_Ventanilla.hashCode(), actual.hashCode());
                rankSame += String.format("{rank = same; Nodo%d; Nodo%d};\n", cliente_Ventanilla.hashCode(), actual.hashCode());
            }
            nombresNodos += "Nodo" + actual.hashCode() + "[shape = box3d label=\" Ventanilla \\n " + ((Ventanilla) actual.getValor()).getId_Ventanilla() + "\"];\n";
            if (actual.siguiente != null) {
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(), actual.siguiente.hashCode());
            }

            //Esta parte de aquí es para poder graficar las pilas de imagenes de cada ventanilla.
            //Aquí se obtiene la cola porque en un pila el último que se ingresa es el primero en salir.
            ListaEnlazada.Nodo actual2 = ((Ventanilla) actual.getValor()).getPila_Imagenes().getCabezaLista();
            while (actual2 != null) {
                nombresNodos += "Nodo" + actual2.hashCode() + "[shape = note label=\"" + ((Imagen) actual2.getValor()).getTipo() + "\"];\n";
                if (actual2 == ((Ventanilla) actual.getValor()).getPila_Imagenes().getCabezaLista()) {
                    conexiones += String.format("Nodo%d -> Nodo%d [constraint = false];\n", actual.hashCode(), actual2.hashCode());
                    rankSame += String.format("{rank = same; Nodo%d; Nodo%d};\n", actual.hashCode(), actual2.hashCode());
                }
                if (actual2.siguiente != null) {
                    conexiones += String.format("Nodo%d -> Nodo%d [constraint = false];\n", actual2.hashCode(), actual2.siguiente.hashCode());
                    rankSame += String.format("{rank = same; Nodo%d; Nodo%d};\n", actual2.hashCode(), actual2.siguiente.hashCode());
                }
                rankSame += String.format("{rank = same; Nodo%d; Nodo%d};\n", actual.hashCode(), actual2.hashCode());
                actual2 = actual2.siguiente;
            }

            actual = actual.siguiente;
        }
        dot.append(nombresClientes_Ventanillas);
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(rankSame); // el atributo rank=same de Graphviz lo utilizo para poder apilar de forma correcta las ventanillas con su pila de imagenes.
        dot.append("}"); //FIN LISTA DE VENTANILLAS CON PILAS DE IMAGENES

        nombresClientes_Ventanillas = "";
        rankSame = "";
        nombresNodos = "";
        conexiones = "";
        //Sección de LISTA DE CLIENTES EN ESPERA
        dot.append("subgraph cluster_recepcion{\n");
        dot.append("label=\"COLA RECEPCIÓN\";\n");
        dot.append("bgcolor=\"bisque\"; \n");
        dot.append("Start1 [shape=\"Mdiamond\" label=\"Start\"];\n");
        dot.append("node[shape = box];\n");

        actual = cola_Clientes_Recepcion.getCabezaLista();
        while (actual != null) {
            Cliente cliente_Actual = ((Cliente) actual.getValor());
            nombresNodos += "Nodo" + actual.hashCode() + "[shape=folder label=\"Cliente " + cliente_Actual.getId() + "\\n" + cliente_Actual.getNombre() + " \\n Color: " + cliente_Actual.getCont_img_color() + "\\n B&W: " + cliente_Actual.getCont_img_bw() + "\"];\n";
            if (actual == cola_Clientes_Recepcion.getCabezaLista()) {
                conexiones += String.format("Start1 -> Nodo%d; \n", actual.hashCode());
            }
            if (actual.siguiente != null) {
                conexiones += String.format("Nodo%d -> Nodo%d;\n", actual.hashCode(), actual.siguiente.hashCode());
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
        try {
            fichero = new FileWriter("./" + nombreFichero + ".dot");
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
        dibujar("./" + nombreFichero + ".dot", "./" + nombreFichero + ".svg");
    }

    //Método para pasar del archivo .dot a Imagen(png, jpg, etc...)
    public static void dibujar(String direccionDot, String direccionSvg) {
        try {
            ProcessBuilder pbuilder;
            /*
             * Realiza la construccion del comando
             * en la linea de comandos esto es:
             * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder("dot", "-Tsvg", "-o", direccionSvg, direccionDot);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Método para abrir el archivo .svg generado por graphviz directamente desde el programa
    public static void abrirarchivo(String archivo) throws IOException {
        File objetofile = new File(archivo);
        Desktop.getDesktop().open(objetofile);
    }

    //---------------------------------Métodos de reportes solicitados en el proyecto------------------------------------
    public static void getLista_top5_ClientesImagenesColor(){
        lista_top5_ClientesImagenesColor = new ListaEnlazada<>();
        ListaEnlazada.Nodo nodo_Actual = lista_Clientes_Atendidos.getCabezaLista();
        while(nodo_Actual != null){
            Cliente clienteClon = (Cliente) nodo_Actual.getValor();
            lista_top5_ClientesImagenesColor.insertElement_AtEnding(clienteClon);
            nodo_Actual = nodo_Actual.siguiente;
        }
        lista_top5_ClientesImagenesColor.ordenar_InsertionSort_MayorMenorColor();
        crearGrafico_Tops("Top5_ImagenesColor", lista_top5_ClientesImagenesColor, "TOP 5 CLIENTES CON MAYOR CANTIDAD DE IMAGENES A COLOR");
        lista_top5_ClientesImagenesColor.abrirarchivo("./Top5_ImagenesColor.svg");
    }
    public static void getLista_top5_ClientesImagenesBw(){
        lista_top5_ClientesImagenesBw = new ListaEnlazada<>();
        ListaEnlazada.Nodo nodo_Actual = lista_Clientes_Atendidos.getCabezaLista();
        while(nodo_Actual != null){
            Cliente clienteClon = (Cliente) nodo_Actual.getValor();
            lista_top5_ClientesImagenesBw.insertElement_AtEnding(clienteClon);
            nodo_Actual = nodo_Actual.siguiente;
        }
        lista_top5_ClientesImagenesBw.ordenar_InsertionSort_MenorMayorBw();
        crearGrafico_Tops("Top5_ImagenesBw", lista_top5_ClientesImagenesBw, "TOP 5 CLIENTES CON MENOR CANTIDAD DE IMAGENES EN BlANCO Y NEGRO");
        lista_top5_ClientesImagenesBw.abrirarchivo("./Top5_ImagenesBw.svg");
    }
    public static void getCliente_MasPasos(){
        try {
            lista_MasPasos = new ListaEnlazada<>();
            ListaEnlazada.Nodo nodo_Actual = lista_Clientes_Atendidos.getCabezaLista();
            while (nodo_Actual != null) {
                Cliente clienteClon = (Cliente) nodo_Actual.getValor();
                lista_MasPasos.insertElement_AtEnding(clienteClon);
                nodo_Actual = nodo_Actual.siguiente;
            }
            lista_MasPasos.ordenar_InsertionSort_MayorMenorPasos();
            lista_MasPasos.crearFicheroDot_DatosCliente("Cliente_Mas_Pasos", lista_MasPasos.getCabezaLista().getValor());
            lista_MasPasos.abrirarchivo("./Cliente_Mas_Pasos.svg");
        }catch (Exception e){
            System.out.println("Todavía no hay clientes registrados intente de nuevo más tarde, gracias :D");
        }
    }
    public static void getCliente(int id_ClienteBuscar){
        try {
            Cliente cliente = (Cliente) lista_Clientes_Atendidos.getNodoEspecifico(id_ClienteBuscar).getValor();
            if (cliente != null) {

                lista_Clientes_Atendidos.crearFicheroDot_DatosCliente("Datos_Cliente_"+cliente.getId(), cliente);
                lista_Clientes_Atendidos.abrirarchivo("./Datos_Cliente_"+cliente.getId()+".svg");
                System.out.println("Reporte del cliente buscado creado con éxito!!!!");
            } else {
                System.out.println("Todavía no hay clientes o ingrese un id válido en el sistema intente de nuevo más tarde :D");
            }
        }catch (Exception e){
            System.out.println("Todavía no hay clientes o ingrese un id válido en el sistema intente de nuevo más tarde :D");
        }

    }

    public static void crearGrafico_Tops(String nombreFichero, ListaEnlazada listaEnlazada, String titulo){
        //Parte del String o texto que va a llevar el fichero
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();

        dot.append("digraph G { \n");
        dot.append("subgraph cluster_ClientesTOP{ \n");
        dot.append("label=\""+titulo+"\"; \n");
        dot.append("bgcolor=\"darkseagreen1\"; \n");
        dot.append("node[shape = box]; \n");
        String nombresNodos = "";
        String conexiones = "";
        ListaEnlazada.Nodo actual = listaEnlazada.getCabezaLista();
        int i = 0;
        while (actual !=  null && i < 5){
            Cliente cliente_Actual = (Cliente)actual.getValor();
            nombresNodos += "Nodo" + actual.hashCode() + "[shape=folder label=\"Cliente " + cliente_Actual.getId() + "\\n" + cliente_Actual.getNombre() + " \\n Color: " + cliente_Actual.getCont_img_color() + "\\n B&W: " + cliente_Actual.getCont_img_bw() + "\\nPasos en espera: "+cliente_Actual.getCont_Pasos_Sistema()+"\"];\n";
            // Aquí es el punto donde se define si el método es para una lista simple, doble, etc...
            if(actual.siguiente != null && i != 4){
                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
            }
            i++;
            actual = actual.siguiente;
        }

        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}");
        dot.append("rankdir = TB;\n");
        dot.append("}");


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
        listaEnlazada.dibujar("./"+nombreFichero+".dot", "./"+nombreFichero+".svg");
    }

}
