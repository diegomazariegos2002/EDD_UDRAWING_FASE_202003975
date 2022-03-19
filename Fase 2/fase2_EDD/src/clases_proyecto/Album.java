package clases_proyecto;

import clases_proyecto.Imagen;
import estructuras.linkedlist.LinkedList;

/**
 * Clase Album de mi proyecto
 * @author Melissa
 */
public class Album {
    String nombreAlbum;
    LinkedList<Imagen> listaImagenes = new LinkedList<>();

    /**
     * Constructor de mi clase Album
     * @param nombreAlbum 
     */
    public Album(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    //========================================MÉTODOS GET AND SET========================================
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public LinkedList<Imagen> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(LinkedList<Imagen> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }
}
