package estructuras.linkedlist;

import clases_proyecto.Album;
import clases_proyecto.Imagen;
import estructuras.Graphviz;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Melissa
 */
public class LinkedList<E> {
    private LinkedList_Node<E> cabeza = null;
    private LinkedList_Node<E> cola = null;
    private int longitud = 0;
    
    //-------------------------------Métodos del proyecto-------------------------------------------
    
    /* Método para imprimir el .dot de los álbumes junto con su listado de imagenes. */

    /**
     *
     * @param nombreFichero
     * @param rutaDot
     * @param rutaPng
     */
    public void crearFicheroDot_Albumes(String nombreFichero, String rutaDot, String rutaPng){
        //Parte del String o texto que va a llevar el fichero 
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();

        dot.append("digraph G { \n");
        dot.append("node [shape=box]  \n");

        String conexionesNodos = getCuerpoDotAlbumes();
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
    
    public String getCuerpoDotAlbumes(){
        String cadena = "";
        String declaracionAlbumes = "//............ ............ ............ ............ ALBUMES \n";
        String conexionesAlbumes = "//............ Enlaces de los albumes \n";
        String declaracionImagenes = "//............ ............ ............ ............ IMAGEN \n";
        String conexionesImagenes = "//............ Enlaces de las imágenes \n";
        //Generación del álbumes.
        LinkedList_Node nodoAlbumActual = this.cabeza;
        while (nodoAlbumActual != null) {
            Album albumActual = (Album)nodoAlbumActual.getValor();
            String nombreAlbum = albumActual.getNombreAlbum();
            declaracionAlbumes += "Album"+albumActual.hashCode()+"[label = \""+nombreAlbum+"\" width = 1.5 ]; \n";
            
            //Generación de conexiones de álbumes.
            if (nodoAlbumActual.siguiente != null) {
                conexionesAlbumes += "Album"+albumActual.hashCode()+" -> Album"+((Album)nodoAlbumActual.siguiente.getValor()).hashCode()+" [constraint = false]; \n";
                conexionesAlbumes += "Album"+((Album)nodoAlbumActual.siguiente.getValor()).hashCode()+" -> Album"+albumActual.hashCode()+" [constraint = false]; \n";
            }
            
            //Generación de imágenes.
            LinkedList_Node nodoImagenActual = albumActual.getListaImagenes().cabeza;
            declaracionImagenes += "//Imagenes "+nombreAlbum + "\n";
            while (nodoImagenActual != null) {
                Imagen imagenActual = ((Imagen)nodoImagenActual.getValor());
                int idImagen = imagenActual.getId_Imagen();
                declaracionImagenes += "I"+imagenActual.hashCode()+"[label = \"Image "+idImagen+"\" width = 1.5 ];\n";
                
                //Generación de conexiones de imágenes.
                conexionesImagenes += "//Imagenes "+nombreAlbum + "\n";;
                if (nodoImagenActual == albumActual.getListaImagenes().cabeza) {
                    conexionesImagenes += "Album"+albumActual.hashCode()+" -> I"+imagenActual.hashCode()+" [constraint = true];";
                }
                
                if (nodoImagenActual.siguiente != null) {
                    conexionesImagenes += "I"+imagenActual.hashCode()+" -> I"+((Imagen)nodoImagenActual.siguiente.getValor()).hashCode()+" [constraint = true];\n";
                    conexionesImagenes += "I"+((Imagen)nodoImagenActual.siguiente.getValor()).hashCode()+" -> I"+imagenActual.hashCode()+" [constraint = true];\n";
                }
                
                nodoImagenActual = nodoImagenActual.siguiente;
            }
            nodoAlbumActual = nodoAlbumActual.siguiente;
        }
        cadena += declaracionAlbumes;
        cadena += conexionesAlbumes;
        cadena += declaracionImagenes;
        cadena += conexionesImagenes;
        
        return cadena;
    }

    //-------------------------------Metodos Fundamentales de mi lista------------------------------------
    //Método para imprimir
    public void imprimir(){
        LinkedList_Node<E> nodoActual = this.cabeza;
        while (nodoActual != null) {
            System.out.println(nodoActual.getValor());
            nodoActual = nodoActual.siguiente;
        }
    }
    
    //Métodos de encapsulamiento que me serviran para mostrar los datos.
    public LinkedList_Node<E> getCabezaLista() {
        return this.cabeza;
    }

    public LinkedList_Node<E> getColaLista() {
        return this.cola;
    }

    public int getlength(){
        return longitud;
    }
    
    /**
     * Método para pasar a array mi linkelist esto solamente para uso en caso
     * de utilizar tablas o cosas ya propiamente de java ya que algunas herramientas 
     * solo aceptan arrays.
     * @return 
     */
    public Object[] getArrayfromLinkedList(){
        Object[] array = new Object[this.longitud];
        LinkedList_Node nodoActual = cabeza;
        int index = 0;
        while (nodoActual != null) {
            array[index] = nodoActual.getValor();
            index++;
            nodoActual = nodoActual.siguiente;
        }
        
        return array;
    }

    //--------------------------------------Método de inserción de datos--------------------------------
    public void insertElement_AtBeggining(E valor) {
        LinkedList_Node<E> nodoNuevo = new LinkedList_Node<E>(valor);
        if (cabeza == null) {
            cabeza = nodoNuevo;
            cola = nodoNuevo;
        } else {
            cabeza.anterior = nodoNuevo;
            nodoNuevo.siguiente = cabeza;
            cabeza = nodoNuevo;
        }
        longitud++;
    }

    public void insertElement_AtEnding(E valor) {
        LinkedList_Node<E> nodoNuevo = new LinkedList_Node<E>(valor);
        if (cabeza == null) {
            cabeza = nodoNuevo;
            cola = nodoNuevo;
        } else {
            cola.siguiente = nodoNuevo;
            nodoNuevo.anterior = cola;
            cola = nodoNuevo;
        }
        longitud++;
    }

    public Boolean insertElment_AtPosition(E valor, int position) {
        if (position < 0) {
            return false;
        } else if (position >= longitud) {
            return false;
        } else {
            LinkedList_Node<E> nodoNuevo = new LinkedList_Node<E>(valor);
            LinkedList_Node<E> nodoActual = cabeza;

            for (int i = 0; i != position; i++) {
                nodoActual = nodoActual.siguiente;
            }
            if (nodoActual == cabeza) {
                cabeza.anterior = nodoNuevo;
                nodoNuevo.siguiente = cabeza;
                cabeza = nodoNuevo;
            } else if (nodoActual == cola) {
                cola.siguiente = nodoNuevo;
                nodoNuevo.anterior = cola;
                cola = nodoNuevo;
            } else {
                nodoNuevo.anterior = nodoActual;
                nodoNuevo.siguiente = nodoActual.siguiente;
                nodoActual.siguiente.anterior = nodoNuevo;
                nodoActual.siguiente = nodoNuevo;
            }
            longitud++;
            return true;
        }
    }

    //-----------------------------------------------------Método de búsqueda-------------------------------------
    public int getPosition(LinkedList_Node<E> nodoBuscar){
        LinkedList_Node<E> nodoActual = cabeza;
        int i = 0;
        while(nodoActual != nodoBuscar){
            i++;
            if(i >= longitud){
                return -1;
            }
            nodoActual = nodoActual.siguiente;
        }
        return i;
    }

    public LinkedList_Node<E> getNode(int position){
        LinkedList_Node<E> nodoActual = cabeza;
        int i = 0;
        while(i != position){
            i++;
            if(i >= longitud){
                return null;
            }
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual;
    }
    //aplicando concepto de polimorfismo
    public LinkedList_Node<E> getNode(E valor){
        LinkedList_Node<E> nodoActual = cabeza;
        while(nodoActual != null){
                if(nodoActual.getValor() == valor){
                    return nodoActual;
                }
            nodoActual = nodoActual.siguiente;
        }

        return null;
    }

    //------------------------------------------------------Método de eliminación------------------------------
    public void deleteElement_AtBeggining(){
        if(longitud==1){
            cabeza = null;
            cola = null;
            longitud=0;
        }else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
        }
    }

    public void deleteElement_AtEnding(){
        if(longitud==1){
            cabeza = null;
            cola = null;
            longitud=0;
        }else {
            cola = cola.anterior;
            cola.siguiente = null;
            longitud--;
        }
    }

    public void deleteElement_AtPosition(int position){
        LinkedList_Node<E> nodoEliminado = getNode(position);
        if (nodoEliminado == cabeza) {
            deleteElement_AtBeggining();
        }else if(nodoEliminado == cola){
            deleteElement_AtEnding();
        }else {
            nodoEliminado.anterior.siguiente = nodoEliminado.siguiente;
            nodoEliminado.siguiente.anterior = nodoEliminado.anterior;
            longitud--;
        }
    }

    public void deleteElement(LinkedList_Node<E> nodoExtraer){
        LinkedList_Node<E> nodoActual = cabeza;
        while(nodoActual != null){
            if(nodoActual == nodoExtraer){
                if (nodoActual == cabeza) {
                    deleteElement_AtBeggining();
                }else if(nodoActual == cola){
                    deleteElement_AtEnding();
                }else {
                    nodoActual.anterior.siguiente = nodoActual.siguiente;
                    nodoActual.siguiente.anterior = nodoActual.anterior;
                    longitud--;
                }
            }
            nodoActual = nodoActual.siguiente;
        }

    }

    public LinkedList_Node<E> extractElement_AtBeggining(){
        LinkedList_Node<E> nodoExtraer = getNode(0);
        deleteElement_AtBeggining();
        return nodoExtraer;
    }

    public LinkedList_Node<E> extractElement_AtEnding(){
        LinkedList_Node<E> nodoExtraer = getNode(longitud-1);
        deleteElement_AtEnding();
        return nodoExtraer;
    }

    public LinkedList_Node<E> extractElement_AtPosition(int position){
        LinkedList_Node<E> nodoExtraer = getNode(position);
        deleteElement_AtPosition(position);
        return nodoExtraer;
    }

    public LinkedList_Node<E> extractElementValor(E valor){
        LinkedList_Node<E> nodoExtraer = getNode(valor);
        deleteElement(nodoExtraer);
        return  nodoExtraer;
    }

    //----------------------------------------------------Métodos de modificación----------------------------------
    public void modifyElement_AtBeggining(E valor){
        LinkedList_Node<E> nodoModificar = getNode(0);
        nodoModificar.setValor(valor);
    }

    public void modifyElement_AtEnding(E valor){
        LinkedList_Node<E> nodoModificar = getNode(longitud-1);
        nodoModificar.setValor(valor);
    }

    public void modifyElement_AtPosition(E valor, int position){
        LinkedList_Node<E> nodoModificar = getNode(position);
        nodoModificar.setValor(valor);
    }

}
