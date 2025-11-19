package com.example.ameixarockapp_tfc.BD.modelo;

public class Actuacion {
    int id;
    String artista;
    String hora;
    String dia;
    String lugar;

    public Actuacion(int id, String artista, String hora, String dia, String lugar) {
        this.lugar = lugar;
        this.dia = dia;
        this.hora = hora;
        this.artista = artista;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
