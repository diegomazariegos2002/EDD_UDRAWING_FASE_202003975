package app;

public class Cliente {
    private int id;
    private String nombre;
    private int img_color;
    private int img_bw;
    private int cont_Pasos_Sistema;
    private int cont_img_color;
    private int cont_img_bw;
    private int num_Img_Color;
    private int num_Img_Bw;

    public Cliente(int id, String nombre, int img_color, int img_bw) {
        this.id = id;
        this.nombre = nombre;
        this.img_color = img_color;
        this.img_bw = img_bw;
        this.cont_Pasos_Sistema = 0;
        this.cont_img_color = img_color;
        this.cont_img_bw = img_bw;
        this.setNum_Img_Color(img_color);
        this.setNum_Img_Bw(img_bw);
    }



    public void sumar_Cont_Img_Color(){
        this.cont_img_color++;
    }

    public void sumar_Cont_Img_Bw(){
        this.cont_img_bw++;
    }

    public void restar_Cont_Img_Color(){
        this.cont_img_color--;
    }

    public void restar_Cont_Img_Bw(){
        this.cont_img_bw--;
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


    public int getCont_Pasos_Sistema() {
        return cont_Pasos_Sistema;
    }

    public void setCont_Pasos_Sistema(int cont_Pasos_Sistema) {
        this.cont_Pasos_Sistema = cont_Pasos_Sistema;
    }

    public void sumarPaso(){this.cont_Pasos_Sistema++;}

    public int getCont_img_color() {
        return cont_img_color;
    }

    public void setCont_img_color(int cont_img_color) {
        this.cont_img_color = cont_img_color;
    }

    public int getCont_img_bw() {
        return cont_img_bw;
    }

    public void setCont_img_bw(int cont_img_bw) {
        this.cont_img_bw = cont_img_bw;
    }

    public int getNum_Img_Color() {
        return num_Img_Color;
    }

    public void setNum_Img_Color(int num_Img_Color) {
        this.num_Img_Color = num_Img_Color;
    }

    public int getNum_Img_Bw() {
        return num_Img_Bw;
    }

    public void setNum_Img_Bw(int num_Img_Bw) {
        this.num_Img_Bw = num_Img_Bw;
    }
}
