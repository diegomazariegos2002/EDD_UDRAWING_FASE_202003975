package app;

import ventanas.*;
import clases_proyecto.Cliente;

/**
 * Clase Main donde ejecuto todo el programa principal.
 *
 * @author Melissa
 */
public class Main {

    
    
    public static void main(String[] args) {
        Cliente clienteActual = new Cliente(1, "Diego", "1234");
        Modulo_Cliente mc = new Modulo_Cliente(clienteActual);
        mc.setVisible(true);
    }

}
