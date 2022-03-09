package app;

import estructuras.matriz_dispersa.Matriz;

/**
 * Clase Main donde ejecuto todo el programa principal.
 * @author Melissa
 */
public class Main {
    public static void main(String[] args) {
        Matriz<Integer> matriz = new Matriz<>();
        matriz.insertar(0, 3, 22);
        matriz.insertar(0, 1, 2);
        matriz.insertar(1, 1, 0);
        matriz.insertar(2, 2, 2);
        matriz.insertar(3, 2, 278);
        matriz.insertar(3, 3, 0);
        matriz.insertar(4, 4, 0);
        matriz.insertar(5, 5, 3);
        matriz.insertar(6, 6, 9);
        matriz.insertar(7, 7, 7);
        matriz.insertar(8, 8, 5);
        matriz.insertar(10, 10, 10);
        matriz.mostrarMatrizColumnas();
        matriz.mostrarMatrizFilas();
        matriz.crearFicheroDot_MatrizConexiones("MatrizTarea4");
        System.out.println("Se genero imagen.");
    }
}
