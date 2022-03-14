package estructuras.linkedlist;

/**
 *
 * @author Melissa
 */
public class LinkedList_Node<E> {

    private E valor;
    public LinkedList_Node siguiente;
    public LinkedList_Node anterior;

    //Constructor por defecto en una lista enlazada
    public LinkedList_Node(E object1) {
        this.valor = object1;
    }
    
    /**
     * @return the valor
     */
    public E getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(E valor) {
        this.valor = valor;
    }
}
