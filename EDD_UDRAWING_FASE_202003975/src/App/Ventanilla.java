package app;
import estructuras.ListaEnlazada;

public class Ventanilla {
    private int id_Ventanilla;
    private ListaEnlazada<Imagen> pila_Imagenes;
    private Cliente cliente;
    private boolean ocupada;

    public Ventanilla(int id_Ventanilla) {
        this.setOcupada(false);
        this.setId_Ventanilla(id_Ventanilla);
        this.setPila_Imagenes(new ListaEnlazada<>());
        this.setCliente(null);
    }


    public int getId_Ventanilla() {
        return id_Ventanilla;
    }

    public void setId_Ventanilla(int id_Ventanilla) {
        this.id_Ventanilla = id_Ventanilla;
    }

    public ListaEnlazada<Imagen> getPila_Imagenes() {
        return pila_Imagenes;
    }

    public void setPila_Imagenes(ListaEnlazada<Imagen> pila_Imagenes) {
        this.pila_Imagenes = pila_Imagenes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
