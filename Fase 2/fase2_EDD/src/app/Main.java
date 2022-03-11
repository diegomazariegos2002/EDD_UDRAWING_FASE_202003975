package app;

import estructuras.matriz_dispersa.Matriz;

/**
 * Clase Main donde ejecuto todo el programa principal.
 * @author Melissa
 */
public class Main {
    public static void main(String[] args) {
        /*Matriz<Integer> matriz = new Matriz<>();
        matriz.insertar(0, 0, 4, "#FF0376");
        matriz.insertar(0, 3, 22, "#FF0000");
        matriz.insertar(0, 1, 2, "#FF0000");
        matriz.insertar(2, 1, 2, "#FF0000");
        matriz.insertar(2, 1, 2, "#00FF00");
        matriz.insertar(3, 1, 0, "#FF0000");
        matriz.insertar(2, 2, 2, "#FF0000");
        matriz.insertar(3, 2, 278, "#FF0000");
        matriz.insertar(3, 3, 0, "#FF0000");
        matriz.insertar(4, 4, 0, "#FF0000");
        matriz.insertar(5, 5, 3, "#FF0000");
        matriz.insertar(6, 6, 9, "#FF0000");
        matriz.insertar(7, 7, 7, "#FF0000");
        matriz.insertar(8, 8, 5, "#FF0000");
        matriz.insertar(10, 10, 10, "#FF0000");
        matriz.mostrarMatrizColumnas();
        matriz.mostrarMatrizFilas();
        matriz.crearFicheroDot_MatrizConexiones("MatrizPrueba");
        System.out.println("Se genero imagen MATRIZ.");
        matriz.crearFicheroDot_MatrizSinConexiones("MatrizSinConexiones");
        System.out.println("Se genero imagen MATRIZ SIN");*/
        Modulo_Admin ma = new Modulo_Admin();
        ma.setVisible(true);
    }
}
