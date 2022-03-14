package app;

import ventanas.*;
import clases_proyecto.Cliente;
import java.io.IOException;


/**
 * Clase Main donde ejecuto todo el programa principal.
 *
 * @author Melissa
 */
public class Main {
    static Funciones_Ficheros ff = new Funciones_Ficheros();
    public static void main(String[] args) {
        Cliente clientePrueba = new Cliente(1, "Diego", "1234");
        
        try{
            crearDirectoriosCliente(clientePrueba);
            Modulo_Cliente mc = new  Modulo_Cliente(clientePrueba);
            mc.setVisible(true);
        }catch(Exception e){
            
        }
    }

    public static void crearDirectoriosCliente(Cliente clienteNuevo) throws IOException{
        ff.borrar_Directorio("./Clientes/Cliente_"+clienteNuevo.getDPI());
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI());
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Imagenes_Con_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Imagenes_Sin_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Neato_Con_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Neato_Sin_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Arbol_AVL_Capas");
        
    }
}
