package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase Graphviz que me sirve para poder usar el método
 de dibujar en cada una de mis estructuras y así
 no repetir este método.
 * @author Melissa
 */
public class Graphviz {
    
    
    /**
     * Método para pasar del archivo (.dot, .neato, etc...) a Imagen(png, jpg, svg, etc...)
     * @param direccionDot
     * @param direccionSvg 
     */
    public void dibujar(String formatoEntrada, String formatoSalida, String direccionDot, String direccionSvg) {
        try {
            ProcessBuilder pbuilder;
            /*
             * Realiza la construccion del comando
             * en la linea de comandos esto es:
             * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder(formatoEntrada, formatoSalida, "-o", direccionSvg, direccionDot);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
