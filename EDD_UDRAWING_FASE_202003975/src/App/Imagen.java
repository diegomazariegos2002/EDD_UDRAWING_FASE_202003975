package app;

public class Imagen {
    private String tipo;
    private int num_Pasos;
    private int cont_Pasos;
    private Cliente cliente;

    public Imagen(String tipo, int num_pasos, int cont_pasos, Cliente cliente) {
        this.tipo = tipo;
        this.setCliente(cliente);
        this.setNum_Pasos(num_pasos);
        this.setCont_Pasos(cont_pasos);
    }

    public void restar_Cont_Pasos(){
        this.cont_Pasos--;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNum_Pasos() {
        return num_Pasos;
    }

    public void setNum_Pasos(int num_Pasos) {
        this.num_Pasos = num_Pasos;
    }

    public int getCont_Pasos() {
        return cont_Pasos;
    }

    public void setCont_Pasos(int cont_Pasos) {
        this.cont_Pasos = cont_Pasos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
