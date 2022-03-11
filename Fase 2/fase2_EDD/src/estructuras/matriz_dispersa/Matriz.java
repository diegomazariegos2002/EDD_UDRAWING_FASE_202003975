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
public class Matriz<E> {

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
     * ===============================================Métodos propios del
     * proyecto=======================================
     */
    /**
     * Método para crear el fichero .Dot de mi Matriz con las conexiones y
     * encabezados. Dot.
     *
     * @param nombreFichero
     */
    public void crearFicheroDot_MatrizSinConexiones(String nombreFichero) {
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
            fichero = new FileWriter("./" + nombreFichero + ".neato");
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
        gv.dibujar("neato", "-Tpng", "./" + nombreFichero + ".neato", "./" + nombreFichero + ".png");
    }

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
                 cadena += "N"+actual.fila+""+actual.columna+" [label = \"\",width = 1, height = 1,"
                         + " style = filled, fillcolor = \""+actual.colorNodo+"\", "
                         + "pos = \""+actual.fila+",-"+actual.columna+"!\" ]; \n";
                actual = actual.derecha;
            }
            eFila = eFila.siguiente;
        }
        return cadena;
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
                while (actual.derecha != null) {
                    if (nuevo.columna < actual.derecha.columna) {
                        nuevo.derecha = actual.derecha;
                        actual.derecha.izquierda = nuevo;
                        nuevo.izquierda = actual;
                        actual.derecha = nuevo;
                        break;
                    }
                    actual = actual.derecha;
                }
                if (actual.derecha == null) {
                    actual.derecha = nuevo;
                    nuevo.izquierda = actual;
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
                while (actual.abajo != null) {
                    if (nuevo.fila < actual.abajo.fila) {
                        nuevo.abajo = actual.abajo;
                        actual.abajo.arriba = nuevo;
                        nuevo.arriba = actual;
                        actual.abajo = nuevo;
                        break;
                    }
                    actual = actual.abajo;
                }
                if (actual.abajo == null) {
                    actual.abajo = nuevo;
                    nuevo.arriba = actual;
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
     * Método para crear el fichero .Dot de mi Matriz con las conexiones y
     * encabezados. Dot.
     *
     * @param nombreFichero
     */
    public void crearFicheroDot_MatrizConexiones(String nombreFichero) {
        //Parte del String o texto que va a llevar el fichero
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();
        dot.append("/* DIEGO ANDRÉ MAZARIEGOS BARRIENTOS */\n");
        dot.append("digraph Sparce_Matrix { \n");
        dot.append("node [shape=box] \n");
        dot.append("\n");
        dot.append("/* La matriz se envía al grupo 1 */\n");
        dot.append("Mt[ label = \"Matriz\", width = 1.5, group = 1 ];\n");
        dot.append("\n");

        /**
         * Parte de declaración de los nodos columna.
         */
        dot.append("//............ ............ ............ ............ COLUMNAS \n");
        dot.append("/* se incrementan los grupos porque son columnas */ \n");
        String nodosColumna = generarNodosColumna();
        dot.append(nodosColumna);
        dot.append("\n");

        /**
         * Parte de agregar las conexiones entre columnas.
         */
        dot.append("//............ Enlaces de las columnas\n");
        String conexionesColumna = generarConexionesColumna();
        dot.append(conexionesColumna);
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
         * Parte de agregar las conexiones entre filas.
         */
        String conexionesFila = generarConexionesFila();
        dot.append(conexionesFila);

        /**
         * Parte de agregar los nodos a utilizar con su respectiva posición.
         */
        dot.append("\n//............ ............DECLARACIÓN NODOS POSICIÓN............................\n");
        String NodosPosicion = generarNodosPosicion();
        dot.append(NodosPosicion);

        /**
         * Parte agregar las conexiones de los nodos tanto con los encabezados
         * como entre ellos.
         */
        dot.append("\n//............ ............ Enlaces de los nodos\n");
        String conexionesNodos = generarConexionesNodos();
        dot.append(conexionesNodos);

        dot.append("    { rank = same;}\n");
        dot.append("}");

        FileWriter fichero = null;
        PrintWriter pw = null;
        //Parte de la creación de un fichero
        try {
            fichero = new FileWriter("./" + nombreFichero + ".dot");
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
        gv.dibujar("dot", "-Tpng", "./" + nombreFichero + ".dot", "./" + nombreFichero + ".png");
    }

    /*====================================Métodos auxiliares para generar la gráfica de mi matriz================================*/
    private String generarNodosColumna() {
        String cadena = "";
        NodoMatriz_Encabezado actualColumna = this.eColumnas.primero;
        int i = 0;
        while (actualColumna != null) {
            cadena += "C" + actualColumna.id + " [label = \"Column " + actualColumna.id + "\"    pos = \"5.3,3.5!\" width = 1.5 group = " + (actualColumna.id+1) + " ];\n";
            i++;
            actualColumna = actualColumna.siguiente;
        }
        return cadena;
    }

    private String generarConexionesColumna() {
        String cadena = "";
        String cadenaRank = "// Posicionando en el mismo nivel \n";
        cadenaRank += "{ rank = same; Mt; ";
        NodoMatriz_Encabezado actualColumna = this.eColumnas.primero;
        int i = 0;
        while (actualColumna.siguiente != null) {
            if (i == 0) {
                cadena += "Mt -> C" + actualColumna.id + "; /*Enlace primer nodo Columna con el objeto Matriz*/\n";
            }
            cadena += "C" + actualColumna.id + " -> C" + actualColumna.siguiente.id + ";\n";
            cadena += "C" + actualColumna.siguiente.id + " -> C" + actualColumna.id + ";\n";

            cadenaRank += "C" + actualColumna.id + "; ";
            i++;
            actualColumna = actualColumna.siguiente;
        }
        cadenaRank += "C" + actualColumna.id + "; }\n";
        cadena += cadenaRank;
        return cadena;
    }

    private String generarNodosFila() {
        String cadena = "";
        NodoMatriz_Encabezado actualFila = this.eFilas.primero;
        int i = 0;
        while (actualFila != null) {
            cadena += "F" + actualFila.id + " [label = \"Row " + actualFila.id + "\"    pos = \"5.3,3.5!\" width = 1.5 group = 0 ];\n";
            i++;
            actualFila = actualFila.siguiente;
        }
        return cadena;
    }

    private String generarConexionesFila() {
        String cadena = "//............ enlaces de las filas \n";
        NodoMatriz_Encabezado actualFila = this.eFilas.primero;
        int i = 0;
        while (actualFila.siguiente != null) {
            if (i == 0) {
                cadena += "Mt -> F" + actualFila.id + "; /*Enlace primer nodo Columna con el objeto Matriz*/\n";
            }
            cadena += "F" + actualFila.id + " -> F" + actualFila.siguiente.id + ";\n";
            cadena += "F" + actualFila.siguiente.id + " -> F" + actualFila.id + ";\n";

            i++;
            actualFila = actualFila.siguiente;
        }
        return cadena;
    }

    private String generarNodosPosicion() {
        String cadena = "";
        String cadenaConexiones = "//............ ............ Enlaces de los nodos \n";
        NodoMatriz_Encabezado eFila = eFilas.primero;
        while (eFila != null) {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            while (actual != null) {
                cadena += "N" + actual.fila + "" + actual.columna + " [label = \"\", style = filled, fillcolor = \"" + actual.colorNodo + "\", width = 1.5, group = " + (actual.columna+1) + " ]; \n";

                actual = actual.derecha;
            }

            eFila = eFila.siguiente;
        }
        return cadena;
    }

    private String generarConexionesNodos() {
        String conexionesNodosAccesoFila = "\n//Conexiones FILAS y ACCESO NODO \n";
        String conexionesNodosAccesoColumna = "\n//Conexiones COLUMNAS y ACCESO NODO \n";
        String conexionesNodosPosicion = "\n//Conexiones NODOS POSICION \n";
        String conexionesNodos = "";
        String rank = "";
        /* Parte para manejar las conexiones de los nodos Acceso FILA */
        NodoMatriz_Encabezado eFila = this.eFilas.primero;
        while (eFila != null) {
            NodoMatriz_Posicion actual = eFila.accesoNodo;
            while (actual != null) {
                /* Si el nodo es un nodo de Acceso FILA */
                if (actual.accesoFila != null) {
                    rank += "{ rank = same; F" + actual.fila + ";";
                    conexionesNodosAccesoFila += "F" + actual.fila + " -> N" + actual.fila + "" + actual.columna + ";\n";
                    conexionesNodosAccesoFila += "N" + actual.fila + "" + actual.columna + " -> F" + actual.fila + ";\n";
                    rank += "N" + actual.fila + "" + actual.columna + ";";
                    rank += " }\n";
                }
                /* Si el nodo es un nodo de Acceso COLUMNA */
                if (actual.accesoColumna != null) {
                    conexionesNodosAccesoColumna += "C" + actual.columna + " -> N" + actual.fila + "" + actual.columna + ";\n";
                    conexionesNodosAccesoColumna += "N" + actual.fila + "" + actual.columna + " -> C" + actual.columna + ";\n";
                }

                /* Conexiones con el resto de nodos */
                if (actual.arriba != null) {
                    conexionesNodosPosicion += "N" + actual.fila + "" + actual.columna + " -> N" + actual.arriba.fila + "" + actual.arriba.columna + ";\n";
                }
                if (actual.abajo != null) {
                    conexionesNodosPosicion += "N" + actual.fila + "" + actual.columna + " -> N" + actual.abajo.fila + "" + actual.abajo.columna + ";\n";
                }
                if (actual.derecha != null) {
                    conexionesNodosPosicion += "N" + actual.fila + "" + actual.columna + " -> N" + actual.derecha.fila + "" + actual.derecha.columna + ";\n";
                    rank += "{ rank = same; N" + actual.fila + "" + actual.columna + ";";
                    rank += "N" + actual.derecha.fila + "" + actual.derecha.columna + ";";
                    rank += " }\n";
                }
                if (actual.izquierda != null) {
                    conexionesNodosPosicion += "N" + actual.fila + "" + actual.columna + " -> N" + actual.izquierda.fila + "" + actual.izquierda.columna + ";\n";
                    rank += "{ rank = same; N" + actual.fila + "" + actual.columna + ";";
                    rank += "N" + actual.izquierda.fila + "" + actual.izquierda.columna + ";";
                    rank += " }\n";
                }
                actual = actual.derecha;
            }
            eFila = eFila.siguiente;
        }
        conexionesNodos += conexionesNodosAccesoFila;
        conexionesNodos += rank;
        conexionesNodos += conexionesNodosAccesoColumna;
        conexionesNodos += conexionesNodosPosicion;
        return conexionesNodos;
    }

}
