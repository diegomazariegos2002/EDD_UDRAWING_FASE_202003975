package estructuras.matriz_dispersa;

/**
 * 
 * @author Melissa
 */
public class ListaEncabezado {
    /*puntero al primer nodo*/
    public NodoMatriz_Encabezado primero;
    /**
     * Constructor de mi clase ListaEncabezado
     * @param primero 
     */
    public ListaEncabezado() {
        this.primero = null;
    }
    
    /**
     * Método para ingresar un nuevo nodo encabezado a mi lista.
     * @param nuevo 
     */
    public void setEncabezado(NodoMatriz_Encabezado nuevo){
        if (primero == null){
               primero = nuevo;
        }else if (nuevo.id < primero.id){
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }else{
            NodoMatriz_Encabezado actual = primero;
            while (actual.siguiente != null) {
                if (nuevo.id < actual.siguiente.id) {
                    nuevo.siguiente = actual.siguiente;
                    actual.siguiente.anterior = nuevo;
                    nuevo.anterior = actual;
                    actual.siguiente = nuevo;
                    break;
                }
                actual = actual.siguiente;
            }
            if (actual.siguiente == null) {
                actual.siguiente = nuevo;
                nuevo.anterior = actual;
            }
        }
    }
    
    /**
     * Método para obtener cierto nodo de mi lista.
     * @param id
     * @return 
     */
    public NodoMatriz_Encabezado getEncabezado(int id){
        NodoMatriz_Encabezado actual = primero;
        while (actual != null){
            if (actual.id == id) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
