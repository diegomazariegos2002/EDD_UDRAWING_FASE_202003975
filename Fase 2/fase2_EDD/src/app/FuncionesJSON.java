package app;

import ventanas.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import clases_proyecto.Capa;

/**
 * Clase para manejar las funciones que se realizan con JSON en el proyecto.
 *
 * @author Melissa
 */
public class FuncionesJSON {

    /**
     * Método para la lectura del JSON capas. Este método además hace la
     * construcción de las capas de una vez.
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
                mca.clienteRegistrado.getArbol_CapasGenerales().crearFicheroDot_Arbol("Arbol_ABB_Capas", rutaCarpetaCapas+"/Arbol_AVL_Capas", rutaCarpetaCapas+"/Arbol_AVL_Capas");
            }
        } catch (Exception e) {
            System.out.println("Error en la carga del JSON.");
        }
    }

}
