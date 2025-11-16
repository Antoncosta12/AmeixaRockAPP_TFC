package com.example.ameixarockapp_tfc.BD.modelo;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String nomUser;
    private String password;
    private String correoElectronico;
    private String origen;


    public Usuario(int id, String nombreCompleto, String nomUser, String password, String correoElectronico, String origen) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.nomUser = nomUser;
        this.password = password;
        this.correoElectronico = correoElectronico;
        this.origen = origen;
    }

    public Usuario(String nombreCompleto, String nomUser, String password, String correoElectronico, String origen) {
        this.nombreCompleto = nombreCompleto;
        this.nomUser = nomUser;
        this.password = password;
        this.correoElectronico = correoElectronico;
        this.origen = origen;
    }

    public Usuario(int id, String nomUser) {
        this.id = id;
        this.nomUser = nomUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
