package app;

import estructuras.matriz_dispersa.Matriz;

/**
 * Clase Main donde ejecuto todo el programa principal.
 * @author Melissa
 */
public class Main {
    public static void main(String[] args) {
        Matriz<Integer> matriz = new Matriz<>();
        
        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 10; j++) {
                 matriz.insertar(i, j, i*j, "#FF037"+i);
            }
        }
       

        matriz.mostrarMatrizColumnas();
        matriz.mostrarMatrizFilas();
        matriz.crearFicheroNeato_MatrizConexiones("MatrizPrueba");
        System.out.println("Se genero imagen MATRIZ.");
        matriz.crearFicheroNeato_MatrizSinConexiones("MatrizSinConexiones");
        System.out.println("Se genero imagen MATRIZ SIN");
        
        Modulo_Admin ma = new Modulo_Admin();
        ma.setVisible(true);
    }
}
