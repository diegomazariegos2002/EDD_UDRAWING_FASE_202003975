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
}
