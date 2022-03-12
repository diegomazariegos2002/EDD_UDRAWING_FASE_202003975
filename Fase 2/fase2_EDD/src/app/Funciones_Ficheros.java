package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase para manejar las funciones de los ficheros.
 * @author Melissa
 */
public class Funciones_Ficheros {
    
    private JFileChooser accion = null;
    private File archivo = null;
    private Modulo_Admin admin;
    
    public FileReader archivo_Buscar(Modulo_Admin admin){
        accion = new JFileChooser("./");
        accion.setFileSelectionMode(0);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JSON", "json");
        accion.setFileFilter(filtroImagen);
        accion.setDialogTitle("Abrir archivo");
        if (accion.showOpenDialog(admin) == JFileChooser.APPROVE_OPTION) {
            archivo = accion.getSelectedFile();
            admin.jLabelNombreArchivoEntrada.setText(archivo.getName());
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
        }else{
            System.out.println("No se selecciono fichero");
            return null;
        }
    }
}
