package estructuras.arbol_avl;

/**
 * Clase AvlNode diseñada específicamente para trabajar con los nodos
 * de tipo Arbol.
 * @author Melissa
 * @param <E>
 */
public class AvlNode<E> {
    E value;
    AvlNode left;
    AvlNode right;
    int height;

    /**
     * Constructor de mi clase AvlNode
     * @param value
     */
    public AvlNode(E value) {
        this.value = value;
    }
}
