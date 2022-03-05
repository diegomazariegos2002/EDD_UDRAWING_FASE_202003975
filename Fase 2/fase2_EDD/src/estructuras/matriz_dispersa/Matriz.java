package estructuras.matriz_dispersa;

/**
 * Clase Matriz que la utilizo principalmente para generar mi matriz dispera,
 * esta hace uso de las clases nodo en su misma matriz.
 *
 * @author Melissa
 * @param <E>
 */
public class Matriz<E> {

    ListaEncabezado eFilas;
    ListaEncabezado eColumnas;

    /**
     * Constructor de mi clase Matriz
     *
     * @param eFilas
     * @param eColumnas
     */
    public Matriz() {
        this.eFilas = new ListaEncabezado();
        this.eColumnas = new ListaEncabezado();
    }

    //==============================================Métodos Fundamentales==================================
    //---------------------------------------Métodos de inserción------------------------------------
    /**
     * Método para insertar en una determinada posición x,y un nodo nuevo.
     *
     * @param fila
     * @param columna
     * @param valor
     */
    public void insertar(int fila, int columna, E valor) {
        NodoMatriz_Posicion<E> nuevo = new NodoMatriz_Posicion<E>(fila, columna, valor);
        //Parte de las filas
        NodoMatriz_Encabezado eFila = eFilas.getEncabezado(fila);
        if (eFila == null) {
            eFila = new NodoMatriz_Encabezado(fila);
            eFila.accesoNodo = nuevo;
            eFilas.setEncabezado(eFila);
        } else {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            while (actual.derecha != null) {
                if (nuevo.columna < actual.derecha.columna) {
                    nuevo.derecha = actual.derecha;
                    actual.derecha.izquierda = nuevo;
                    nuevo.izquierda = actual;
                    actual.derecha = nuevo;
                }
                actual = actual.derecha;
            }
            if (actual.derecha == null) {
                actual.derecha = nuevo;
                nuevo.izquierda = actual;
            }
        }
        //Parte de las columnas
        NodoMatriz_Encabezado eColumna = eColumnas.getEncabezado(columna);
        if (eColumna == null) {
            eColumna = new NodoMatriz_Encabezado(columna);
            eColumna.accesoNodo = nuevo;
            eColumnas.setEncabezado(eColumna);
        } else {
            NodoMatriz_Posicion actual = eColumna.accesoNodo;
            while (actual.abajo != null) {
                if (nuevo.fila < actual.abajo.fila) {
                    nuevo.abajo = actual.abajo;
                    actual.abajo.arriba = nuevo;
                    nuevo.arriba = actual;
                    actual.abajo = nuevo;
                    break;
                }
                actual = actual.abajo;
            }
            if (actual.abajo == null) {
                actual.abajo = nuevo;
                nuevo.arriba = actual;
            }
        }
    }

    /**
     * Método para mostrar la matriz por medio de columnas
     */
    public void mostrarMatrizColumnas() {
        String matrizGrafica = "";
        NodoMatriz_Encabezado eColumna = eColumnas.primero;
        System.out.println("=============================Matriz por COLUMNAS=============================\n");
        while (eColumna != null) {
            NodoMatriz_Posicion actual = eColumna.accesoNodo;
            while (actual != null) {
                matrizGrafica = matrizGrafica + "[" + actual.fila + "][" + actual.columna + "](" + actual.valor + ")";
                matrizGrafica = matrizGrafica + "   ";
                actual = actual.abajo;
            }
            matrizGrafica = matrizGrafica + "\n";
            eColumna = eColumna.siguiente;
        }
        System.out.println(matrizGrafica);
    }

    public void mostrarMatrizFilas() {
        String matrizGrafica = "";
        NodoMatriz_Encabezado eFila = eFilas.primero;
        System.out.println("=============================Matriz por FILAS=============================\n");
        while (eFila != null) {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            while (actual != null) {
                matrizGrafica = matrizGrafica + "[" + actual.fila + "][" + actual.columna + "](" + actual.valor + ")";
                matrizGrafica = matrizGrafica + "   ";
                actual = actual.derecha;
            }
            matrizGrafica = matrizGrafica + "\n";
            eFila = eFila.siguiente;
        }
        System.out.println(matrizGrafica);
    }
}
