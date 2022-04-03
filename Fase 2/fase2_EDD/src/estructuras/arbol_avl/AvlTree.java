package estructuras.arbol_avl;

import clases_proyecto.Imagen;
import estructuras.Graphviz;
import estructuras.arbol_abb.AbbNode;
import estructuras.linkedlist.LinkedList;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase AvlTree diseñada para crear un árbol de tipo de Avl
 *
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

    //========================================MÉTODOS PROPIOS DEL PROYECTO========================================
     /**
     * Parte 1 para graficar el árbol por medio de graphviz. Cuerpo en general.
     *
     * @param nombreFichero
     * @param rutaDot
     * @param rutaPng
     */
    public void crearFicheroDot_Arbol(String nombreFichero, String rutaDot, String rutaPng, Imagen imagenSolicitada) {
        //Parte del String o texto que va a llevar el fichero 
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();

        dot.append("digraph binaryTree { \n");
        dot.append("node[shape = circle]; \n");
        AvlNode actual = this.root;

        String conexionesNodos = getConexionNodos_PreOrdenConImagen("", actual, imagenSolicitada);
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
    private String getConexionNodos_PreOrdenConImagen(String entrada, AvlNode actual, Imagen imagenSolicitada) {
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

            cadena += getConexionNodos_PreOrdenConImagen(cadena, actual.left, imagenSolicitada);
            cadena += getConexionNodos_PreOrdenConImagen(cadena, actual.right, imagenSolicitada);
            if (imagenSolicitada == actual.value) {
                cadena += getConexionNodos_PreOrdenCapas(entrada, imagenSolicitada.getCapasImagen().getRoot(), actual);
            }
        }
        return cadena;
    }
    
    
    private String getConexionNodos_PreOrdenCapas(String entrada, AbbNode actual, AvlNode nodoImagenSolicitada) {
        String cadena = "";
        if (actual != null) {
            /**
             * Estos if de aquí es para realizar las conexiones entre nodos hijo
             * y nodo padre en el Dot.
             */
            cadena += "\nNodo" + actual.hashCode() + "[label = \"" + actual.getValor().toString() + "\"];\n";

            if (actual == ((Imagen)nodoImagenSolicitada.value).getCapasImagen().getRoot()) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", nodoImagenSolicitada.hashCode(), actual.hashCode());
            }
            
            if (actual.getHijoIzq() != null) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", actual.hashCode(), actual.getHijoIzq().hashCode());
            }
            if (actual.getHijoDer() != null) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", actual.hashCode(), actual.getHijoDer().hashCode());
            }

            cadena += getConexionNodos_PreOrdenCapas(cadena, actual.getHijoIzq(), nodoImagenSolicitada);
            cadena += getConexionNodos_PreOrdenCapas(cadena, actual.getHijoDer(), nodoImagenSolicitada);
        }
        return cadena;
    }
    
    //=========================================MÉTODOS FUNDAMENTALES=========================================
    
    /**
     * Método de insertar un nodo nuevo (inserción). cuando no existe raíz en el
     * árbol.
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
            if (newNode.value.compareTo(actual_root.value) < 0) { // Cuando es menor
                actual_root.left = this.insert_node(actual_root.left, newNode); // Se manda al nodo izquierdo

                //Validaciones de balanceo
                if (this.node_Heigth(actual_root.right) - this.node_Heigth(actual_root.left) == -2) {
                    // Posibles casos de rotación con -2 de balanceo
                    if (newNode.value.compareTo((E) actual_root.left.value) < 0) { // Si L-L ROTACIÓN SIMPLE DERECHA
                        actual_root = this.right_Rotation(actual_root);
                    } else { // Si L-R ROTACIÓN IZQUIERDA DERECHA
                        actual_root = this.left_Right_Rotation(actual_root);
                    }
                }
            } else if (newNode.value.compareTo(actual_root.value) > 0) { // Cuando es mayor
                actual_root.right = this.insert_node(actual_root.right, newNode); // Se manda al nodo derecho

                //Validaciones de balanceo
                if (this.node_Heigth(actual_root.right) - this.node_Heigth(actual_root.left) == 2) {
                    // Posibles casos de rotación con +2 de balanceo
                    if (newNode.value.compareTo((E) actual_root.right.value) > 0) { // Si R-R ROTACIÓN SIMPLE IZQUIERDA
                        actual_root = this.left_Rotation(actual_root);
                    } else { //Si R-L ROTACIÓN DERECHA IZQUIERDA
                        actual_root = this.right_left_Rotation(actual_root);
                    }
                }
            } else {
                System.out.println("No se puede insertar el dato porque ya existe.");
            }
            actual_root.height = this.max_Height(this.node_Heigth(actual_root.right), this.node_Heigth(actual_root.left)) + 1;
            return actual_root;
        } else {
            actual_root = newNode;
            return actual_root;
        }
    }

    /**
     * Retornar altura de los nodos.
     *
     * @param nodo
     * @return
     */
    public int node_Heigth(AvlNode<E> nodo) {
        if (nodo != null) {
            return nodo.height;
        } else {
            return -1;
        }
    }

    /**
     * Retorna la altura mayor.
     *
     * @param h1
     * @param h2
     * @return
     */
    public int max_Height(int h1, int h2) {
        if (h2 >= h1) {
            return h2;
        } else {
            return h1;
        }
    }

    /**
     * Método de rotación simple izquierda
     */
    public AvlNode<E> right_Rotation(AvlNode<E> node) {
        AvlNode<E> aux = node.left;
        node.left = aux.right;
        aux.right = node;
        node.height = this.max_Height(this.node_Heigth(node.right), this.node_Heigth(node.left)) + 1;
        aux.height = this.max_Height(node.height, this.node_Heigth(node.left)) + 1;
        return aux;
    }

    /**
     * Método de rotación simple izquierda
     *
     * @param node
     * @return
     */
    public AvlNode<E> left_Rotation(AvlNode<E> node) {
        AvlNode<E> aux = node.right;
        node.right = aux.left;
        aux.left = node;
        node.height = this.max_Height(this.node_Heigth(node.left), this.node_Heigth(node.right)) + 1;
        aux.height = this.max_Height(node.height, this.node_Heigth(node.right));
        return aux;
    }

    /**
     * Método de rotación doble izq-der
     *
     * @param node
     * @return
     */
    public AvlNode<E> left_Right_Rotation(AvlNode<E> node) {
        node.left = this.left_Rotation(node.left);
        AvlNode<E> aux = this.right_Rotation(node);
        return aux;
    }

    /**
     * Método de rotación de doble der-izq
     *
     * @param node
     * @return
     */
    public AvlNode<E> right_left_Rotation(AvlNode<E> node) {
        node.right = this.right_Rotation(node.right);
        AvlNode<E> aux = this.left_Rotation(node);
        return aux;
    }

    /**
     * Parte 1 del método recursivo de busqueda en pre-orden del árbol. Este
     * método busca por medio del objeto con el cual se comparo. Por eso se
     * utiliza el método .ToCompare de cada objeto valor.
     *
     * @param valor
     * @return
     */
    public E getValue(E valor) {
        if (arbolVacio() != true) {
            return getValueNode(this.root, valor);
        } else {
            return null;
        }
    }

    /**
     * Parte 2 del método recursivo de busqueda en pre-orden del árbol.
     *
     * @param actualRoot
     * @param valor
     * @return
     */
    private E getValueNode(AvlNode<E> actualRoot, E valor) {
        if (actualRoot != null) {
            if (valor.compareTo(actualRoot.value) == 0) {
                return actualRoot.value;
            } else if (valor.compareTo(actualRoot.value) < 0) {
                return getValueNode((AvlNode<E>) actualRoot.left, valor);
            } else {
                return getValueNode((AvlNode<E>) actualRoot.right, valor);
            }
        } else {
            return null;
        }
    }

    public int getLength() {
        LinkedList<Integer> cont = new LinkedList<>();
        cont.insertElement_AtEnding(0);
        getLength(root, cont);
        return (int) cont.getCabezaLista().getValor();
    }

    private void getLength(AvlNode<E> nodoActual, LinkedList<Integer> contador) {
        if (nodoActual != null) {
            System.out.print(nodoActual.value.toString());
            contador.getCabezaLista().setValor((int) contador.getCabezaLista().getValor() + 1);
            getLength(nodoActual.left, contador);
            getLength(nodoActual.right, contador);
        }
    }

    /**
     * Método que sirve para verificar si la lista esta vacía.
     *
     * @return
     */
    public boolean arbolVacio() {
        return (this.root == null);
    }

    //=================================VOLVER LISTA EL ÁRBOL===================================
    public LinkedList<E> getLinkedList_PreOrden() {
        LinkedList<E> lista = new LinkedList<>();
        return getLinkedList_PreOrden(root, lista);
    }

    private LinkedList<E> getLinkedList_PreOrden(AvlNode<E> nodoActual, LinkedList<E> lista) {
        if (nodoActual != null) {
            System.out.print(nodoActual.value.toString());
            lista.insertElement_AtEnding(nodoActual.value);
            getLinkedList_PreOrden(nodoActual.left, lista);
            getLinkedList_PreOrden(nodoActual.right, lista);
        }
        return lista;
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
    private void preOrden(AvlNode<E> actual_root) {
        if (actual_root != null) {
            System.out.println(actual_root.value);
            this.preOrden(actual_root.left);
            this.preOrden(actual_root.right);
        }
    }

    /**
     * Parte 1 Método para realizar el recorrido por amplitud del árbol avl.
     * Recordar que este método es iterativo a diferencia de los otros
     * recorridos y hace uso de una cola para ir mostrando los nodos por niveles
     * de izquierda a derecha.
     */
    public void recorridoAmplitud() {
        recorridoAmplitud(this.root);
    }

    /**
     * Parte 2 Método para realizar el recorrido por amplitud del árbol avl.
     * Recordar que este método es iterativo a diferencia de los otros
     * recorridos y hace uso de una cola para ir mostrando los nodos por niveles
     * de izquierda a derecha.
     */
    private void recorridoAmplitud(AvlNode<E> actual_Root) {
        if (this.root != null) {
            LinkedList<AvlNode> colaNodos = new LinkedList<>();
            colaNodos.insertElement_AtEnding(root);
            AvlNode aux = null;
            while (colaNodos.getlength() != 0) {
                aux = colaNodos.extractElement_AtBeggining().getValor();
                System.out.println(aux.value);
                if (aux.left != null) {
                    colaNodos.insertElement_AtEnding(aux.left);
                }
                if (aux.right != null) {
                    colaNodos.insertElement_AtEnding(aux.right);
                }
            }
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

    /*================================================MÉTODOS GET AND SET================================================*/
    public AvlNode<E> getRoot() {
        return root;
    }

    public void setRoot(AvlNode<E> root) {
        this.root = root;
    }
}
