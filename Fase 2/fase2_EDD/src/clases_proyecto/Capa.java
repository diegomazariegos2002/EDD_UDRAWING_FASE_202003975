package clases_proyecto;

import estructuras.matriz_dispersa.Matriz;

/**
 * Clase capas que básicamente almacena su id y su respectiva matriz.
 * @author Melissa
 */
public class Capa implements Comparable<Capa>{
    private int id_Capa;
    /*
        aquí le puse String pero básicamente el E valor de la matriz no lo utilizo
        porque en este caso solo necesito una matriz especifica y utilizo el atributo 
        nodoColor de una vez para ahorrarme casteos futuros.
    */
    private Matriz<String> matriz_Capa = new Matriz<>();
    
    @Override
    public String toString() {
        return "Capa " + id_Capa + "";
    }
    
    /**
     * Método abstracto que se utiliza para comparar dos capas.
     * Recordar que esto lo hago mas por temas de los árboles binarios.
     * @param capaAComparar
     * @return 
     */
    @Override
    public int compareTo(Capa capaAComparar) {
        return this.id_Capa - capaAComparar.id_Capa;
        
    }
    
    /*===================================MÉTODO GET AND SET=========================================*/
    
    /**
     * Constructor de mi clase Capa
     * @param id_Capa 
     */
    public Capa(int id_Capa) {
        this.id_Capa = id_Capa;
    }    
    
    /**
     * @return the id_Capa
     */
    public int getId_Capa() {
        return id_Capa;
    }

    /**
     * @param id_Capa the id_Capa to set
     */
    public void setId_Capa(int id_Capa) {
        this.id_Capa = id_Capa;
    }

    /**
     * @return the matriz_Capa
     */
    public Matriz<String> getMatriz_Capa() {
        return matriz_Capa;
    }

    /**
     * @param matriz_Capa the matriz_Capa to set
     */
    public void setMatriz_Capa(Matriz<String> matriz_Capa) {
        this.matriz_Capa = matriz_Capa;
    }
}
