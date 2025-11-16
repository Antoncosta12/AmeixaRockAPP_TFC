package com.example.ameixarockapp_tfc.BD.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "AmeixaRock.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_FOTO);
        db.execSQL(SQL_CREATE_ENTRIES_NOTICIA);
        db.execSQL(SQL_CREATE_ENTRIES_PRODUCTO);
        db.execSQL(SQL_CREATE_ENTRIES_TALLA);
        db.execSQL(SQL_CREATE_ENTRIES_USUARIO);
        db.execSQL(SQL_CREATE_ENTRIES_FAVORITOPRODUCTO);
        db.execSQL(SQL_CREATE_ENTRIES_FAVORITOFOTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antiguaVersion, int nuevaVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_FOTO);
        db.execSQL(SQL_DELETE_ENTRIES_NOTICIA);
        db.execSQL(SQL_DELETE_ENTRIES_PRODUCTO);
        db.execSQL(SQL_DELETE_ENTRIES_TALLA);
        db.execSQL(SQL_DELETE_ENTRIES_USUARIO);
        db.execSQL(SQL_DELETE_ENTRIES_FAVORITOPRODUCTO);
        db.execSQL(SQL_DELETE_ENTRIES_FAVORITOFOTO);
        onCreate(db);
    }

    private static final String SQL_CREATE_ENTRIES_FOTO =
            "CREATE TABLE " + DBContract.fotoEntry.TABLE_NAME + " (" +
                    DBContract.fotoEntry.COLUMN_NAME_IDFOTO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.fotoEntry.COLUMN_NAME_FOTO + " TEXT," +
                    DBContract.fotoEntry.COLUMN_NAME_EDICION + " INTEGER)";
    private static final String SQL_CREATE_ENTRIES_NOTICIA =
            "CREATE TABLE " + DBContract.noticiaEntry.TABLE_NAME + " (" +
                    DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.noticiaEntry.COLUMN_NAME_TITULO + " TEXT," +
                    DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO + " TEXT," +
                    DBContract.noticiaEntry.COLUMN_NAME_FECHA + " TEXT," +
                    DBContract.noticiaEntry.COLUMN_NAME_FOTO + " TEXT)";
    private static final String SQL_CREATE_ENTRIES_PRODUCTO =
            "CREATE TABLE " + DBContract.productEntry.TABLE_NAME + " (" +
                    DBContract.productEntry.COLUMN_NAME_IDPRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.productEntry.COLUMN_NAME_NOMPROD + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_MATPROD + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_OBSPROD + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_DESCRPROD + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_FOTO + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_PRECIO + " REAL," + //Es el standard de SQLite para double
                    DBContract.productEntry.COLUMN_NAME_CATEGORIA + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_TALLA =
            "CREATE TABLE " + DBContract.tallaEntry.TABLE_NAME + " (" +
                    DBContract.tallaEntry.COLUMN_NAME_IDTALLA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.tallaEntry.COLUMN_NAME_TALLA + " TEXT," +
                    DBContract.tallaEntry.COLUMN_NAME_IDPRODUCTO + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + DBContract.tallaEntry.COLUMN_NAME_IDPRODUCTO + ") REFERENCES " +
                    DBContract.productEntry.TABLE_NAME + "(" + DBContract.productEntry.COLUMN_NAME_IDPRODUCTO + ") ON DELETE CASCADE)";

    private static final String SQL_CREATE_ENTRIES_USUARIO =
            "CREATE TABLE " + DBContract.usuarioEntry.TABLE_NAME + " (" +
                    DBContract.usuarioEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO + " TEXT," +
                    DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER + " TEXT," +
                    DBContract.usuarioEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO + " TEXT," +
                    DBContract.usuarioEntry.COLUMN_NAME_ORIGEN + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_FAVORITOPRODUCTO =
            "CREATE TABLE " + DBContract.favoritoProductoEntry.TABLE_NAME + " (" +
                    DBContract.favoritoProductoEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO + " INTEGER NOT NULL," +
                    DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO + ") REFERENCES " +
                    DBContract.usuarioEntry.TABLE_NAME + "(" + DBContract.usuarioEntry.COLUMN_NAME_ID + ") ON DELETE CASCADE, " +
                    "FOREIGN KEY(" + DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO + ") REFERENCES " +
                    DBContract.productEntry.TABLE_NAME + "(" + DBContract.productEntry.COLUMN_NAME_IDPRODUCTO + ") ON DELETE CASCADE)";

    private static final String SQL_CREATE_ENTRIES_FAVORITOFOTO =
            "CREATE TABLE " + DBContract.favoritoFotoEntry.TABLE_NAME + " (" +
                    DBContract.favoritoFotoEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO + " INTEGER NOT NULL," +
                    DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO + ") REFERENCES " +
                    DBContract.usuarioEntry.TABLE_NAME + "(" + DBContract.usuarioEntry.COLUMN_NAME_ID + ") ON DELETE CASCADE, " +
                    "FOREIGN KEY(" + DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO + ") REFERENCES " +
                    DBContract.fotoEntry.TABLE_NAME + "(" + DBContract.fotoEntry.COLUMN_NAME_IDFOTO + ") ON DELETE CASCADE)";

    private static final String SQL_DELETE_ENTRIES_FOTO =
            "DROP TABLE IF EXISTS " + DBContract.fotoEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_NOTICIA =
            "DROP TABLE IF EXISTS " + DBContract.noticiaEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_PRODUCTO =
            "DROP TABLE IF EXISTS " + DBContract.productEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_TALLA =
            "DROP TABLE IF EXISTS " + DBContract.tallaEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_USUARIO =
            "DROP TABLE IF EXISTS " + DBContract.usuarioEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_FAVORITOPRODUCTO =
            "DROP TABLE IF EXISTS " + DBContract.favoritoProductoEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_FAVORITOFOTO =
            "DROP TABLE IF EXISTS " + DBContract.favoritoFotoEntry.TABLE_NAME;
}
