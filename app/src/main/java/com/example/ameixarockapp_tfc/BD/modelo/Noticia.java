package com.example.ameixarockapp_tfc.BD.modelo;

public class Noticia {
    private int id;
    private String titulo;
    private String contenido;
    private String fecha;
    private String foto;

    public Noticia(int id, String titulo, String contenido, String fecha, String foto) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.foto = foto;
    }

    public Noticia(String titulo, String contenido, String fecha, String foto) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
