package app;

import ventanas.Modulo_Admin;
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
    public void leerJSON_Capas(Modulo_Admin modAdmin, Funciones_Ficheros ff) {
        try {
            //Make object GSON
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            FileReader fr = ff.archivo_Buscar(modAdmin);
            if (fr != null) {
                //Se manda a llamar al método de lectura de ficheros y que devuelve un FileReader
                JsonElement datos = parser.parse(fr);

                for (JsonElement capa : datos.getAsJsonArray()) {
                    JsonObject jObjCapa = (JsonObject) capa;
                    int id = jObjCapa.get("id_capa").getAsInt();
                    Capa newCapa = new Capa(id);

                    /*System.out.println(jObjCapa.get("id_capa"));*/
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
                    //Aquí se tendría que añadir la capa al árbol de capas.
//                    if (id == 8) {
//                        System.out.println("========================" + jObjCapa.get("id_capa") + "================================");
//                        newCapa.getMatriz_Capa().mostrarMatrizFilas();
//                    }
                    newCapa.getMatriz_Capa().crearFicheroNeato_MatrizConexiones(String.valueOf(id) + "_ConConexiones");
                    newCapa.getMatriz_Capa().crearFicheroNeato_MatrizSinConexiones(String.valueOf(id) + "_SinConexiones");
                    
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la carga del JSON.");
        }
    }

}
