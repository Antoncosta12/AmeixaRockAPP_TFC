package com.example.ameixarockapp_tfc.BD.modelo;

public class Talla {
    private int idTalla;
    private String talla;
    private int idProducto;

    public Talla(int idProducto, String talla, int idTalla) {
        this.idProducto = idProducto;
        this.talla = talla;
        this.idTalla = idTalla;
    }

    public Talla(String talla, int idProducto) {
        this.talla = talla;
        this.idProducto = idProducto;
    }

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
