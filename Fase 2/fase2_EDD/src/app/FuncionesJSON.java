package app;

import ventanas.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import clases_proyecto.Capa;
import clases_proyecto.Imagen;
import clases_proyecto.Album;
import clases_proyecto.Cliente;
import java.io.IOException;

/**
 * Clase para manejar las funciones que se realizan con JSON en el proyecto.
 *
 * @author Melissa
 */
public class FuncionesJSON {

    /**
     * Método para la lectura del JSON capas.Este método además hace la
     * construcción de las capas de una vez.
     *
     * @param mca
     * @param ff
     * @param rutaCarpetaCapas
     */
    public void leerJSON_Capas(Modulo_Cliente_CargaMasiva mca, Funciones_Ficheros ff, String rutaCarpetaCapas) {
        try {
            //Make object GSON
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            FileReader fr = ff.archivo_Buscar(mca);
            if (fr != null) {
                //Se manda a llamar al método de lectura de ficheros y que devuelve un FileReader
                JsonElement datos = parser.parse(fr);

                for (JsonElement capa : datos.getAsJsonArray()) { //for que se repite por cada capa.
                    JsonObject jObjCapa = (JsonObject) capa;
                    int id = jObjCapa.get("id_capa").getAsInt();
                    Capa newCapa = new Capa(id);

                    System.out.println(jObjCapa.get("id_capa"));
                    JsonArray pixeles = (JsonArray) jObjCapa.get("pixeles");
                    for (JsonElement pixel : pixeles) {
                        JsonObject jObjPixel = (JsonObject) pixel;
                        /*System.out.println(jObjPixel.get("fila"));
                        System.out.println(jObjPixel.get("columna"));
                        System.out.println(jObjPixel.get("color"));*/
                        int fila = jObjPixel.get("fila").getAsInt();
                        int columna = jObjPixel.get("columna").getAsInt();
                        String color = jObjPixel.get("color").getAsString();
                        newCapa.getMatriz_Capa().insertar(fila, columna, color, color);
                    }
                    String rutaNeatoConConexiones = rutaCarpetaCapas + "/Neato_Con_Conexiones";
                    String rutaPngConConexiones = rutaCarpetaCapas + "/Imagenes_Con_Conexiones";
                    String rutaNeatoSinConexiones = rutaCarpetaCapas + "/Neato_Sin_Conexiones";
                    String rutaPngSinConexiones = rutaCarpetaCapas + "/Imagenes_Sin_Conexiones";
                    newCapa.getMatriz_Capa().crearFicheroNeato_MatrizConexiones(String.valueOf(id) + "_ConConexiones", rutaNeatoConConexiones, rutaPngConConexiones);
                    newCapa.getMatriz_Capa().crearFicheroNeato_MatrizSinConexiones(String.valueOf(id) + "_SinConexiones", rutaNeatoSinConexiones, rutaPngSinConexiones);

                    // Se inserta la nueva capa en el árbol de capas generales del Cliente logeado.
                    mca.clienteRegistrado.getArbol_CapasGenerales().insertar(newCapa);
                }
                mca.clienteRegistrado.getArbol_CapasGenerales().preOrden();
                mca.clienteRegistrado.getArbol_CapasGenerales().crearFicheroDot_Arbol("Arbol_ABB_Capas", rutaCarpetaCapas + "/Arbol_ABB_Capas", rutaCarpetaCapas + "/Arbol_ABB_Capas");
            }
        } catch (Exception e) {
            System.out.println("Error en la carga del JSON CAPAS.");
        }
    }

    /**
     * Método para la lectura del JSON Imagenes.Este método además hace la
     * construcción de las imagenes de una vez.
     *
     * @param mca
     * @param ff
     * @param rutaCarpetaImagenes
     */
    public void leerJSON_Imagenes(Modulo_Cliente_CargaMasiva mca, Funciones_Ficheros ff, String rutaCarpetaImagenes) {
        try {
            //Make object GSON
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            FileReader fr = ff.archivo_Buscar(mca);
            if (fr != null) {
                //Se manda a llamar al método de lectura de ficheros y que devuelve un FileReader
                JsonElement datos = parser.parse(fr);

                for (JsonElement imagen : datos.getAsJsonArray()) { //for que se repite por cada imagen.
                    JsonObject jObjImagen = (JsonObject) imagen;
                    int id = jObjImagen.get("id").getAsInt();
                    Imagen newImagen = new Imagen(id);

                    System.out.println("capa: " + jObjImagen.get("id"));
                    JsonArray id_Capas = (JsonArray) jObjImagen.get("capas");
                    for (JsonElement id_Capa : id_Capas) {
                        System.out.println(id_Capa.getAsInt());
                        /*
                            Recordar que cada ventana de carga masiva tiene un cliente registrado que a su vez tiene un árbol de capas generales
                            ya para cuando ejecute esta opción, entonces podemos obtener el valor de sus nodos por medio de crear una instancia 
                            temporal que es la parte de new CAPA(...); y así buscar dentro de su árbol abb e insertar la dirección de dicha capa
                            encontrada en el árbol abb de las capas que conforman cada imagen.
                         */
                        Capa capaTemporal = mca.clienteRegistrado.getArbol_CapasGenerales().getValue(new Capa(id_Capa.getAsInt()));
                        if (capaTemporal != null) {
                            newImagen.getCapasImagen().insertar(capaTemporal);
                        } else {
                            System.out.println("No existe una capa con el id: " + id_Capa.getAsInt());
                        }
                    }
                    /*
                        Generar recorrido por amplitud de cada capa en cada imagen.
                     */
//                    newImagen.unirCapasAmplitud();
//                    newImagen.getCapasUnidas().crearFicheroNeato_MatrizSinConexiones("Imagen_"+newImagen.getId_Imagen(), rutaCarpetaImagenes + "/Neato_Imagenes", rutaCarpetaImagenes + "/Imagenes_Puras");
                    /*
                        Agregar la imagen ya procesada al árbol de imágenes.
                     */
                    mca.clienteRegistrado.getArbol_Imagenes().insert(newImagen);
                }
                System.out.println("Recorrido PreOrden del árbol de imágenes");
                mca.clienteRegistrado.getArbol_Imagenes().preOrden();
                System.out.println("Recorrido por amplitud del árbol de imagenes");
                mca.clienteRegistrado.getArbol_Imagenes().recorridoAmplitud();
                mca.clienteRegistrado.getArbol_Imagenes().crearFicheroDot_Arbol("Arbol_AVL_Imagenes", rutaCarpetaImagenes + "/Arbol_AVL_Imagenes", rutaCarpetaImagenes + "/Arbol_AVL_Imagenes");

            }
        } catch (Exception error) {
            System.out.println("Error en la carga del JSON IMAGENES.");
        }
    }

    /**
     * Método para la lectura del JSON Albumes. Este método además hace la
     * construción de los álbumes de una vez.
     *
     * @param mca
     * @param ff
     * @param rutaCarpetaAlbumes
     */
    public void leerJSON_Albumes(Modulo_Cliente_CargaMasiva mca, Funciones_Ficheros ff, String rutaCarpetaAlbumes) {
        try {
            //Make object GSON
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            FileReader fr = ff.archivo_Buscar(mca);
            if (fr != null) {
                //Se manda a llamar al método de lectura de ficheros y que devuelve un FileReader
                JsonElement datos = parser.parse(fr);

                for (JsonElement imagen : datos.getAsJsonArray()) { //for que se repite por cada imagen.
                    JsonObject jObjImagen = (JsonObject) imagen;
                    String nombreAlbum = jObjImagen.get("nombre_album").getAsString();

                    //Se crea el nuevo album.
                    Album newAlbum = new Album(nombreAlbum);

                    System.out.println(nombreAlbum);
                    JsonArray imgs = (JsonArray) jObjImagen.get("imgs");
                    for (JsonElement id_Img : imgs) {
                        System.out.println(id_Img.getAsInt());
                        Imagen imagenTemporal = mca.clienteRegistrado.getArbol_Imagenes().getValue(new Imagen(id_Img.getAsInt()));
                        if (imagenTemporal != null) {

                            //Agregar la imagen al album.
                            newAlbum.getListaImagenes().insertElement_AtEnding(imagenTemporal);
                        } else {
                            System.out.println("No existe una capa con el id: " + id_Img.getAsInt());
                        }
                    }

                    //Ingresar el album a listado de álbumes del cliente.
                    mca.clienteRegistrado.getLista_Albumes().insertElement_AtEnding(newAlbum);
                }
                System.out.println(rutaCarpetaAlbumes);
                mca.clienteRegistrado.getLista_Albumes().crearFicheroDot_Albumes("Lista_Albumes", rutaCarpetaAlbumes, rutaCarpetaAlbumes);
            }
        } catch (Exception error) {
            System.out.println("Error en la carga del JSON ÁLBUMES.");
        }
    }

    /**
     * Método para la lectura del JSON Clientes. Este método además hace la
     * construción de los clientes de una vez.
     *
     * @param ma
     * @param ff
     * @param rutaCarpetaClientes
     */
    public void leerJSON_Clientes(Modulo_Admin ma, Funciones_Ficheros ff, String rutaCarpetaClientes) {
        try {
            //Make object GSON
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            FileReader fr = ff.archivo_Buscar(ma);
            if (fr != null) {
                //Se manda a llamar al método de lectura de ficheros y que devuelve un FileReader
                JsonElement datos = parser.parse(fr);

                for (JsonElement cliente : datos.getAsJsonArray()) { //for que se repite por cada imagen.
                    JsonObject jObjCliente = (JsonObject) cliente;
                    long dpi = Long.valueOf(jObjCliente.get("dpi").getAsString());
                    String nombreCliente = jObjCliente.get("nombre_cliente").getAsString();
                    String password = jObjCliente.get("password").getAsString();

                    //Se crea el nuevo Cliente.
                    Cliente newCliente = new Cliente(dpi, nombreCliente, password);
                    
                    System.out.println(newCliente);
                    
                    crearDirectoriosCliente(ff, newCliente);
                    
                    //Ingresar el cliente al árbol del cliente.
                    ma.arbolClientes.insertarEnArbol(newCliente);
                }
                System.out.println(rutaCarpetaClientes);
                ma.arbolClientes.graficarArbolB("Arbol_Clientes", rutaCarpetaClientes, rutaCarpetaClientes);
            }
        } catch (Exception error) {
            System.out.println("Error en la carga del JSON CLIENTES.");
        }
    }
    
        public void crearDirectoriosCliente(Funciones_Ficheros ff, Cliente clienteNuevo) throws IOException{
        ff.borrar_Directorio("./Clientes/Cliente_"+clienteNuevo.getDPI());
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI());
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Imagenes_Con_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Imagenes_Sin_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Neato_Con_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Neato_Sin_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Arbol_ABB_Capas");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes/Neato_Imagenes");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes/Imagenes_Puras");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes/Arbol_AVL_Imagenes");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Albumes");
    }
}
