package com.example.ameixarockapp_tfc.BD.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AmeixaRock.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_FOTO);
        db.execSQL(SQL_CREATE_ENTRIES_NOTICIA);
        db.execSQL(SQL_CREATE_ENTRIES_PRODUCTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antiguaVersion, int nuevaVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_FOTO);
        db.execSQL(SQL_DELETE_ENTRIES_NOTICIA);
        db.execSQL(SQL_DELETE_ENTRIES_PRODUCTO);
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
                    DBContract.productEntry.COLUMN_NAME_DESCRPROD + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_FOTO + " TEXT," +
                    DBContract.productEntry.COLUMN_NAME_PRECIO + " REAL," + //Es el standad más fiable de SQLite para double
                    DBContract.productEntry.COLUMN_NAME_TALLAS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_FOTO =
            "DROP TABLE IF EXISTS " + DBContract.fotoEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_NOTICIA =
            "DROP TABLE IF EXISTS " + DBContract.noticiaEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_PRODUCTO =
            "DROP TABLE IF EXISTS " + DBContract.productEntry.TABLE_NAME;
}
