package com.example.ameixarockapp_tfc.BD.modelo;

public class Usuario {
    private int id;
    private String nomUser;
    private String correoElectronico;
    private double contraseña;

    public Usuario(double contraseña, String correoElectronico, String nomUser, int id) {
        this.contraseña = contraseña;
        this.correoElectronico = correoElectronico;
        this.nomUser = nomUser;
        this.id = id;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNomUser() {return nomUser;}
    public void setNomUser(String nomUser) {this.nomUser = nomUser;}
    public String getCorreoElectronico() {return correoElectronico;}
    public void setCorreoElectronico(String correoElectronico) {this.correoElectronico = correoElectronico;}
    public double getContraseña() {return contraseña;}
    public void setContraseña(double contraseña) {this.contraseña = contraseña;}
}
