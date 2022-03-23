package estructuras.arbolB;

/**
 *
 * @author Melissa
 */
public class Pagina{
    boolean hoja;//identificar si es una hoja
    int contador;//identificar la cantidad de elementos que tiene la pagina
    NodoB primero;

    public Pagina() {
        this.primero = null;
        this.hoja = true;
        this.contador = 0;
    }

    public void insertarEnPagina(NodoB nuevo) {
        if (primero == null) {
            //primero en la lista
            primero = nuevo;
            contador++;
        } else {
            //recorrer e insertarEnPagina
            NodoB aux = primero;
            while (aux != null) {
                if (aux.valor.compareTo(nuevo.valor) == 0 ) {//------------->ya existe en el arbol
                    System.out.println("El ID " + nuevo.valor + " ya existe");
                    break;
                } else {
                    if (aux.valor.compareTo(nuevo.valor) > 0) {
                        if (aux == primero) {//------------->insertarEnPagina al inicio
                            aux.anterior = nuevo;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.hijoIzq = nuevo.hijoDer;
                            nuevo.hijoDer = null;

                            primero = nuevo;
                            contador++;
                            break;
                        } else {//------------->insertarEnPagina en medio;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.hijoIzq = nuevo.hijoDer;
                            nuevo.hijoDer = null;

                            nuevo.anterior = aux.anterior;
                            aux.anterior.siguiente = nuevo;
                            aux.anterior = nuevo;
                            contador++;
                            break;
                        }
                    } else if (aux.siguiente == null) {//------------->insertarEnPagina al final
                        aux.siguiente = nuevo;
                        nuevo.anterior = aux;
                        aux.hijoDer = null;
                        contador++;
                        break;
                    }
                }
                aux = aux.siguiente;
            }

        }
    }
    public void imprimir(){
        NodoB aux=this.primero;
        while(aux.siguiente!=null){
            System.out.println(aux.valor + ", ");
            aux=aux.siguiente;
        }
    }
}
