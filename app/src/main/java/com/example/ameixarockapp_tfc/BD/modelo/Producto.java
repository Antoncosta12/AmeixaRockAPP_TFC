package com.example.ameixarockapp_tfc.BD.modelo;

public class Producto {
    private int id;
    private String nombreProducto;
    private String materialProducto;
    private String observacionProducto;
    private String descripProducto;
    private String foto;
    private double precio;
    private String categoria;

    public Producto(int id, String nombreProducto, String materialProducto, String observacionProducto,String descripProducto, String foto, double precio, String categoria) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.materialProducto = materialProducto;
        this.observacionProducto = observacionProducto;
        this.descripProducto = descripProducto;
        this.foto = foto;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(String nombreProducto, String materialProducto, String observacionProducto, String descripProducto, String foto, double precio, String categoria) {
        this.nombreProducto = nombreProducto;
        this.materialProducto = materialProducto;
        this.observacionProducto = observacionProducto;
        this.descripProducto = descripProducto;
        this.foto = foto;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMaterialProducto() {
        return materialProducto;
    }

    public void setMaterialProducto(String materialProducto) {
        this.materialProducto = materialProducto;
    }

    public String getObservacionProducto() {
        return observacionProducto;
    }

    public void setObservacionProducto(String observacionProducto) {
        this.observacionProducto = observacionProducto;
    }

    public String getDescripProducto() {
        return descripProducto;
    }

    public void setDescripProducto(String descripProducto) {
        this.descripProducto = descripProducto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
