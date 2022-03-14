package estructuras.arbol_abb;

/**
 * Clase diseñada para manejar todo lo relacionado con el árbol ABB.
 * @author Melissa
 * @param <E>
 */
public class AbbTree<E extends Comparable<E>>{
    
    AbbNode<E> root;
    
    public AbbTree(){
        this.root=null;
    }

    /*===================================================MÉTODOS FUNDAMENTALES================================================*/
    
    /**
     * Parte 1 del método recursivo de inserción de valors.
     * @param valor 
     */
    public void insertar(E valor){
        root=insertarNodo(root,valor);
    }

    /**
     * Parte 2 del método recursivo de inserción de valors.
     * @param root
     * @param valor
     * @return 
     */
    private AbbNode<E> insertarNodo(AbbNode<E> root, E valor){
        if (root==null) // si el árbol esta vacío.
            root=new AbbNode<E>(valor);
        else if (valor.compareTo(root.valor)< 0)    // si el valor del nodo nuevo es menor al de su raíz actual.
            root.hijoIzq=insertarNodo(root.hijoIzq,valor);
        else if(valor.compareTo(root.valor)> 0)     // si el valor del nodo nuevo es mayor al de su raíz actual.
            root.hijoDer=insertarNodo(root.hijoDer, valor);
        //TODO: Validar la insercion de claves repetidas.
        return root;
    }
    
    /**
     * Parte 1 del método recursivo de recorrido pre-orden del árbol.
     */
    public void preOrden(){
        preOrden(this.root);
    }

    /**
     * Parte 2 del método recursivo de recorrido pre-orden del árbol.
     * @param root 
     */
    private void preOrden(AbbNode<E> root){
        if(root != null){
            System.out.print(root.valor.toString());
            preOrden(root.hijoIzq);
            preOrden(root.hijoDer);
        }
    }
}
