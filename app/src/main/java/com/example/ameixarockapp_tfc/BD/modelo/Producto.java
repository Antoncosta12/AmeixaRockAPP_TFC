package com.example.ameixarockapp_tfc.BD.modelo;

public class Producto {
    private int id;
    private String nombreProducto;
    private String descripProducto;
    private String foto;
    private double precio;
    private String tallas;

    public Producto(int id, String nombreProducto, String descripProducto, String foto, double precio, String tallas) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.descripProducto = descripProducto;
        this.foto = foto;
        this.precio = precio;
        this.tallas = tallas;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombreProducto() {return nombreProducto;}
    public void setNombreProducto(String nombreProducto) {this.nombreProducto = nombreProducto;}
    public String getDescripProducto() {return descripProducto;}
    public void setDescripProducto(String descripProducto) {this.descripProducto = descripProducto;}
    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}
    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}
    public String getTallas() {return tallas;}
    public void setTallas(String tallas) {this.tallas = tallas;}
}
