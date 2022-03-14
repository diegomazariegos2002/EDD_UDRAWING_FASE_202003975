package clases_proyecto;

import estructuras.arbol_abb.AbbTree;
import estructuras.arbol_avl.AvlTree;
import estructuras.matriz_dispersa.Matriz;
import estructuras.linkedlist.LinkedList;

/**
 * Clase Cliente la utilizo para manipular información de los clientes.
 * @author Melissa
 */
public class Cliente {
    int DPI;
    String nombre;
    String password;
    AvlTree<Imagen> arbol_Imagenes;
    AbbTree<Capa> arbol_CapasGenerales;

    /**
     * Constructor de mi clase.
     * @param DPI
     * @param nombre
     * @param password 
     */
    public Cliente(int DPI, String nombre, String password) {
        this.DPI = DPI;
        this.nombre = nombre;
        this.password = password;
        this.arbol_Imagenes = new AvlTree<>();
        this.arbol_CapasGenerales = new AbbTree<>();
    }
    
    public int getDPI() {
        return DPI;
    }

    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AvlTree<Imagen> getArbol_Imagenes() {
        return arbol_Imagenes;
    }

    public void setArbol_Imagenes(AvlTree<Imagen> arbol_Imagenes) {
        this.arbol_Imagenes = arbol_Imagenes;
    }

    public AbbTree<Capa> getArbol_CapasGenerales() {
        return arbol_CapasGenerales;
    }

    public void setArbol_CapasGenerales(AbbTree<Capa> arbol_CapasGenerales) {
        this.arbol_CapasGenerales = arbol_CapasGenerales;
    }
}
