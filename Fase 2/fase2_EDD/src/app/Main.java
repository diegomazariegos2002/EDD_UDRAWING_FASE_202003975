package app;

import clases_proyecto.Capa;
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
        ArbolB<Cliente> arbolClientes = new ArbolB();
        try {
            ff.vaciarTodoDirectorio("./Clientes");
            ff.crearNuevoDirectorio("./Clientes");
            Login login = new Login(arbolClientes);
            login.setVisible(true);
        } catch (Exception error) {

        }
//        Cliente clientePrueba = new Cliente(1, "Diego", "1234");
//        Capa capa = new Capa(14);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(4);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(15);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(3);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(9);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(18);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(16);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(20);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(7);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(5);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(20);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        capa = new Capa(17);
//        clientePrueba.getArbol_CapasGenerales().insertar(capa);
//        
//        Modulo_ReportesCliente mrc = new Modulo_ReportesCliente(clientePrueba, arbolClientes);
//        mrc.setVisible(true);
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
