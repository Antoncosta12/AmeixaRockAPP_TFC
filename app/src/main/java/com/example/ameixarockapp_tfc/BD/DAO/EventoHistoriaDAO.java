package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.EventoHistoria;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

public class EventoHistoriaDAO {
    private DBHelper dbHelper;

    public EventoHistoriaDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarEventoHistoria(EventoHistoria evento){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO, evento.getId());
        values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_TITULO, evento.getTitulo());
        values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_DESCRIPCION, evento.getDescripcion());
        values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_FECHA, evento.getFecha());
        values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_FOTO, evento.getFoto());

        long newRowId = db.insert(DBContract.eventoHistoriaEntry.TABLE_NAME, null, values);
    }

    public void insertarListaEventosHistoria(List<EventoHistoria> eventos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(EventoHistoria evento : eventos){
            values = new ContentValues();
            values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO, evento.getId());
            values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_TITULO, evento.getTitulo());
            values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_DESCRIPCION, evento.getDescripcion());
            values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_FECHA, evento.getFecha());
            values.put(DBContract.eventoHistoriaEntry.COLUMN_NAME_FOTO, evento.getFoto());

            newRowId = db.insert(DBContract.eventoHistoriaEntry.TABLE_NAME, null, values);
        }
    }

    public List<EventoHistoria> obtenerEventos() {
        List<EventoHistoria> listaEventos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_TITULO,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_DESCRIPCION,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_FECHA,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_FOTO
        };

        Cursor cursor = db.query(
                DBContract.eventoHistoriaEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_TITULO));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_DESCRIPCION));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_FECHA));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_FOTO));
            listaEventos.add(new EventoHistoria(id, titulo, descripcion, fecha, foto));
        }
        cursor.close();

        return listaEventos;
    }

    public EventoHistoria obtenerEventoPorId(int idEvento) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        EventoHistoria evento = null;

        String[] columnas = {
                DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_TITULO,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_DESCRIPCION,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_FECHA,
                DBContract.eventoHistoriaEntry.COLUMN_NAME_FOTO
        };

        String selection = DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO + " = ?";
        String[] selectionArgs = { String.valueOf(idEvento) };

        Cursor cursor = db.query(
                DBContract.eventoHistoriaEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_IDEVENTO));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_TITULO));
            String descipcion = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_DESCRIPCION));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_FECHA));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.eventoHistoriaEntry.COLUMN_NAME_FOTO));
            evento = new EventoHistoria(id, titulo, descipcion, fecha, foto);
        }
        cursor.close();
        return evento;
    }
}
