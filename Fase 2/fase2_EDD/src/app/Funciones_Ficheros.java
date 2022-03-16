package app;

import ventanas.Modulo_Admin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase para manejar las funciones de los ficheros.
 *
 * @author Melissa
 */
public class Funciones_Ficheros {

    private JFileChooser accion = null;
    private File archivo = null;

    /**
     * Método para buscar un fichero de tipo JSON.
     *
     * @param ventana
     * @return
     */
    public FileReader archivo_Buscar(javax.swing.JFrame ventana) {
        accion = new JFileChooser("./");
        accion.setFileSelectionMode(0);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JSON", "json");
        accion.setFileFilter(filtroImagen);
        accion.setDialogTitle("Abrir archivo");
        if (accion.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {
            archivo = accion.getSelectedFile();
            /*System.out.println(accion.getSelectedFile().toString());*/
            try {
                /*Si existe el fichero*/
                if (archivo.exists()) {
                    return new FileReader(archivo);
                } else {
                    System.out.println("Fichero No Existe");
                    return null;
                }
            } catch (Exception ex) {
                /*Captura un posible error y le imprime en pantalla*/
                System.out.println(ex.getMessage());
                return null;
            }
        } else {
            System.out.println("No se selecciono fichero");
            return null;
        }
    }

    /**
     * Método para borrar todos los ficheros dentro de una
     * carpeta/archivo/directorio. OJO recordar este método solo sirve para
     * borrar los ficheros del directorio NO borra si posee carpetas con
     * contenido. Para esa funcionalidad existen otros métodos en esta clase
     * como borrar_Directorio.
     *
     * @param nombreCarpeta
     */
    public void vaciar_Directorio(String rutaDirectorio) {
        File carpeta = new File(rutaDirectorio);
        if (carpeta.listFiles().length > 0) {
            for (File fichero : carpeta.listFiles()) {
                fichero.delete();
            }
            System.out.println("Carpeta vaciada con éxito.");
        } else {
            System.out.println("El directorio " + rutaDirectorio + " esta vacía.");
        }
    }

    /**
     * Parte 1 para borrar una carpeta. Recordar que para borrar una carpeta hay
     * que borrar todos los archivos que este contenga.
     *
     * @param filepath
     * @throws IOException
     */
    public void borrar_Directorio(String filepath) throws IOException {
        vaciarTodoDirectorio(filepath);
        File carpeta = new File(filepath);
        carpeta.delete();
        //System.out.println("Carpeta borrada con éxito.");
    }

    /**
     * Parte 2 para borrar una carpeta. Este método se encarga de vaciar la
     * carpeta que se quería borrar en la Parte 1.
     *
     * @param filepath
     * @throws IOException
     */
    public void vaciarTodoDirectorio(String filepath) throws IOException {
        File f = new File(filepath); // define la ruta del archivo
        if (f.exists() && f.isDirectory()) {// determina si es un archivo o un directorio
            if (f.listFiles().length == 0) {// Si no hay archivos en el directorio, elimínelos directamente
                f.delete();
            } else {// Si lo hay, coloque el archivo en la matriz y determine si hay un directorio subordinado
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        vaciarTodoDirectorio(delFile[j].getAbsolutePath()); // Llame al método del de forma recursiva y obtenga la ruta del subdirectorio
                    }
                    delFile[j].delete(); // eliminar un archivo
                }
            }
        }
    }

    /**
     * Método para crear nuevos carpetas si es que no existen.
     * @param rutaCompleta
     * @throws IOException 
     */
    public void crearNuevoDirectorio(String rutaCompleta) throws IOException {
        Path path = Paths.get(rutaCompleta);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("New Directory created !   " + rutaCompleta);
        } else {

            System.out.println("Directory already exists");
        }
    }
}
