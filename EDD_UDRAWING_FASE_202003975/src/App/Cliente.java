package app;

public class Cliente {
    private int id;
    private String nombre;
    private int img_color;
    private int img_bw;

    public Cliente(int id, String nombre, int img_color, int img_bw) {
        this.id = id;
        this.nombre = nombre;
        this.img_color = img_color;
        this.img_bw = img_bw;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImg_color() {
        return img_color;
    }

    public void setImg_color(int img_color) {
        this.img_color = img_color;
    }

    public int getImg_bw() {
        return img_bw;
    }

    public void setImg_bw(int img_bw) {
        this.img_bw = img_bw;
    }
}
