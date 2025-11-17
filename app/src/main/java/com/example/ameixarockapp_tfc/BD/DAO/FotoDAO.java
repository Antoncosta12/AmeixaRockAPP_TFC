package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

public class FotoDAO {
    private DBHelper dbHelper;

    public FotoDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarFoto(Foto foto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.fotoEntry.COLUMN_NAME_IDFOTO, foto.getId());
        values.put(DBContract.fotoEntry.COLUMN_NAME_FOTO, foto.getFoto());
        values.put(DBContract.fotoEntry.COLUMN_NAME_EDICION, foto.getEdicion());

        long newRowId = db.insert(DBContract.fotoEntry.TABLE_NAME, null, values);
    }

    public void insertarListaFotos(List<Foto> fotos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(Foto foto : fotos){
            values = new ContentValues();
            values.put(DBContract.fotoEntry.COLUMN_NAME_IDFOTO, foto.getId());
            values.put(DBContract.fotoEntry.COLUMN_NAME_FOTO, foto.getFoto());
            values.put(DBContract.fotoEntry.COLUMN_NAME_EDICION, foto.getEdicion());

            newRowId = db.insert(DBContract.fotoEntry.TABLE_NAME, null, values);
        }
    }

    public List<Foto> obtenerFotos() {
        List<Foto> listaFotos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.fotoEntry.COLUMN_NAME_IDFOTO,
                DBContract.fotoEntry.COLUMN_NAME_FOTO,
                DBContract.fotoEntry.COLUMN_NAME_EDICION
        };

        Cursor cursor = db.query(
                DBContract.fotoEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_IDFOTO));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_FOTO));
            int edicion = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_EDICION));
            listaFotos.add(new Foto(id, foto, edicion));
        }
        cursor.close();

        return new ArrayList<>();
    }

    public Foto obtenerFotoPorId(int idFoto) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Foto foto = null;

        String[] columnas = {
                DBContract.fotoEntry.COLUMN_NAME_IDFOTO,
                DBContract.fotoEntry.COLUMN_NAME_FOTO,
                DBContract.fotoEntry.COLUMN_NAME_EDICION
        };

        String selection = DBContract.fotoEntry.COLUMN_NAME_IDFOTO + " = ?";
        String[] selectionArgs = { String.valueOf(idFoto) };

        Cursor cursor = db.query(
                DBContract.fotoEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_IDFOTO));
            String fotografia = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_FOTO));
            int edicion = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_EDICION));
            foto = new Foto(id, fotografia, edicion);
        }
        cursor.close();
        return foto;
    }

    public Foto obtenerFotoPorEdicion(int edicionFoto) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Foto foto = null;

        String[] columnas = {
                DBContract.fotoEntry.COLUMN_NAME_IDFOTO,
                DBContract.fotoEntry.COLUMN_NAME_FOTO,
                DBContract.fotoEntry.COLUMN_NAME_EDICION
        };

        String selection = DBContract.fotoEntry.COLUMN_NAME_EDICION + " = ?";
        String[] selectionArgs = { String.valueOf(edicionFoto) };

        Cursor cursor = db.query(
                DBContract.fotoEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_IDFOTO));
            String fotografia = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_FOTO));
            int edicion = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.fotoEntry.COLUMN_NAME_EDICION));
            foto = new Foto(id, fotografia, edicion);
        }
        cursor.close();
        return foto;
    }

}
