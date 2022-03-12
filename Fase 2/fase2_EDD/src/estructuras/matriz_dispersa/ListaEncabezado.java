package estructuras.matriz_dispersa;

/**
 *
 * @author Melissa
 */
public class ListaEncabezado {

    /*Puntero al primer nodo*/
    public NodoMatriz_Encabezado primero;

    // Estas variables de aquí en adelante solo son para el debugger
    /*Puntero al último nodo*/
    public NodoMatriz_Encabezado ultimo;
    public int longitud = 0;

    /**
     * Constructor de mi clase ListaEncabezado
     *
     * @param primero
     */
    public ListaEncabezado() {
        this.primero = null;
    }

    /**
     * Método para ingresar un nuevo nodo encabezado a mi lista.
     *
     * @param nuevo
     */
    public void setEncabezado(NodoMatriz_Encabezado nuevo) {
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else if (nuevo.id < primero.id) {
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
            /*
                CASO 1 sumarle +1 a todos los nodos excepto al nuevo.
                Esto lo hago mediante punteros para ahorrarme ciclos. [nuevo.siguiente.posicion = nuevo.posicion + 1;]
                Recordar esta idea de antes era erronea ya que en Java solo se puede pasar valor por parametro y no por referencia.
                Por lo que al final tuve que recurrir a un ciclo.
             */
            NodoMatriz_Encabezado actual = nuevo.siguiente;
            while (actual != null) {
                actual.posicion++;
                actual = actual.siguiente;
            }
        } else {
            NodoMatriz_Encabezado actual = primero;
            while (actual.siguiente != null) {
                nuevo.posicion = nuevo.posicion + 1;
                if (nuevo.id < actual.siguiente.id) {
                    nuevo.siguiente = actual.siguiente;
                    actual.siguiente.anterior = nuevo;
                    nuevo.anterior = actual;
                    actual.siguiente = nuevo;
                    /*
                        CASO 2 sumarle +1 a todos los nodos a partir de la posición actual del nuevo.
                        Paso 1) que el valor posición del nuevo apunte a su anterior.
                     */
                    nuevo.posicion = nuevo.anterior.posicion + 1;
                    //  Pasa 2) relacionar el siguiente con la posición de mi nodo nuevo.
                    NodoMatriz_Encabezado actual2 = nuevo.siguiente;
                    while (actual2 != null) {
                        actual2.posicion++;
                        actual2 = actual2.siguiente;
                    }
                    break;
                }
                actual = actual.siguiente;
            }
            if (actual.siguiente == null) {
                actual.siguiente = nuevo;
                nuevo.anterior = actual;
                // CASO 3 como es el último entonces solo apunto su posición a su anterior.
                nuevo.posicion = nuevo.anterior.posicion + 1;
                ultimo = nuevo; // esto del último es simplemente para el debugger.
            }
        }
        longitud++;
    }

    /**
     * Método para obtener cierto nodo de mi lista.
     *
     * @param id
     * @return
     */
    public NodoMatriz_Encabezado getEncabezado(int id) {
        NodoMatriz_Encabezado actual = primero;
        while (actual != null) {
            if (actual.id == id) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
