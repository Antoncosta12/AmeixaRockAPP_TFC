package com.example.ameixarockapp_tfc.BD.BD;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract(){}

    public static abstract class productEntry implements BaseColumns {
        public static final String TABLE_NAME = "Producto";
        public static final String COLUMN_NAME_IDPRODUCTO = "id";
        public static final String COLUMN_NAME_NOMPROD = "nombreProducto";
        public static final String COLUMN_NAME_MATPROD = "materialProducto";
        public static final String COLUMN_NAME_OBSPROD = "observacionProducto";
        public static final String COLUMN_NAME_DESCRPROD = "descripcProducto";
        public static final String COLUMN_NAME_FOTO = "foto";
        public static final String COLUMN_NAME_PRECIO = "precio";
        public static final String COLUMN_NAME_CATEGORIA = "categoria";
    }

    public static abstract class fotoEntry implements BaseColumns {
        public static final String TABLE_NAME = "Foto";
        public static final String COLUMN_NAME_IDFOTO= "id";
        public static final String COLUMN_NAME_FOTO= "foto";
        public static final String COLUMN_NAME_EDICION = "edicion";
    }

    public static abstract class noticiaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Noticia";
        public static final String COLUMN_NAME_IDNOTICIA = "id";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_CONTENIDO = "contenido";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_FOTO = "foto";
    }

    public static abstract class tallaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Tallas";
        public static final String COLUMN_NAME_IDTALLA = "idtalla";
        public static final String COLUMN_NAME_TALLA = "talla";
        public static final String COLUMN_NAME_IDPRODUCTO = "idproducto";

    }

    public abstract class usuarioEntry implements BaseColumns {
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NOMBRECOMPLETO = "nombreCompleto";
        public static final String COLUMN_NAME_NOMBREUSER = "nomUser";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_CORREOELECTRONICO = "correoElectronico";
        public static final String COLUMN_NAME_ORIGEN = "origen";
    }

    public abstract class favoritoProductoEntry implements BaseColumns {
        public static final String TABLE_NAME = "FavoritoProducto";
        public static final String COLUMN_NAME_ID = "idFavoritoProducto";
        public static final String COLUMN_NAME_IDUSUARIO = "idUsuario";
        public static final String COLUMN_NAME_IDPRODUCTO = "idProducto";
    }

    public abstract class favoritoFotoEntry implements BaseColumns {
        public static final String TABLE_NAME = "FavoritoFoto";
        public static final String COLUMN_NAME_ID = "idFavoritoFoto";
        public static final String COLUMN_NAME_IDUSUARIO = "idUsuario";
        public static final String COLUMN_NAME_IDFOTO = "idFoto";
    }
}
