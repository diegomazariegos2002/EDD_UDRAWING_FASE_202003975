package estructuras.matriz_dispersa;

/**
 * Clase NodoMatriz_Posicion es una clase destinada para todos aquellos
 * nodos que guardan datos en la matriz.
 * @author Melissa
 * @param <E>
 */
public class NodoMatriz_Posicion<E> {
    public int fila, columna;
    public E valor;
    public NodoMatriz_Posicion derecha, izquierda, arriba, abajo;
    public NodoMatriz_Encabezado accesoFila, accesoColumna;
    /*
        Variable propia del proyecto, en una matriz dispersa normal no tendría que agregarla
    */
    String colorNodo;

    /**
     * Constructor de mi clase NodoMatriz_Posicion
     * @param fila
     * @param columna
     * @param valor 
     */
    public NodoMatriz_Posicion(int fila, int columna, E valor, String colorNodo) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.colorNodo = colorNodo;
    }
    
    
}
