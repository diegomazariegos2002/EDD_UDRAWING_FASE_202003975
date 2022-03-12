package estructuras.matriz_dispersa;

/**
 * Clase NodoMatriz_Encabezado que sirve básicamente a todos los 
 * nodos que sirven como encabezados en las filas y columnas 
 * de mi matriz.
 * @author Melissa
 */
public class NodoMatriz_Encabezado {
    public int id;
    public int posicion; //variables que utilizo para posicionar el nodoPosicion en neato.
    public NodoMatriz_Encabezado siguiente, anterior;
    public NodoMatriz_Posicion accesoNodo;

    /**
     * Constructor para mi clase NodoMatriz_Encabezado.
     * @param id 
     */
    public NodoMatriz_Encabezado(int id) {
        this.id = id;
        this.posicion = 1;
    }
}
