package com.example.ameixarockapp_tfc.BD.modelo;

public class EventoHistoria {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String foto;

    public EventoHistoria(int id, String titulo, String descripcion, String fecha, String foto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.foto = foto;
    }

    public EventoHistoria(String titulo, String contenido, String fecha, String foto) {
        this.titulo = titulo;
        this.descripcion = contenido;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
