package clases_proyecto;

import estructuras.arbol_abb.AbbNode;
import estructuras.arbol_abb.AbbTree;
import estructuras.linkedlist.LinkedList;
import estructuras.linkedlist.LinkedList_Node;
import estructuras.matriz_dispersa.Matriz;
import ventanas.Cliente_GestionImagenes;

/**
 * Clase Imagen de mi proyecto
 *
 * @author Melissa
 */
public class Imagen implements Comparable<Imagen> {

    private int id_Imagen;
    private AbbTree<Capa> capasImagen;
    private Matriz<Capa> capasUnidas;

    /**
     * Método constructor de mi clase Imagen.
     *
     * @param id_Imagen
     */
    public Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
        this.capasImagen = new AbbTree<>();
        this.capasUnidas = new Matriz<>();
    }

    @Override
    public int compareTo(Imagen imagenAComparar) {
        return this.id_Imagen - imagenAComparar.id_Imagen;
    }

    @Override
    public String toString() {
        return "Imagen ID: " + id_Imagen;
    }

    /*==============================================UNIÓN DE LAS CAPAS DE LA IMAGEN==============================================*/
    /**
     * Parte 1) unir capas de árbol general de capas del cliente.
     *
     * @param clienteRegistrado
     * @param arbolCapas
     * @param numCapas
     * @throws InterruptedException
     */
    public void unirCapasPostOrden(Cliente_GestionImagenes cgi, Cliente clienteRegistrado, AbbTree arbolCapas, int numCapas) throws InterruptedException {
        Matriz capasUnidas = new Matriz();
        LinkedList<String> listaRecorrido = new LinkedList<>();
        LinkedList<Integer> a = new LinkedList<>();
        a.insertElement_AtBeggining(numCapas);
        unirCapaPostOrden(arbolCapas.getRoot(), a, capasUnidas, listaRecorrido);
        String rutaImagenes = "./Clientes/Cliente_" + clienteRegistrado.getDPI() + "/Imagenes";
        capasUnidas.crearFicheroNeato_MatrizSinConexiones("Imagen_PostOrden", rutaImagenes + "/Neato_Imagenes", rutaImagenes + "/Imagenes_Puras");
        /*Parte de impresión en un jTextField el resultado del recorrido*/
        LinkedList_Node<String> nodoActual = listaRecorrido.getCabezaLista();
        String cadena = "";
        while (nodoActual != null) {
            cadena += (String) nodoActual.getValor().toString();
            cadena += ", ";
            nodoActual = nodoActual.siguiente;
        }
        cgi.jTextFieldRecorridoGenerado.setText(cadena);
    }

    /**
     * Parte 2) unir capas de árbol general de capas del cliente.
     *
     * @param nodoActual
     * @param numCapas
     * @param capasUnidas
     */
    private void unirCapaPostOrden(AbbNode nodoActual, LinkedList<Integer> numCapas, Matriz capasUnidas, LinkedList<String> listaRecorrido) {
        if (nodoActual != null) {
            if ((int) numCapas.getCabezaLista().getValor() != 0) {
                unirCapaPostOrden(nodoActual.getHijoIzq(), numCapas, capasUnidas, listaRecorrido);
                unirCapaPostOrden(nodoActual.getHijoDer(), numCapas, capasUnidas, listaRecorrido);
                if ((int) numCapas.getCabezaLista().getValor() != 0) {
                    /*Parte de inserción del recorrido*/
                    listaRecorrido.insertElement_AtEnding(String.valueOf(((Capa) nodoActual.getValor()).getId_Capa()));
                    /*Parte de apilación de las capas*/
                    capasUnidas.superPonerMatriz(((Capa) nodoActual.getValor()).getMatriz_Capa());
                    int valorActual = (int) numCapas.getCabezaLista().getValor() - 1;
                    numCapas.getCabezaLista().setValor(valorActual);
                }
            }
        }
    }

    /**
     * Parte 1) unir capas de árbol general de capas del cliente.
     *
     * @param clienteRegistrado
     * @param arbolCapas
     * @param numCapas
     * @throws InterruptedException
     */
    public void unirCapasInOrden(Cliente_GestionImagenes cgi, Cliente clienteRegistrado, AbbTree arbolCapas, int numCapas) throws InterruptedException {
        Matriz capasUnidas = new Matriz();
        LinkedList<String> listaRecorrido = new LinkedList<>();
        LinkedList<Integer> a = new LinkedList<>();
        a.insertElement_AtBeggining(numCapas);
        unirCapaInOrden(arbolCapas.getRoot(), a, capasUnidas, listaRecorrido);
        String rutaImagenes = "./Clientes/Cliente_" + clienteRegistrado.getDPI() + "/Imagenes";
        capasUnidas.crearFicheroNeato_MatrizSinConexiones("Imagen_InOrden", rutaImagenes + "/Neato_Imagenes", rutaImagenes + "/Imagenes_Puras");
        /*Parte de impresión en un jTextField el resultado del recorrido*/
        LinkedList_Node<String> nodoActual = listaRecorrido.getCabezaLista();
        String cadena = "";
        while (nodoActual != null) {
            cadena += (String) nodoActual.getValor().toString();
            cadena += ", ";
            nodoActual = nodoActual.siguiente;
        }
        cgi.jTextFieldRecorridoGenerado.setText(cadena);
    }

    /**
     * Parte 2) unir capas de árbol general de capas del cliente.
     *
     * @param nodoActual
     * @param numCapas
     * @param capasUnidas
     */
    private void unirCapaInOrden(AbbNode nodoActual, LinkedList<Integer> numCapas, Matriz capasUnidas, LinkedList<String> listaRecorrido) {
        if (nodoActual != null) {
            if ((int) numCapas.getCabezaLista().getValor() != 0) {
                unirCapaInOrden(nodoActual.getHijoIzq(), numCapas, capasUnidas, listaRecorrido);
                if ((int) numCapas.getCabezaLista().getValor() != 0) {
                    /*Parte de inserción del recorrido*/
                    listaRecorrido.insertElement_AtEnding(String.valueOf(((Capa) nodoActual.getValor()).getId_Capa()));
                    /*Parte de apilación de las capas*/
                    capasUnidas.superPonerMatriz(((Capa) nodoActual.getValor()).getMatriz_Capa());
                    int valorActual = (int) numCapas.getCabezaLista().getValor() - 1;
                    numCapas.getCabezaLista().setValor(valorActual);
                }
                unirCapaInOrden(nodoActual.getHijoDer(), numCapas, capasUnidas, listaRecorrido);
            }
        }
    }

    /**
     * Parte 1) unir capas de árbol general de capas del cliente.
     *
     * @param clienteRegistrado
     * @param arbolCapas
     * @param numCapas
     * @throws InterruptedException
     */
    public void unirCapasPreOrden(Cliente_GestionImagenes cgi, Cliente clienteRegistrado, AbbTree arbolCapas, int numCapas) throws InterruptedException {
        Matriz capasUnidas = new Matriz();
        LinkedList<String> listaRecorrido = new LinkedList<>();
        /*
        Recordar super importante que yo utilizo esta linkedlist porque en java el paso de punteros no existen osea el paso de parametros por referencia no existe
        Solo existe el paso por valor y lo que hace java es pasar una copia de la dirección de mis parámetros así que para simular un paso por referencia 
        lo que se puede hacer es encapsular tu variable en un array y pasar a ese array. De ahí de la declaración de mi Variable "a" que es como una capsula
        para poder modificar mi número de capas en el otro método. 
         */
        LinkedList<Integer> a = new LinkedList<>();
        a.insertElement_AtBeggining(numCapas);
        /*Parte de apilación de capas*/
        unirCapaPreOrden(arbolCapas.getRoot(), a, capasUnidas, listaRecorrido);
        /*Parte de impresión de imagen resultante*/
        String rutaImagenes = "./Clientes/Cliente_" + clienteRegistrado.getDPI() + "/Imagenes";
        capasUnidas.crearFicheroNeato_MatrizSinConexiones("Imagen_PreOrden", rutaImagenes + "/Neato_Imagenes", rutaImagenes + "/Imagenes_Puras");
        /*Parte de impresión en un jTextField el resultado del recorrido*/
        LinkedList_Node<String> nodoActual = listaRecorrido.getCabezaLista();
        String cadena = "";
        while (nodoActual != null) {
            cadena += (String) nodoActual.getValor().toString();
            cadena += ", ";
            nodoActual = nodoActual.siguiente;
        }
        cgi.jTextFieldRecorridoGenerado.setText(cadena);
    }

    /**
     * Parte 2) unir capas de árbol general de capas del cliente.
     *
     * @param nodoActual
     * @param numCapas
     * @param capasUnidas
     */
    private void unirCapaPreOrden(AbbNode nodoActual, LinkedList<Integer> numCapas, Matriz capasUnidas, LinkedList<String> listaRecorrido) {
        if (nodoActual != null) {
            if ((int) numCapas.getCabezaLista().getValor() != 0) {
                /*Parte de inserción del recorrido*/
                listaRecorrido.insertElement_AtEnding(String.valueOf(((Capa) nodoActual.getValor()).getId_Capa()));
                /*Parte de apilación de las capas según el recorrido*/
                capasUnidas.superPonerMatriz(((Capa) nodoActual.getValor()).getMatriz_Capa());
                /*Parte de marcación de nodo visitado*/
                int valorActual = (int) numCapas.getCabezaLista().getValor() - 1;
                numCapas.getCabezaLista().setValor(valorActual);
                /*Parte de desplazamiento de nodos hijos*/
                unirCapaPreOrden(nodoActual.getHijoIzq(), numCapas, capasUnidas, listaRecorrido);
                unirCapaPreOrden(nodoActual.getHijoDer(), numCapas, capasUnidas, listaRecorrido);
            }
        }
    }

    /**
     * Método para unir capas mediante el recorrido de amplitud en el árbol de
     * capas de cada imagen.
     */
    public void unirCapasAmplitud() {
        capasUnidas = new Matriz<>();
        if (capasImagen.getRoot() != null) {
            LinkedList<AbbNode> colaNodos = new LinkedList<>();
            colaNodos.insertElement_AtEnding(capasImagen.getRoot());
            AbbNode aux = null;
            while (colaNodos.getlength() != 0) {
                aux = colaNodos.extractElement_AtBeggining().getValor();
                System.out.println(aux.getValor());
                capasUnidas.superPonerMatriz(((Capa) aux.getValor()).getMatriz_Capa());
                if (aux.getHijoIzq() != null) {
                    colaNodos.insertElement_AtEnding(aux.getHijoIzq());
                }
                if (aux.getHijoDer() != null) {
                    colaNodos.insertElement_AtEnding(aux.getHijoDer());
                }
            }
        }
    }

    /*==============================================MÉTODOS GET AND SET==============================================*/
    /**
     * @return the id_Imagen
     */
    public int getId_Imagen() {
        return id_Imagen;
    }

    /**
     * @param id_Imagen the id_Imagen to set
     */
    public void setId_Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    /**
     * @return the capasImagen
     */
    public AbbTree<Capa> getCapasImagen() {
        return capasImagen;
    }

    /**
     * @param capasImagen the capasImagen to set
     */
    public void setCapasImagen(AbbTree<Capa> capasImagen) {
        this.capasImagen = capasImagen;
    }

    /**
     * @return the capasUnidas
     */
    public Matriz<Capa> getCapasUnidas() {
        return capasUnidas;
    }

    /**
     * @param capasUnidas the capasUnidas to set
     */
    public void setCapasUnidas(Matriz<Capa> capasUnidas) {
        this.capasUnidas = capasUnidas;
    }
}
