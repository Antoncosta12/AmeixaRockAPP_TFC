package com.example.ameixarockapp_tfc.BD.BD;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract(){}

    public static abstract class productEntry implements BaseColumns {
        public static final String TABLE_NAME = "Producto";
        public static final String COLUMN_NAME_IDPRODUCTO = "id";
        public static final String COLUMN_NAME_NOMPROD = "nombreProducto";
        public static final String COLUMN_NAME_DESCRPROD = "descripcProducto";
        public static final String COLUMN_NAME_FOTO = "foto";
        public static final String COLUMN_NAME_PRECIO = "precio";
        public static final String COLUMN_NAME_TALLAS = "tallas";
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

    //QUEDA COMENTADO A LA ESPERA DE SABER COMO VOY A HACER LA BD, PARA ESTO IGUAL ES MEJOR EXTERNA
//    public abstract class usuarioEntry implements BaseColumns {
//        public static final String TABLE_NAME = "Usuario";
//        public static final String COLUMN_NAME_ID = "id";
//        public static final String COLUMN_NAME_TITULO = "titulo";
//        public static final String COLUMN_NAME_CONTENIDO = "contenido";
//        public static final String COLUMN_NAME_FECHA = "fecha";
//        public static final String COLUMN_NAME_FOTO = "foto";
//    }
}
