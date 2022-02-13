package app;
import estructuras.ListaEnlazada;

public class Ventanilla {
    private int id_Ventanilla;
    private ListaEnlazada<Imagen> pila_Imagenes;

    public Ventanilla(int id_Ventanilla) {
        this.setId_Ventanilla(id_Ventanilla);
        this.setPila_Imagenes(new ListaEnlazada<>());
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
}
