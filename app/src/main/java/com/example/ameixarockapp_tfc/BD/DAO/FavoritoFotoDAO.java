package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoFoto;
import com.example.ameixarockapp_tfc.BD.modelo.Talla;

import java.util.ArrayList;
import java.util.List;

public class FavoritoFotoDAO {
    private DBHelper dbHelper;

    public FavoritoFotoDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarFotoFavoritos(FavoritoFoto favoritoFoto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO, favoritoFoto.getIdUsuario());
        values.put(DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO, favoritoFoto.getIdFoto());

        long newRowId = db.insert(DBContract.favoritoFotoEntry.TABLE_NAME, null, values);
    }

  public void insertarFavoritosEnFoto(List<FavoritoFoto> favoritoFotos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(FavoritoFoto favFoto : favoritoFotos){
            values = new ContentValues();
            values.put(DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO, favFoto.getIdUsuario());
            values.put(DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO, favFoto.getIdFoto());

            newRowId = db.insert(DBContract.favoritoFotoEntry.TABLE_NAME, null, values);
        }
    }

    public void eliminarFotoFavorito(int idUsuario, int idFoto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO + " = ? AND " +
                DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO + " = ?";
        String[] selectionArgs = { String.valueOf(idUsuario), String.valueOf(idFoto) };


        long newRowId = db.delete(DBContract.favoritoFotoEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<FavoritoFoto>  obtenerFavoritosFotoUsuario(int idUsuario) {
        List<FavoritoFoto> listaFavsFotos= new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.favoritoFotoEntry.COLUMN_NAME_ID,
                DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO,
                DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO,
        };

        String selection = DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO + " = ?";
        String[] selectionArgs = { String.valueOf(idUsuario) };

        Cursor cursor = db.query(
                DBContract.favoritoFotoEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int idFavorito = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.favoritoFotoEntry.COLUMN_NAME_ID));
            int idUser = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.favoritoFotoEntry.COLUMN_NAME_IDUSUARIO));
            int idFoto = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.favoritoFotoEntry.COLUMN_NAME_IDFOTO));

            listaFavsFotos.add(new FavoritoFoto(idFavorito, idUser, idFoto));
        }
        cursor.close();

        return listaFavsFotos;
    }

}
