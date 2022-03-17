package estructuras.arbol_abb;

/**
 * Clase para manejar los nodos del árbol ABB.
 * @author Melissa
 */
public class AbbNode<E> {

    E valor;
    AbbNode<E> hijoIzq, hijoDer;

    /**
     * Constructor de mi clase AbbNode.
     * @param valor 
     */
    public AbbNode(E valor) {
        this.valor = valor;
        this.hijoIzq = this.hijoDer = null;
    }
    
    /*===========================================MÉTODOS GET AND SET===========================================*/
    public E getValor() {
        return valor;
    }

    public void setValor(E valor) {
        this.valor = valor;
    }
    
    public AbbNode<E> getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(AbbNode<E> hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public AbbNode<E> getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(AbbNode<E> hijoDer) {
        this.hijoDer = hijoDer;
    }
}
