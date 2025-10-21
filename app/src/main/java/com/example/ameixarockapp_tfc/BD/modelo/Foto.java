package com.example.ameixarockapp_tfc.BD.modelo;

public class Foto {
    private int id;
    private String foto;
    private int edicion;

    public Foto(int id, int edicion, String foto) {
        this.id = id;
        this.edicion = edicion;
        this.foto = foto;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}
    public int getEdicion() {return edicion;}
    public void setEdicion(int edicion) {this.edicion = edicion;}
}
