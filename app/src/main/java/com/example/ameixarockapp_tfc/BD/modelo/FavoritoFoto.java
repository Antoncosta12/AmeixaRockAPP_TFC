package com.example.ameixarockapp_tfc.BD.modelo;

public class FavoritoFoto {
    int idFavoritoProducto;
    int idUsuario;
    int idFoto;

    public FavoritoFoto(int idFavoritoProducto, int idUsuario, int idFoto) {
        this.idFavoritoProducto = idFavoritoProducto;
        this.idUsuario = idUsuario;
        this.idFoto = idFoto;
    }

    public FavoritoFoto(int idUsuario, int idFoto) {
        this.idUsuario = idUsuario;
        this.idFoto = idFoto;
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

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
