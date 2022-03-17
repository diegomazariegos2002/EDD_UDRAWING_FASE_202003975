package estructuras.arbol_abb;

import java.io.FileWriter;
import java.io.PrintWriter;
import estructuras.Graphviz;

/**
 * Clase diseñada para manejar todo lo relacionado con el árbol ABB.
 *
 * @author Melissa
 * @param <E>
 */
public class AbbTree<E extends Comparable<E>> {

    AbbNode<E> root;

    public AbbTree() {
        this.root = null;
    }

    /*===================================================MÉTODOS FUNDAMENTALES================================================*/
    /**
     * Parte 1 del método recursivo de inserción de valors.
     *
     * @param valor
     */
    public void insertar(E valor) {
        root = insertarNodo(root, valor);
    }

    /**
     * Parte 2 del método recursivo de inserción de valors.
     *
     * @param root
     * @param valor
     * @return
     */
    private AbbNode<E> insertarNodo(AbbNode<E> root, E valor) {
        if (root == null) // Si llegas al tope entonces se crea el nuevo nodo en el árbol.
        {
            root = new AbbNode<E>(valor);
        } else if (valor.compareTo(root.valor) < 0) // si el valor del nodo nuevo es menor al de su raíz actual.
        {
            root.hijoIzq = insertarNodo(root.hijoIzq, valor);
        } else if (valor.compareTo(root.valor) > 0) // si el valor del nodo nuevo es mayor al de su raíz actual.
        {
            root.hijoDer = insertarNodo(root.hijoDer, valor);
        }
        //TODO: Validar la insercion de claves repetidas.
        return root;
    }

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
    private void preOrden(AbbNode<E> root) {
        if (root != null) {
            System.out.print(root.valor.toString());
            preOrden(root.hijoIzq);
            preOrden(root.hijoDer);
        }
    }
    
    /**
     * Parte 1 del método recursivo de busqueda en pre-orden del árbol.
     * Este método busca por medio del objeto con el cual se comparo.
     * Por eso se utiliza el método .ToCompare de cada objeto valor.
     * @param valor
     * @return 
     */
    public E getValue(E valor){
        if(arbolVacio() != true){
            return getValueNode(this.root, valor);
        }else{
            return null;
        }
    }
    
    /**
     * Parte 2 del método recursivo de busqueda en pre-orden del árbol.
     * @param actualRoot
     * @param valor
     * @return 
     */
    private E getValueNode(AbbNode<E> actualRoot, E valor){
        if(actualRoot != null){
            if (valor.compareTo(actualRoot.valor) == 0) {
                return actualRoot.valor;
            }else if(valor.compareTo(actualRoot.valor) < 0){
                return getValueNode(actualRoot.hijoIzq, valor);
            }else{
                return getValueNode(actualRoot.hijoDer, valor);
            }
        }else{
            return null;
        }
    }
    
    /**
     * Método que sirve para verificar si la lista esta vacía.
     * @return 
     */
    public boolean arbolVacio(){
        return (this.root == null);
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
        AbbNode actual = this.root;

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
    private String getConexionNodos_PreOrden(String entrada, AbbNode actual) {
        String cadena = "";
        if (actual != null) {
            /**
             * Estos if de aquí es para realizar las conexiones entre nodos hijo
             * y nodo padre en el Dot.
             */
            cadena += "\nNodo" + actual.hashCode() + "[label = \"" + actual.valor.toString() + "\"];\n";

            if (actual.hijoIzq != null) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", actual.hashCode(), actual.hijoIzq.hashCode());
            }
            if (actual.hijoDer != null) {
                cadena += String.format("\nNodo%d -> Nodo%d; \n", actual.hashCode(), actual.hijoDer.hashCode());
            }

            cadena += getConexionNodos_PreOrden(cadena, actual.hijoIzq);
            cadena += getConexionNodos_PreOrden(cadena, actual.hijoDer);
        }
        return cadena;
    }
    
    /*===========================================MÉTODOS GET AND SET===========================================*/
        public AbbNode<E> getRoot() {
        return root;
    }

    public void setRoot(AbbNode<E> root) {
        this.root = root;
    }
}
