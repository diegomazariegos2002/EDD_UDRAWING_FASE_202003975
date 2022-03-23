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
        ArbolB prueba = new ArbolB();
        prueba.insertarEnArbol(0);
        prueba.insertarEnArbol(1);
        prueba.insertarEnArbol(2);
        prueba.insertarEnArbol(3);
        prueba.insertarEnArbol(4);
        prueba.insertarEnArbol(5);
        prueba.insertarEnArbol(6);
        prueba.insertarEnArbol(7);
        prueba.insertarEnArbol(8);
        prueba.insertarEnArbol(9);
        prueba.graficarArbolB("pruebaArbolB", "./", "./");
        
        ArbolB arbolClientes = new ArbolB();
        
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

    public static void crearDirectoriosCliente(Cliente clienteNuevo) throws IOException{
        ff.borrar_Directorio("./Clientes/Cliente_"+clienteNuevo.getDPI());
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI());
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Imagenes_Con_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Imagenes_Sin_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Neato_Con_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Neato_Sin_Conexiones");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Capas/Arbol_ABB_Capas");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes/Neato_Imagenes");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes/Imagenes_Puras");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Imagenes/Arbol_AVL_Imagenes");
        ff.crearNuevoDirectorio("./Clientes/Cliente_"+clienteNuevo.getDPI()+"/Albumes");
    }
}
