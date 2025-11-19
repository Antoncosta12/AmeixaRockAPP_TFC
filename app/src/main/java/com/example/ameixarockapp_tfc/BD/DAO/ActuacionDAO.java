package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.Actuacion;
import com.example.ameixarockapp_tfc.BD.modelo.EventoHistoria;

import java.util.ArrayList;
import java.util.List;

public class ActuacionDAO {

    private DBHelper dbHelper;

    public ActuacionDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarActuacion(Actuacion actuacion){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION, actuacion.getId());
        values.put(DBContract.actuacionEntry.COLUMN_NAME_ARTISTA, actuacion.getArtista());
        values.put(DBContract.actuacionEntry.COLUMN_NAME_HORA, actuacion.getHora());
        values.put(DBContract.actuacionEntry.COLUMN_NAME_DIA, actuacion.getDia());
        values.put(DBContract.actuacionEntry.COLUMN_NAME_LUGAR, actuacion.getLugar());

        long newRowId = db.insert(DBContract.actuacionEntry.TABLE_NAME, null, values);
    }

    public void insertarListaActuaciones(List<Actuacion> actuaciones){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(Actuacion actuacion : actuaciones){
            values = new ContentValues();
            values.put(DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION, actuacion.getId());
            values.put(DBContract.actuacionEntry.COLUMN_NAME_ARTISTA, actuacion.getArtista());
            values.put(DBContract.actuacionEntry.COLUMN_NAME_HORA, actuacion.getHora());
            values.put(DBContract.actuacionEntry.COLUMN_NAME_DIA, actuacion.getDia());
            values.put(DBContract.actuacionEntry.COLUMN_NAME_LUGAR, actuacion.getLugar());

            newRowId = db.insert(DBContract.actuacionEntry.TABLE_NAME, null, values);
        }
    }

    public List<Actuacion> obtenerActuaciones() {
        List<Actuacion> listaActuaciones = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION,
                DBContract.actuacionEntry.COLUMN_NAME_ARTISTA,
                DBContract.actuacionEntry.COLUMN_NAME_HORA,
                DBContract.actuacionEntry.COLUMN_NAME_DIA,
                DBContract.actuacionEntry.COLUMN_NAME_LUGAR
        };

        Cursor cursor = db.query(
                DBContract.actuacionEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION));
            String artista = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_ARTISTA));
            String hora = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_HORA));
            String dia = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_DIA));
            String lugar = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_LUGAR));
            listaActuaciones.add(new Actuacion(id, artista, hora, dia, lugar));
        }
        cursor.close();

        return listaActuaciones;
    }

    public Actuacion obtenerActuacionPorId(int idActuacion) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Actuacion actuacion = null;

        String[] columnas = {
                DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION,
                DBContract.actuacionEntry.COLUMN_NAME_ARTISTA,
                DBContract.actuacionEntry.COLUMN_NAME_HORA,
                DBContract.actuacionEntry.COLUMN_NAME_DIA,
                DBContract.actuacionEntry.COLUMN_NAME_LUGAR
        };

        String selection = DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION + " = ?";
        String[] selectionArgs = { String.valueOf(idActuacion) };

        Cursor cursor = db.query(
                DBContract.actuacionEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION));
            String artista = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_ARTISTA));
            String hora = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_HORA));
            String dia = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_DIA));
            String lugar = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_LUGAR));
            actuacion = new Actuacion(id, artista, hora, dia, lugar);
        }
        cursor.close();
        return actuacion;
    }

    public List<Actuacion> obtenerActuacionesPorDia(String diaFiltro) {
        List<Actuacion> listaActuaciones = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION,
                DBContract.actuacionEntry.COLUMN_NAME_ARTISTA,
                DBContract.actuacionEntry.COLUMN_NAME_HORA,
                DBContract.actuacionEntry.COLUMN_NAME_DIA,
                DBContract.actuacionEntry.COLUMN_NAME_LUGAR
        };

        String selection = DBContract.actuacionEntry.COLUMN_NAME_DIA + " = ?";
        String[] selectionArgs = { String.valueOf(diaFiltro) };

        Cursor cursor = db.query(
                DBContract.actuacionEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_IDACTUACION));
            String artista = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_ARTISTA));
            String hora = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_HORA));
            String dia = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_DIA));
            String lugar = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.actuacionEntry.COLUMN_NAME_LUGAR));
            listaActuaciones.add(new Actuacion(id, artista, hora, dia, lugar));
        }
        cursor.close();

        return listaActuaciones;
    }
}
