package com.example.ameixarockapp_tfc.BD.modelo;

public class FavoritoProducto {
    int idFavoritoProducto;
    int idUsuario;
    int idProducto;

    public FavoritoProducto(int idFavoritoProducto, int idUsuario, int idProducto) {
        this.idFavoritoProducto = idFavoritoProducto;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
    }

    public FavoritoProducto(int idUsuario, int idProducto) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
    }

    public int getIdFavoritoProducto() {
        return idFavoritoProducto;
    }

    public void setIdFavoritoProducto(int idFavoritoProducto) {
        this.idFavoritoProducto = idFavoritoProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
