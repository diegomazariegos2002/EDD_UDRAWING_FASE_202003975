package estructuras;

import app.Cliente;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ListaEnlazada<E> {

    //Usando Generics (<E>) para que el nodo pueda guardar cualquier tipo de información.
    public class Nodo {

        private E valor;
        public Nodo siguiente;
        public Nodo anterior;

        //Constructor por defecto en una lista enlazada
        public Nodo(E object1) {
            this.valor = object1;
        }
        //Elementos y constructor necesario para trabajar con ColumnMajor
        private int posicion;

        public Nodo(E object1, int posicion) {
            this.valor = object1;
            this.posicion = posicion;
        }

        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }

        public E getValor() {
            return valor;
        }

        public void setValor(E valor) {
            this.valor = valor;
        }

    }

    private Nodo cabeza = null;
    private Nodo cola = null;
    private int longitud = 0;

    //-------------------------------Metodos Fundamentales de mi lista------------------------------------
    //Métodos de encapsulamiento que me serviran para mostrar los datos.
    public Nodo getCabezaLista() {
        return this.cabeza;
    }

    public Nodo getColaLista() {
        return this.cola;
    }

    public int getlength(){
        return longitud;
    }

    //--------------------------------------Método de inserción de datos--------------------------------
    public void insertElement_AtBeggining(E valor) {
        Nodo nodoNuevo = new Nodo(valor);
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
        Nodo nodoNuevo = new Nodo(valor);
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
            Nodo nodoNuevo = new Nodo(valor);
            Nodo nodoActual = cabeza;

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
    public int getPosition(Nodo nodoBuscar){
        Nodo nodoActual = cabeza;
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

    public Nodo getNodo(int position){
        Nodo nodoActual = cabeza;
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
    public Nodo getNodo(E valor){
        Nodo nodoActual = cabeza;
        while(nodoActual != null){
                if(nodoActual.valor == valor){
                    return nodoActual;
                }
            nodoActual = nodoActual.siguiente;
        }

        return null;
    }

    //Extra de busqueda (Clase Cliente) se busca por medio de su atributo id
    public Nodo getNodoEspecifico(int id_Busqueda){
        Nodo nodoActual = cabeza;
        while(nodoActual != null){
            if(((Cliente)nodoActual.valor).getId() == id_Busqueda){
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
        Nodo nodoEliminado = getNodo(position);
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

    public void deleteElement_Nodo(Nodo nodoExtraer){
        Nodo nodoActual = cabeza;
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

    public Nodo extractElement_AtBeggining(){
        Nodo nodoExtraer = getNodo(0);
        deleteElement_AtBeggining();
        return nodoExtraer;
    }

    public Nodo extractElement_AtEnding(){
        Nodo nodoExtraer = getNodo(longitud-1);
        deleteElement_AtEnding();
        return nodoExtraer;
    }

    public Nodo extractElement_AtPosition(int position){
        Nodo nodoExtraer = getNodo(position);
        deleteElement_AtPosition(position);
        return nodoExtraer;
    }

    public Nodo extractElementValor(E valor){
        Nodo nodoExtraer = getNodo(valor);
        deleteElement_Nodo(nodoExtraer);
        return  nodoExtraer;
    }

    //----------------------------------------------------Métodos de modificación----------------------------------
    public void modifyElement_AtBeggining(E valor){
        Nodo nodoModificar = getNodo(0);
        nodoModificar.valor = valor;
    }

    public void modifyElement_AtEnding(E valor){
        Nodo nodoModificar = getNodo(longitud-1);
        nodoModificar.valor = valor;
    }

    public void modifyElement_AtPosition(E valor, int position){
        Nodo nodoModificar = getNodo(position);
        nodoModificar.valor = valor;
    }

    //--------------------------------Métodos de ordenamiento de la lista enlazada (Clase cliente) ----------------------------------------
    public void ordenar_InsertionSort_MenorMayorBw(){
        for (int i = 0; i < longitud; i++) {
            int min = i;
            for (int j = i + 1; j < longitud; j++) {
                if (((Cliente)this.getNodo(j).getValor()).getNum_Img_Bw() < ((Cliente)this.getNodo(min).getValor()).getNum_Img_Bw()){
                    min = j;
                }
            }
            E aux = (this.getNodo(i).getValor());
            this.modifyElement_AtPosition(this.getNodo(min).getValor(), i);
            this.modifyElement_AtPosition(aux, min);
        }
    }

    public void ordenar_InsertionSort_MayorMenorColor(){
        for (int i = 0; i < longitud; i++) {
            int max = i;
            for (int j = i + 1; j < longitud; j++) {
                if (((Cliente)this.getNodo(j).getValor()).getNum_Img_Color() > ((Cliente)this.getNodo(max).getValor()).getNum_Img_Color()){
                    max = j;
                }
            }
            E aux = (this.getNodo(i).getValor());
            this.modifyElement_AtPosition(this.getNodo(max).getValor(), i);
            this.modifyElement_AtPosition(aux, max);
        }
    }

    public void ordenar_InsertionSort_MayorMenorPasos(){
        for (int i = 0; i < longitud; i++) {
            int max = i;
            for (int j = i + 1; j < longitud; j++) {
                if (((Cliente)this.getNodo(j).getValor()).getCont_Pasos_Sistema() > ((Cliente)this.getNodo(max).getValor()).getCont_Pasos_Sistema()){
                    max = j;
                }
            }
            E aux = (this.getNodo(i).getValor());
            this.modifyElement_AtPosition(this.getNodo(max).getValor(), i);
            this.modifyElement_AtPosition(aux, max);
        }
    }

    //--------------------------------Métodos para imprimir en un archivo .dot (Graphviz)--------------------------------
    //Nota: Impresión de todas las listas en un archivo cada sección del métod//Parte del String o texto que va a llevar el fichero
    //        // (en este caso un archivo .dot)
    //        StringBuilder dot = new StringBuilder();
    //
    //        dot.append("digraph G { \n");
    //        dot.append("node[shape = box]; \n");
    //        String nombresNodos = "";
    //        String conexiones = "";
    //        Nodo actual = this.cabeza;
    //        int i = 0;
    //        while (actual !=  null){
    //            nombresNodos += "Nodo" + actual.hashCode() + "[label="+i+"]; \n";
    //            // Aquí es el punto donde se define si el método es para una lista simple, doble, etc...
    //            if(actual.siguiente != null){
    //                conexiones += String.format("Nodo%d -> Nodo%d \n", actual.hashCode(), actual.siguiente.hashCode());
    //            }
    //            i++;
    //            actual = actual.siguiente;
    //        }
    //
    //        dot.append(nombresNodos);
    //        dot.append(conexiones);
    //        dot.append("rankdir = TB;\n");
    //        dot.append("}");
    //
    //
    //        FileWriter fichero = null;
    //        PrintWriter pw = null;
    //        //Parte de la creación de un fichero
    //        try
    //        {
    //            fichero = new FileWriter("./"+nombreFichero+".dot");
    //            pw = new PrintWriter(fichero);
    //
    //            pw.println(dot);
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        } finally {
    //            try {
    //                // Nuevamente aprovechamos el finally para
    //                // asegurarnos que se cierra el fichero.
    //                if (null != fichero)
    //                    fichero.close();
    //            } catch (Exception e2) {
    //                e2.printStackTrace();
    //            }
    //        }o específica para cada lista, pila, cola, etc...
    public void crearFicheroDot_ListaSimple(String nombreFichero){

        dibujar("./"+nombreFichero+".dot", "./"+nombreFichero+".svg");
    }

    //Método para pasar del archivo .dot a Imagen(png, jpg, etc...)
    public void dibujar( String direccionDot, String direccionSvg ){
        try
        {
            ProcessBuilder pbuilder;
            /*
             * Realiza la construccion del comando
             * en la linea de comandos esto es:
             * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder( "dot", "-Tsvg", "-o", direccionSvg, direccionDot );
            pbuilder.redirectErrorStream( true );
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception e) { e.printStackTrace(); }
    }
    //Método para abrir el archivo .svg generado por graphviz directamente desde el programa
    public void abrirarchivo(String archivo){

        try {

            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

        }catch (Exception ex) {

            System.out.println(ex);

        }

    }

    //Imprimir para una clase Cliente OJO
    public void crearFicheroDot_DatosCliente(String nombreFichero, Cliente cliente){
        //Parte del String o texto que va a llevar el fichero
        // (en este caso un archivo .dot)
        StringBuilder dot = new StringBuilder();
        String nombresNodos = "";
        dot.append("digraph G { \n");
        dot.append("subgraph cluster_Clientes_Datos{ \n");
        dot.append("label=\"DATOS DEL CLIENTE\"; \n");
        dot.append("bgcolor=\"darkseagreen1\"; \n");
        dot.append("node[shape = folder]; \n");

        nombresNodos += "Nodo" + cliente.hashCode() + "[label=\"Cliente "+cliente.getId()+"\\n "+cliente.getNombre()+" \\nImagenes Color: "+cliente.getNum_Img_Color()+"\\nImagenes B&W: "+cliente.getNum_Img_Bw()+"\\nPasos en el sistema: "+cliente.getCont_Pasos_Sistema()+"\"]; \n";

        dot.append(nombresNodos);
        dot.append("}");
        dot.append("rankdir = TB;\n");
        dot.append("}");

        FileWriter fichero = null;
        PrintWriter pw = null;
        //Parte de la creación de un fichero
        try
        {
            fichero = new FileWriter("./"+nombreFichero+".dot");
            pw = new PrintWriter(fichero);

            pw.println(dot);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        dibujar("./"+nombreFichero+".dot", "./"+nombreFichero+".svg");
    }

    //-------------------------------Elementos para trabajar con ColumnMajor------------------
    private int[] dimensiones;

    //inicializar el array con n dimensiones
    public void insertarDimensiones(int[] dimensiones) {
        this.dimensiones = dimensiones;
    }

    public void insertarColumnMajor(E object1, int[] indices) {
        int posicion = 0;
        //primer paso para insertar un lemento es calcular su posición
        posicion = calculoColumnMajor((dimensiones.length - 1), indices);
        //segundo paso insertar el nodo en la posición acorde en la lista enlazada.
        Nodo nodoNuevo = new Nodo(object1, posicion);
        if (cabeza == null) {
            cabeza = nodoNuevo;
            cola = nodoNuevo;
            longitud++;
        } else {
            Nodo nodoActual = cabeza;
            while (nodoActual.getPosicion() < nodoNuevo.getPosicion()) {
                nodoActual = nodoActual.siguiente;
            }
            if (nodoActual.getPosicion() == nodoNuevo.getPosicion()) {
                nodoNuevo.siguiente = nodoActual.siguiente;
                nodoNuevo.anterior = nodoActual.anterior;
                nodoActual = nodoNuevo;
            } else {//osea que si no son iguales nodo nuevo es un nodo con una posición mayor
                nodoNuevo.anterior = nodoActual;
                nodoNuevo.siguiente = nodoActual.siguiente;
                if (nodoActual.siguiente != null) {
                    nodoActual.siguiente.anterior = nodoNuevo;
                }
                nodoActual.siguiente = nodoNuevo;
                if (nodoActual == cola) {
                    cola = nodoNuevo;
                }
                longitud++;
            }

        }
    }

    //Método recursivo para calcular la posición en ColumnMajor
    public int calculoColumnMajor(int index, int[] indices) {
        if (index != 0) {
            return indices[index] + dimensiones[index] * calculoColumnMajor(index - 1, indices);
        }
        return indices[0];
    }

}