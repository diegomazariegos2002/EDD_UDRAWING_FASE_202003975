package estructuras.arbol_avl;

import estructuras.Graphviz;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase AvlTree diseñada para crear un árbol de tipo de Avl
 * @author Melissa
 * @param <E>
 */
public class AvlTree<E extends Comparable<E>> {
    
    public AvlNode<E> root;
    
    /**
     * Constructor de mi clase AvlTree
     */
    public AvlTree() {
        this.root = null;
    }

    /**
     * Método de insertar un nodo nuevo (inserción).
     * cuando no existe raíz en el árbol.
     *
     * @param value
     */
    public void insert(E value) {
        AvlNode newNode = new AvlNode<E>(value);

        if (this.root == null) {
            this.root = newNode;
        } else {
            this.root = this.insert_node(this.root, newNode);
        }
    }

    private AvlNode<E> insert_node(AvlNode<E> actual_root, AvlNode<E> newNode) {
        if (actual_root != null) { // Recorrer hijos
            if (newNode.value.compareTo(actual_root.value) < 0) { //Cuando es menor
                actual_root.left = this.insert_node(actual_root.left, newNode); // se manda al nodo izquierdo

                //Validaciones de balanceo
                if (this.node_Heigth(actual_root.right) - this.node_Heigth(actual_root.left) == -2) {
                    // Posibles casos de rotación con -2 de balanceo
                    if (newNode.value.compareTo((E) actual_root.left.value) < 0) { // Si L-L ROTACIÓN SIMPLE DERECHA
                        actual_root = this.right_Rotation(actual_root);
                    } else { // Si L-R ROTACIÓN IZQUIERDA DERECHA
                        actual_root = this.left_Right_Rotation(actual_root);
                    }
                }
            } else if (newNode.value.compareTo(actual_root.value) > 0) {
                actual_root.right = this.insert_node(actual_root.right, newNode);

                //Validaciones de balanceo
                if (this.node_Heigth(actual_root.right) - this.node_Heigth(actual_root.left) == 2) {
                    // Posibles casos de rotación con +2 de balanceo
                    if (newNode.value.compareTo((E) actual_root.right.value) > 0) { // Si R-R ROTACIÓN SIMPLE IZQUIERDA
                        actual_root = this.left_Rotation(actual_root);
                    } else { //Si R-L ROTACIÓN DERECHA IZQUIERDA
                        actual_root = this.right_left_Rotation(actual_root);
                    }
                }
            }else {
                    System.out.println("No se puede insertar el dato porque ya existe.");
                }
            actual_root.height = this.max_Height(this.node_Heigth(actual_root.right), this.node_Heigth(actual_root.left)) + 1;
            return actual_root;
        }else{
            actual_root = newNode;
            return actual_root;
        }
    }

    /**
     * Retornar altura de los nodos.
     * @param nodo
     * @return
     */
    public int node_Heigth(AvlNode<E> nodo){
        if (nodo != null){
            return nodo.height;
        }else{
            return -1;
        }
    }

    /**
     * Retorna la altura mayor.
     * @param h1
     * @param h2
     * @return
     */
    public int max_Height(int h1, int h2){
        if(h2 >= h1){
            return h2;
        }else{
            return h1;
        }
    }

    /**
     * Método de rotación simple izquierda
     */
    public AvlNode<E> right_Rotation(AvlNode<E> node){

        AvlNode<E> aux = node.left;
        node.left = aux.right;
        aux.right = node;
        node.height = this.max_Height(this.node_Heigth(node.right), this.node_Heigth(node.left)) + 1;
        aux.height = this.max_Height(node.height, this.node_Heigth(node.left))+1;
        return aux;
    }

    /**
     * Método de rotación simple izquierda
     * @param node
     * @return
     */
    public AvlNode<E> left_Rotation(AvlNode<E> node){
        AvlNode<E> aux = node.right;
        node.right = aux.left;
        aux.left = node;
        node.height = this.max_Height(this.node_Heigth(node.left), this.node_Heigth(node.right))+1;
        aux.height = this.max_Height(node.height, this.node_Heigth(node.right));
        return aux;
    }

    /**
     * Método de rotación doble izq-der
     * @param node
     * @return
     */
    public AvlNode<E> left_Right_Rotation(AvlNode<E> node){
        node.left = this.left_Rotation(node.right);
        AvlNode<E> aux = this.right_Rotation(node);
        return aux;
    }

    /**
     * Método de rotación de doble der-izq
     * @param node
     * @return
     */
    public AvlNode<E> right_left_Rotation(AvlNode<E> node){
        node.right = this. right_Rotation(node.right);
        AvlNode<E> aux = this.left_Rotation(node);
        return aux;
    }

    //===============================RECORRIDOS===================================
    /**
     * Parte 1 del método recursivo de recorrido pre-orden del árbol.
     */
    public void preOrden() {
        preOrden(this.root);
    }
    
    /**
     * Parte 2 del método recursivo de recorrido pre-orden del árbol.
     *
     * @param root
     */
    private void preOrden(AvlNode<E> actual_root){
        if(actual_root != null){
            System.out.println(actual_root.value);
            this.preOrden(actual_root.left);
            this.preOrden(actual_root.right);
        }
    }

    
    /*==========================================MÉTODOS DE GRAPHVIZ==========================================*/

    /**
     * Parte 1 para graficar el árbol por medio de graphviz. Cuerpo en general.
     *
     * @param nombreFichero
     * @param rutaDot
     * @param rutaPng
     */
    public void crearFicheroDot_Arbol(String nombreFichero, String rutaDot, String rutaPng) {
        //Parte del String o texto que va a llevar el fichero 
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();

        dot.append("digraph binaryTree { \n");
        dot.append("node[shape = circle]; \n");
        AvlNode actual = this.root;

        String conexionesNodos = getConexionNodos_PreOrden("", actual);
        dot.append(conexionesNodos);

        dot.append("}");

        FileWriter fichero = null;
        PrintWriter pw = null;
        //Parte de la creación de un fichero
        try {
            fichero = new FileWriter(rutaDot + "/" + nombreFichero + ".dot");
            pw = new PrintWriter(fichero);

            pw.println(dot);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        new Graphviz().dibujar("dot", "-Tpng", rutaDot + "/" + nombreFichero + ".dot", rutaPng + "/" + nombreFichero + ".png");
    }

    /**
     * Parte 2 para graficar el árbol por medio de graphviz. Método para
     * retornar las CONEXIONES ENTRE NODOS para el .Dot
     *
     * @param entrada
     * @param actual
     * @return
     */
    private String getConexionNodos_PreOrden(String entrada, AvlNode actual) {
        String cadena = "";
        if (actual != null) {
            /**
             * Estos if de aquí es para realizar las conexiones entre nodos hijo
             * y nodo padre en el Dot.
             */
            cadena += "\nNodo" + actual.hashCode() + "[label = \"" + actual.value.toString() + "\"];\n";

            if (actual.left != null) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", actual.hashCode(), actual.left.hashCode());
            }
            if (actual.right != null) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", actual.hashCode(), actual.right.hashCode());
            }

            cadena += getConexionNodos_PreOrden(cadena, actual.left);
            cadena += getConexionNodos_PreOrden(cadena, actual.right);
        }
        return cadena;
    }
    
    
}
