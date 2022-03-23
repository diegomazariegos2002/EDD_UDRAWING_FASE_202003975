package estructuras.arbolB;

/**
 * Nodo de árbol B utilizando genéricos.
 * @author Melissa
 */
public class NodoB<E extends Comparable<E>> {
     
    //Valores
    E valor;  //--> clave
    //Apuntadores
    NodoB<E> anterior, siguiente;
    Pagina hijoIzq, hijoDer;

    public NodoB(E valor) {
        this.valor = valor;
        this.anterior = this.siguiente=null;
        this.hijoIzq = this.hijoDer= null;
    }

    public NodoB(E valor, Pagina izquierda, Pagina derecha){
        this.valor=valor;
        this.anterior=this.siguiente=null;
        this.hijoIzq=izquierda;
        this.hijoDer=derecha;
    }
}
