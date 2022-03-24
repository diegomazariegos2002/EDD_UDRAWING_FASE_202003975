package app;

import ventanas.*;
import clases_proyecto.Cliente;
import java.io.IOException;
import estructuras.arbolB.ArbolB;

/**
 * Clase Main donde ejecuto todo el programa principal.
 *
 * @author Melissa
 */
public class Main {
    static Funciones_Ficheros ff = new Funciones_Ficheros();
    public static void main(String[] args) {
        //Esto para que cada vez que se ejecute el proyecto se vacíe el directorio de Clientes que almacena todo.
        try{
        ff.vaciarTodoDirectorio("./Clientes");
        }catch(Exception error){
            
        }
        ArbolB<Cliente> arbolClientes = new ArbolB();
        try{
            Modulo_Admin ma = new Modulo_Admin(arbolClientes);
            ma.setVisible(true);
        }catch(Exception error){
            
        }
        
//        Cliente clientePrueba = new Cliente(1, "Diego", "1234");
//        
//        try{
//            crearDirectoriosCliente(clientePrueba);
//            Modulo_Cliente mc = new  Modulo_Cliente(clientePrueba);
//            mc.setVisible(true);
//        }catch(Exception e){
//            
//        }
    }
}
