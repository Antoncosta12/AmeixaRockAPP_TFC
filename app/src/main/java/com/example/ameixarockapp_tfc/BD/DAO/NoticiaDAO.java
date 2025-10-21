package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiaDAO {
    private DBHelper dbHelper;

    public NoticiaDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarNoticia(Noticia noticia){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA, noticia.getId());
        values.put(DBContract.noticiaEntry.COLUMN_NAME_TITULO, noticia.getTitulo());
        values.put(DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO, noticia.getContenido());
        values.put(DBContract.noticiaEntry.COLUMN_NAME_FECHA, noticia.getFecha());
        values.put(DBContract.noticiaEntry.COLUMN_NAME_FOTO, noticia.getFoto());

        long newRowId = db.insert(DBContract.noticiaEntry.TABLE_NAME, null, values);
    }

    public void insertarListaNoticias(List<Noticia> noticias){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(Noticia noticia : noticias){
            values = new ContentValues();
            values.put(DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA, noticia.getId());
            values.put(DBContract.noticiaEntry.COLUMN_NAME_TITULO, noticia.getTitulo());
            values.put(DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO, noticia.getContenido());
            values.put(DBContract.noticiaEntry.COLUMN_NAME_FECHA, noticia.getFecha());
            values.put(DBContract.noticiaEntry.COLUMN_NAME_FOTO, noticia.getFoto());

            newRowId = db.insert(DBContract.noticiaEntry.TABLE_NAME, null, values);
        }
    }

    public List<Noticia> obtenerNoticias() {
        List<Noticia> listaNoticias = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA,
                DBContract.noticiaEntry.COLUMN_NAME_TITULO,
                DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO,
                DBContract.noticiaEntry.COLUMN_NAME_FECHA,
                DBContract.noticiaEntry.COLUMN_NAME_FOTO
        };

        Cursor cursor = db.query(
                DBContract.noticiaEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_TITULO));
            String contenido = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_FECHA));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_FOTO));
            listaNoticias.add(new Noticia(id, titulo, contenido, fecha, foto));
        }
        cursor.close();

        return listaNoticias;
    }

    public Noticia obtenerNoticiaPorId(int idNoticia) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Noticia noticia = null;

        String[] columnas = {
                DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA,
                DBContract.noticiaEntry.COLUMN_NAME_TITULO,
                DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO,
                DBContract.noticiaEntry.COLUMN_NAME_FECHA,
                DBContract.noticiaEntry.COLUMN_NAME_FOTO
        };

        String selection = DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA + " = ?";
        String[] selectionArgs = { String.valueOf(idNoticia) };

        Cursor cursor = db.query(
                DBContract.noticiaEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_IDNOTICIA));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_TITULO));
            String contenido = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_CONTENIDO));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_FECHA));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.noticiaEntry.COLUMN_NAME_FOTO));
            noticia = new Noticia(id, titulo, contenido, fecha, foto);
        }
        cursor.close();
        return noticia;
    }
}
