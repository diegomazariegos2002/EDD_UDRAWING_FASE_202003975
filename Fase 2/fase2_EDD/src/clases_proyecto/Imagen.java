package clases_proyecto;

import estructuras.arbol_abb.AbbTree;
import estructuras.matriz_dispersa.Matriz;

/**
 * Clase Imagen de mi proyecto 
 * @author Melissa
 */
public class Imagen implements Comparable<Imagen>{

    private int id_Imagen;
    private AbbTree<Capa> capasImagen;
    private Matriz<Capa> capasUnidas;

    /**
     * Método constructor de mi clase Imagen.
     * @param id_Imagen 
     */
    public Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
        this.capasImagen = new AbbTree<>();
        this.capasUnidas = new Matriz<>();
    }  
    
    @Override
    public int compareTo(Imagen imagenAComparar) {
        return this.id_Imagen - imagenAComparar.id_Imagen;
    }
    
    /*==============================================UNIÓN DE LAS CAPAS DE LA IMAGEN==============================================*/
    
    
    /*==============================================MÉTODOS GET AND SET==============================================*/ 
    /**
     * @return the id_Imagen
     */
    public int getId_Imagen() {
        return id_Imagen;
    }

    /**
     * @param id_Imagen the id_Imagen to set
     */
    public void setId_Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    /**
     * @return the capasImagen
     */
    public AbbTree<Capa> getCapasImagen() {
        return capasImagen;
    }

    /**
     * @param capasImagen the capasImagen to set
     */
    public void setCapasImagen(AbbTree<Capa> capasImagen) {
        this.capasImagen = capasImagen;
    }

    /**
     * @return the capasUnidas
     */
    public Matriz<Capa> getCapasUnidas() {
        return capasUnidas;
    }

    /**
     * @param capasUnidas the capasUnidas to set
     */
    public void setCapasUnidas(Matriz<Capa> capasUnidas) {
        this.capasUnidas = capasUnidas;
    }
}
