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

    /**
     * Constructor de mi clase NodoMatriz_Posicion
     * @param fila
     * @param columna
     * @param valor 
     */
    public NodoMatriz_Posicion(int fila, int columna, E valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }
    
    
}
