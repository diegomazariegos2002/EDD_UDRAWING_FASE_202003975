package estructuras.arbolB;

import estructuras.Graphviz;
import estructuras.linkedlist.LinkedList;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * IMPORTANTE Recordatorio: en este tipo de árbol los nodos tiene dos punteros
 * su hijoIzq y su hijoDer pero hay que recordar que los hijoDer en los nodos
 * que no sean los últimos de sus páginas correspondientes no importan. Esto por
 * la norma de que una página solo puede tener un máximo de páginas como sea su
 * número de orden. Por ejemplo si el árbol es de orden 5 cada página puede
 * tener como máximo 5 páginas hijas. Graficar para entender mejor esto también.
 *
 * @author Melissa
 */
public class ArbolB<E extends Comparable<E>> {

    final int orden_arbol = 5;
    Pagina raiz;

    public ArbolB() {
        this.raiz = null;
    }

    /*==============================================MÉTODOS FUNDAMENTALES==============================================*/
    //MÉTODOS DE INSERCIÓN.
    public void insertarEnArbol(E valor) {
        NodoB nodo = new NodoB(valor);
        if (raiz == null) {
            raiz = new Pagina();
            raiz.insertarEnPagina(nodo);
        } else {
            NodoB obj = insertar_En_Pagina(nodo, raiz);
            if (obj != null) {
                //si devuelve algo el metodo de insertar en rama quiere decir que creo una nueva rama, y se debe insertar en el arbol
                raiz = new Pagina();
                raiz.insertarEnPagina(obj);
                raiz.hoja = false;
            }
        }
    }

    private NodoB insertar_En_Pagina(NodoB nodoNuevo, Pagina pagina) {
        if (pagina.hoja) {
            /*Esta parte es para insertarEnArbol el nodo en la página que sea hoja, de acuerdo al orden que debería llevar.*/
            pagina.insertarEnPagina(nodoNuevo);
            /*Este es un if-else básicamente es la parte que compara si se ha alcanzado el máximo de elementos en la hoja.*/
            return (pagina.contador == orden_arbol) ? dividir(pagina) : null;
        } else {
            /*Si no es una hoja la página*/
 /*
                Entonces hay que recorrer el árbol por medio de sus páginas 
                y en cada página recorrer el nodo
                correspondiente viendo si es mayor o menor el nodo de entrada
             */
            NodoB nodoActual = pagina.primero;
            do {
                if (nodoNuevo.valor.compareTo(nodoActual.valor) == 0) { //Si tiene le mismo valor, ya no ingresas nada.
                    return null;
                } else if (nodoNuevo.valor.compareTo(nodoActual.valor) < 0) { //si es menor te tienes que desplazar a la página hijoIzq del nodoActual recorrido.
                    NodoB obj = insertar_En_Pagina(nodoNuevo, nodoActual.hijoIzq);
                    return validar_Division(obj, pagina);
                } else if (nodoActual.siguiente == null) {
                    NodoB obj = insertar_En_Pagina(nodoNuevo, nodoActual.hijoDer);
                    return validar_Division(obj, pagina);
                }
                nodoActual = (NodoB) nodoActual.siguiente;
            } while (nodoActual != null);
        }
        return null;
    }

    /**
     * Método validar si no se debe dividir aún más los nodos.
     *
     * @param obj
     * @param pagina
     * @return
     */
    private NodoB validar_Division(NodoB obj, Pagina pagina) {
        if (obj instanceof NodoB) {
            pagina.insertarEnPagina((NodoB) obj);
            if (pagina.contador == orden_arbol) {
                return dividir(pagina);
            }
        }
        return null;
    }

    /**
     * Este método es el encargado de dividir la página recursivamente.
     *
     * @param rama
     * @return
     */
    private NodoB dividir(Pagina pagina) {
        E valorNodoDivisor = null;
        NodoB<E> temp, nodoGenerado;
        NodoB<E> nodoActual = pagina.primero;
        Pagina rderecha = new Pagina();
        Pagina rizquierda = new Pagina();

        int cont = 0;
        while (nodoActual != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) { // Para los nodos que se van a la izquierda.
                temp = new NodoB(nodoActual.valor);
                temp.hijoIzq = nodoActual.hijoIzq;
                if (cont == 2) {
                    temp.hijoDer = nodoActual.siguiente.hijoIzq;
                } else {
                    temp.hijoDer = nodoActual.hijoDer; //este podría ser null
                }
                //si la pagina posee hijos quiere decir que no es una hoja
                if (temp.hijoDer != null && temp.hijoIzq != null) {
                    rizquierda.hoja = false;
                }
                rizquierda.insertarEnPagina(temp);

            } else if (cont == 3) {
                valorNodoDivisor = nodoActual.valor;
            } else { // Para los nodos que se van a la derecha.
                temp = new NodoB(nodoActual.valor, nodoActual.hijoIzq, nodoActual.hijoDer);
                //si la rama posee ramas deja de ser hoja
                if (temp.hijoDer != null && temp.hijoIzq != null) {
                    rderecha.hoja = false;
                }
                rderecha.insertarEnPagina(temp);
            }
            nodoActual = nodoActual.siguiente;
        }
        nodoGenerado = new NodoB(valorNodoDivisor, rizquierda, rderecha);
        return nodoGenerado;
    }

    //==================================================MÉTODOS DE BÚSQUEDA==================================================
    public E getValorNodoB_byId(E valorBuscado) {
        return getValorNodo_by_Id(raiz, valorBuscado);
    }

    private E getValorNodo_by_Id(Pagina paginaActual, E valorBuscado) {
        if (paginaActual != null) {
            //recorrer los hijos de cada clave
            NodoB<E> nodoActual = paginaActual.primero;
            while (nodoActual != null) {
                if (valorBuscado.compareTo(nodoActual.valor) == 0) {
                    return nodoActual.valor;
                } else if (valorBuscado.compareTo(nodoActual.valor) < 0) { // Si el valor buscado es menor al del nodo actual.
                    return getValorNodo_by_Id(nodoActual.hijoIzq, valorBuscado);
                } else if (nodoActual.siguiente == null) { //Si llego al último nodo ahí si debería irse por la derecha.
                    return getValorNodo_by_Id(nodoActual.hijoDer, valorBuscado);
                }
                nodoActual = nodoActual.siguiente;
            }
        }
        return null;
    }
    
    //==================================================MÉTODOS DE RECORRIDOS==================================================
    /**
     * Parte 1) Método para convertir a una LinkedList mi árbol por medio del recorrido de amplitud.
     */
    public LinkedList<E> getLinkedList_RecorridoAmplitud(){
        LinkedList<E> listaValores_E =  new LinkedList<>();
        getLinkedList_RecorridoAmplitud(raiz, listaValores_E);
        return listaValores_E;
    }
    
    /**
     * Parte 2) Método para convertir a una LinkedList mi árbol por medio del recorrido de amplitud.
     * Recordar que este es un método iterativo que se acompaña de una cola.
     */
    private void getLinkedList_RecorridoAmplitud(Pagina root,  LinkedList<E> listaValores_E){
        if (root != null) {
            LinkedList<Pagina> colaPaginas = new LinkedList<>();
            colaPaginas.insertElement_AtEnding(raiz);
            Pagina paginaActual = null;
            while (colaPaginas.getlength() != 0) {
                paginaActual = colaPaginas.extractElement_AtBeggining().getValor();
                //Recorremos todos los nodos de la pagina actual y encolamos las paginas de cada nodo.
                NodoB<E> nodoActual = paginaActual.primero;
                while (nodoActual != null) {
                    listaValores_E.insertElement_AtEnding(nodoActual.valor);
                    if (nodoActual.hijoIzq != null) colaPaginas.insertElement_AtEnding(nodoActual.hijoIzq);
                    if (nodoActual.hijoDer != null) colaPaginas.insertElement_AtEnding(nodoActual.hijoDer);
                    nodoActual = nodoActual.siguiente;
                }
            }
        }
    }

    /*==============================================MÉTODOS GRAPHVIZ==============================================*/
    public void graficarArbolB(String nombreFichero, String rutaDot, String rutaPng) {
        //Parte del String o texto que va a llevar el fichero 
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();
        dot.append("digraph arbolB{\n");
        dot.append("rankr=TB;\n");
        dot.append("node[shape = box,fillcolor=\"azure2\" color=\"black\" style=\"filled\"];\n");
        //metodos para graficarArbolB el arbol
        dot.append(this.graficar_nodos(this.raiz));
        dot.append(this.graficar_enlaces(this.raiz));
        dot.append("}\n");

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

    private String graficar_nodos(Pagina pagina) {
        String cadena = "";

        if (pagina.hoja) { //si es un hoja solo grafica el nodo
            cadena += "node[shape=record label= \"<p0>";
            int contador = 0;
            NodoB nodoActual = pagina.primero;
            while (nodoActual != null) {
                contador++;
                cadena += "|{" + nodoActual.valor + "}|<p" + contador + "> ";
                nodoActual = nodoActual.siguiente;
            }
            cadena += "\"]" + pagina.hashCode() + ";\n";
            return cadena;
        } else {
            cadena += "node[shape=record label= \"<p0>";
            int contador = 0;
            NodoB nodoActual = pagina.primero;
            while (nodoActual != null) {
                contador++;
                cadena += "|{" + nodoActual.valor + "}|<p" + contador + "> ";
                nodoActual = nodoActual.siguiente;
            }
            cadena += "\"]" + pagina.hashCode() + ";\n";

            //recorrer los hijos de cada clave
            nodoActual = pagina.primero;
            NodoB ultimo = null;
            while (nodoActual != null) {
                cadena += this.graficar_nodos(nodoActual.hijoIzq);
                ultimo = nodoActual;
                nodoActual = nodoActual.siguiente;
            }
            cadena += this.graficar_nodos(ultimo.hijoDer);
            return cadena;
        }
    }

    private String graficar_enlaces(Pagina pagina_Actual) {
        String cadena = "";
        if (pagina_Actual.hoja) {
            return "" + pagina_Actual.hashCode() + ";\n";
        } else {
            NodoB nodoActual = pagina_Actual.primero;
            NodoB ultimo = null;
            int contador = 0;
            int raiz_actual_txt = pagina_Actual.hashCode();
            while (nodoActual != null) {
                cadena += "\n" + raiz_actual_txt + ":p" + contador + "->" + this.graficar_enlaces(nodoActual.hijoIzq);
                contador++;
                ultimo = nodoActual;
                nodoActual = nodoActual.siguiente;
            }
            if (ultimo.hijoDer != null) {
                cadena += "\n" + raiz_actual_txt + ":p" + contador + "->" + this.graficar_enlaces(ultimo.hijoDer);
            }

            return cadena;
        }
    }
}
