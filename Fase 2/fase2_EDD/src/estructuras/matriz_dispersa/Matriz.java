package estructuras.matriz_dispersa;

import java.io.FileWriter;
import java.io.PrintWriter;
import estructuras.Graphviz;

/**
 * Clase Matriz que la utilizo principalmente para generar mi matriz dispera,
 * esta hace uso de las clases nodo en su misma matriz.
 *
 * @author Melissa
 * @param <E>
 */
public class Matriz<E>{

    ListaEncabezado eFilas;
    ListaEncabezado eColumnas;

    /**
     * Constructor de mi clase Matriz
     *
     * @param eFilas
     * @param eColumnas
     */
    public Matriz() {
        this.eFilas = new ListaEncabezado();
        this.eColumnas = new ListaEncabezado();
    }

    /**
     * ===============================================Métodos propios del proyecto=======================================
     */
    /**
     * Método para crear el fichero .Neato_Con_Conexiones de mi Matriz con las
     * conexiones y encabezados. Neato_Con_Conexiones.
     *
     * @param nombreFichero
     */
    public void crearFicheroNeato_MatrizSinConexiones(String nombreFichero, String rutaNeato, String rutaPng) throws InterruptedException {
        //Parte del String o texto que va a llevar el fichero
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();
        dot.append("/* DIEGO ANDRÉ MAZARIEGOS BARRIENTOS */\n");
        dot.append("digraph Sparce_Matrix {   \n");
        dot.append("node [shape=box]\n\n");
        dot.append("/* ............ ............DECLARACIÓN NODOS POSICIÓN............................ */\n\n");

        /**
         * Parte declaración de la matriz dispersa sin conexiones.
         */
        String cuerpoTabla = crearCuerpoTabla();

        dot.append(cuerpoTabla);
        dot.append("}");

        FileWriter fichero = null;
        PrintWriter pw = null;
        //Parte de la creación de un fichero
        try {
            fichero = new FileWriter(rutaNeato + "/" + nombreFichero + ".neato");
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
        Graphviz gv = new Graphviz();
        gv.dibujar("neato", "-Tpng", rutaNeato + "/" + nombreFichero + ".neato", rutaPng + "/" + nombreFichero + ".png");
    }

    /**
     * Métood parte de crearFicheroNeato
     * @return 
     */
    private String crearCuerpoTabla() {
        String cadena = "";
        /*
            Recordar que para el gráfico de esta matriz dispersa que no posee direcciones me es útil
            utilizar neato que es otra herramienta parecida a dot en Graphviz.
         */
        NodoMatriz_Encabezado eFila = eFilas.primero;
        while (eFila != null) {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            while (actual != null) {
                cadena += "/* Nodo F: " + actual.fila + " C: " + actual.columna + " */ \n";
                cadena += "N" + actual.hashCode() + " [label = \"\",width = 1, height = 1,"
                        + " style = filled, fillcolor = \"" + actual.colorNodo + "\", "
                        + "pos = \"" + (eColumnas.getEncabezado(actual.columna).posicion) + ",-" + (eFilas.getEncabezado(actual.fila).posicion) + "!\" ]; \n";
                actual = actual.derecha;
            }
            eFila = eFila.siguiente;
        }
        return cadena;
    }
    
    public Matriz superPonerMatriz(Matriz matrizDeEncima){
        NodoMatriz_Encabezado eFila = matrizDeEncima.eFilas.primero;
        while (eFila != null) {
            NodoMatriz_Posicion nodoActual = eFila.accesoNodo;
            while (nodoActual != null) {
                this.insertar(nodoActual.fila, nodoActual.columna, (E)nodoActual.valor, nodoActual.colorNodo);
                nodoActual = nodoActual.derecha;
            }
            eFila = eFila.siguiente;
        }
        return null;
    }

    //==============================================Métodos Fundamentales==================================
    //---------------------------------------Métodos de inserción------------------------------------
    /**
     * Método para insertar en una determinada posición x,y un nodo nuevo.
     *
     * @param fila
     * @param columna
     * @param valor
     * @param colorNodo recordar que este atributo es extra del proyecto en una
     * matriz normal no se agrega.
     */
    public void insertar(int fila, int columna, E valor, String colorNodo) {
        NodoMatriz_Posicion<E> nuevo = new NodoMatriz_Posicion<>(fila, columna, valor, colorNodo);
        //Parte de las filas
        NodoMatriz_Encabezado eFila = eFilas.getEncabezado(fila);
        if (eFila == null) {
            eFila = new NodoMatriz_Encabezado(fila);
            eFila.accesoNodo = nuevo;
            nuevo.accesoFila = eFila;
            eFilas.setEncabezado(eFila);
        } else {
            /*Si se llegase a insertar un nodo con menor nivel en columnas que la que tiene el acceso nodo*/
            if (nuevo.columna < eFila.accesoNodo.columna) {
                nuevo.derecha = eFila.accesoNodo;
                eFila.accesoNodo.izquierda = nuevo;

                //Como es menor entonces va a ser el nuevo acceso nodo y necesitar apuntar a su acceso fila.
                nuevo.accesoFila = eFila;
                eFila.accesoNodo.accesoFila = null;

                //Declarar al nuevo nodo de acceso.
                eFila.accesoNodo = nuevo;

            } else {
                NodoMatriz_Posicion actual = eFila.accesoNodo;
                boolean superPosicion = false; // variable bandera para validar si hubo superposición de un nodo ya existente.
                if (nuevo.columna == actual.columna) { // si la superposición se da en un nodo acceso.
                    actual.valor = nuevo.valor;
                    actual.colorNodo = nuevo.colorNodo;
                    superPosicion = true;
                } else {
                    while (actual.derecha != null) {
                        if (nuevo.columna == actual.columna) {  // si la superposición se da en un nodo cualquiera.
                            actual.valor = nuevo.valor;
                            actual.colorNodo = nuevo.colorNodo;
                            superPosicion = true;
                            break;
                        }
                        if (nuevo.columna < actual.derecha.columna) {
                            nuevo.derecha = actual.derecha;
                            actual.derecha.izquierda = nuevo;
                            nuevo.izquierda = actual;
                            actual.derecha = nuevo;
                            break;
                        }
                        actual = actual.derecha;
                    }
                    if (nuevo.columna == actual.columna) {  // si la superposición se da en el último nodo.
                        actual.valor = nuevo.valor;
                        actual.colorNodo = nuevo.colorNodo;
                        superPosicion = true;
                    }
                    if (actual.derecha == null && superPosicion == false) {
                        actual.derecha = nuevo;
                        nuevo.izquierda = actual;
                    }
                }
            }
        }
        //Parte de las columnas
        NodoMatriz_Encabezado eColumna = eColumnas.getEncabezado(columna);
        if (eColumna == null) {
            eColumna = new NodoMatriz_Encabezado(columna);
            eColumna.accesoNodo = nuevo;
            nuevo.accesoColumna = eColumna;
            eColumnas.setEncabezado(eColumna);
        } else {
            /*Si se llegase a insertar un nodo con menor nivel en filas que la que tiene el acceso nodo*/
            if (nuevo.fila < eColumna.accesoNodo.fila) {
                nuevo.abajo = eColumna.accesoNodo;
                eColumna.accesoNodo.arriba = nuevo;

                //Como es menor entonces va a ser el nuevo acceso nodo y necesitar apuntar a su acceso fila.
                nuevo.accesoColumna = eColumna;
                eColumna.accesoNodo.accesoColumna = null;

                //Declarar al nuevo nodo de acceso.
                eColumna.accesoNodo = nuevo;

            } else {
                NodoMatriz_Posicion actual = eColumna.accesoNodo;
                boolean superPosicion = false; // variable bandera para validar si hubo superposición de un nodo ya existente.
                if (nuevo.fila == actual.fila) { // si la superposición se da en un nodo acceso.
                    actual.valor = nuevo.valor;
                    actual.colorNodo = nuevo.colorNodo;
                    superPosicion = true;
                } else {
                    while (actual.abajo != null) {
                        if (nuevo.fila == actual.fila) { // si la superposición se da en un nodo cualquiera
                            actual.valor = nuevo.valor;
                            actual.colorNodo = nuevo.colorNodo;
                            superPosicion = true;
                            break;
                        }
                        if (nuevo.fila < actual.abajo.fila) {
                            nuevo.abajo = actual.abajo;
                            actual.abajo.arriba = nuevo;
                            nuevo.arriba = actual;
                            actual.abajo = nuevo;
                            break;
                        }
                        actual = actual.abajo;
                    }
                    if (nuevo.fila == actual.fila) { // si la superposición se da en el último nodo
                        actual.valor = nuevo.valor;
                        actual.colorNodo = nuevo.colorNodo;
                        superPosicion = true;
                    }
                    if (actual.abajo == null && superPosicion == false) {
                        actual.abajo = nuevo;
                        nuevo.arriba = actual;
                    }
                }
            }
        }
    }

    /**
     * Método para mostrar la matriz por medio de columnas
     */
    public void mostrarMatrizColumnas() {
        String matrizGrafica = "";
        NodoMatriz_Encabezado eColumna = eColumnas.primero;
        System.out.println("=============================Matriz por COLUMNAS=============================\n");
        while (eColumna != null) {
            NodoMatriz_Posicion actual = eColumna.accesoNodo;
            while (actual != null) {
                matrizGrafica = matrizGrafica + "[" + actual.fila + "][" + actual.columna + "](" + actual.valor + ")";
                matrizGrafica = matrizGrafica + "   ";
                actual = actual.abajo;
            }
            matrizGrafica = matrizGrafica + "\n";
            eColumna = eColumna.siguiente;
        }
        System.out.println(matrizGrafica);
    }

    public void mostrarMatrizFilas() {
        String matrizGrafica = "";
        NodoMatriz_Encabezado eFila = eFilas.primero;
        System.out.println("=============================Matriz por FILAS=============================\n");
        while (eFila != null) {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            while (actual != null) {
                matrizGrafica = matrizGrafica + "[" + actual.fila + "][" + actual.columna + "](" + actual.valor + ")";
                matrizGrafica = matrizGrafica + "   ";
                actual = actual.derecha;
            }
            matrizGrafica = matrizGrafica + "\n";
            eFila = eFila.siguiente;
        }
        System.out.println(matrizGrafica);
    }

    /**
     * Método para crear el fichero .Neato_Con_Conexiones de mi Matriz con las
     * conexiones y encabezados. Neato_Con_Conexiones.
     *
     * @param nombreFichero
     */
    public void crearFicheroNeato_MatrizConexiones(String nombreFichero, String rutaNeato, String rutaPng) {
        //Parte del String o texto que va a llevar el fichero
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();
        dot.append("/* DIEGO ANDRÉ MAZARIEGOS BARRIENTOS */\n");
        dot.append("digraph Sparce_Matrix { \n");
        dot.append("node [shape=box] \n");
        dot.append("\n");
        dot.append("/* La matriz se envía al grupo 1 */\n");
        dot.append("Mt[ label = \"Matriz\", width = 0.5, height=0.5, pos = \"0,-0!\"  ];\n");
        dot.append("\n");

        /**
         * Parte de declaración de los nodos columna.
         */
        dot.append("//............ ............ ............ ............ COLUMNAS \n");
        dot.append("/* se incrementan los grupos porque son columnas */ \n");
        String nodosColumna = generarNodosColumna();
        dot.append(nodosColumna);
        dot.append("\n");

        dot.append("//............ ............ ............ ............ FILAS \n");
        dot.append("/*  Se mantienen en el mismo grupo porque son filas */ \n");
        /**
         * Parte de declaración de los nodos filas.
         */
        String nodosFila = generarNodosFila();
        dot.append(nodosFila);
        dot.append("\n");

        /**
         * Parte de agregar los nodos a utilizar con su respectiva posición.
         */
        dot.append("\n//............ ............DECLARACIÓN NODOS POSICIÓN............................\n");
        String NodosPosicion = generarNodosPosicion();
        dot.append(NodosPosicion);

        dot.append("}");

        FileWriter fichero = null;
        PrintWriter pw = null;
        //Parte de la creación de un fichero
        try {
            fichero = new FileWriter(rutaNeato + "/" + nombreFichero + ".neato");
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
        Graphviz gv = new Graphviz();
        gv.dibujar("neato", "-Tpng", rutaNeato + "/" + nombreFichero + ".neato", rutaPng + "/" + nombreFichero + ".png");
    }

    /*====================================Métodos auxiliares para generar la gráfica de mi matriz================================*/
    private String generarNodosColumna() {
        String cadena = "";
        NodoMatriz_Encabezado actualColumna = this.eColumnas.primero;
        int i = 1;
        while (actualColumna.siguiente != null) {
            if (actualColumna == this.eColumnas.primero) {
                cadena += "Mt -> C" + actualColumna.hashCode() + "; /*Enlace primer nodo Columna con el objeto Matriz*/\n";
            }
            //Declaración del nodo
            cadena += "C" + actualColumna.hashCode() + " [label = \"C" + actualColumna.id + "\", width = 0.5, height=0.5, pos = \"" + (i) + ",0!\" ];\n";
            //Conexiones del nodo declarado
            cadena += "C" + actualColumna.hashCode() + " -> C" + actualColumna.siguiente.hashCode() + ";\n";
            cadena += "C" + actualColumna.siguiente.hashCode() + " -> C" + actualColumna.hashCode() + ";\n";
            actualColumna = actualColumna.siguiente;
            i++;
        }
        cadena += "C" + actualColumna.hashCode() + " [label = \"C" + actualColumna.id + "\", width = 0.5, height=0.5, pos = \"" + (i) + ",0!\" ];\n";
        return cadena;
    }

    private String generarNodosFila() {
        String cadena = "";
        NodoMatriz_Encabezado actualFila = this.eFilas.primero;
        int i = 1;
        while (actualFila.siguiente != null) {
            if (actualFila == this.eFilas.primero) {
                cadena += "Mt -> F" + actualFila.hashCode() + "; /*Enlace primer nodo Columna con el objeto Matriz*/\n";
            }
            //Declaración del nodo
            cadena += "F" + actualFila.hashCode() + " [label = \"F" + actualFila.id + "\", width = 0.5, height=0.5, pos = \"0,-" + (i) + "!\" ];\n";
            //Conexiones del nodo declarado
            cadena += "F" + actualFila.hashCode() + " -> F" + actualFila.siguiente.hashCode() + ";\n";
            cadena += "F" + actualFila.siguiente.hashCode() + " -> F" + actualFila.hashCode() + ";\n";
            actualFila = actualFila.siguiente;
            i++;
        }
        cadena += "F" + actualFila.hashCode() + " [label = \"F" + actualFila.id + "\", width = 0.5, height=0.5, pos = \"0,-" + (i) + "!\" ];\n";
        return cadena;
    }

    private String generarNodosPosicion() {
        String cadena = "";
        String cadenaConexiones = "//............ ............ Enlaces de los nodos \n";
        NodoMatriz_Encabezado eFila = eFilas.primero;
        NodoMatriz_Encabezado eColuman = eColumnas.primero;
        int i = 2;
        while (eFila != null) {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            int j = 2;
            while (actual != null) {
                cadena += "// Nodo F:" + actual.fila + " C:" + actual.columna + " \n";
                cadena += "N" + actual.hashCode() + " [label = \"\", style = filled, fillcolor = \"" + actual.colorNodo + "\", width = 0.5, height=0.5 pos = \"" + (eColumnas.getEncabezado(actual.columna).posicion) + ",-" + (eFilas.getEncabezado(actual.fila).posicion) + "!\" ]; \n";
                /*======================Conexiones del nodo==================================*/
                /* Si el nodo es un nodo de Acceso FILA */
                if (actual.accesoFila != null) {
                    cadena += "F" + actual.accesoFila.hashCode() + " -> N" + actual.hashCode() + ";\n";
                    cadena += "N" + actual.hashCode() + " -> F" + actual.accesoFila.hashCode() + ";\n";
                }
                /* Si el nodo es un nodo de Acceso COLUMNA */
                if (actual.accesoColumna != null) {
                    cadena += "C" + actual.accesoColumna.hashCode() + " -> N" + actual.hashCode() + ";\n";
                    cadena += "N" + actual.hashCode() + " -> C" + actual.accesoColumna.hashCode() + ";\n";
                }
                /* Conexiones con el resto de nodos */
                if (actual.arriba != null) {
                    cadena += "N" + actual.hashCode() + " -> N" + actual.arriba.hashCode() + ";\n";
                }
                if (actual.abajo != null) {
                    cadena += "N" + actual.hashCode() + " -> N" + actual.abajo.hashCode() + ";\n";
                }
                if (actual.derecha != null) {
                    cadena += "N" + actual.hashCode() + " -> N" + actual.derecha.hashCode() + ";\n";
                }
                if (actual.izquierda != null) {
                    cadena += "N" + actual.hashCode() + " -> N" + actual.izquierda.hashCode() + ";\n";
                }
                actual = actual.derecha;
                j++;
            }

            eFila = eFila.siguiente;
            i++;
        }
        return cadena;
    }
}
