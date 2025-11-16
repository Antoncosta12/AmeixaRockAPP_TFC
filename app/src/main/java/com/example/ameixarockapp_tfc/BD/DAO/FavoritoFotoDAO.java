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

//    public void insertarTallasEnProducto(List<Talla> tallas){
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        long newRowId;
//        ContentValues values;
//
//        for(Talla talla : tallas){
//            values = new ContentValues();
//            values.put(DBContract.tallaEntry.COLUMN_NAME_TALLA, talla.getTalla());
//            values.put(DBContract.tallaEntry.COLUMN_NAME_IDPRODUCTO, talla.getIdProducto());
//
//            newRowId = db.insert(DBContract.tallaEntry.TABLE_NAME, null, values);
//        }
//    }
//
//    public List<Talla> obtenerTallasProducto(int idProducto) {
//        List<Talla> listaTallas = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        String[] columnas = {
//                DBContract.tallaEntry.COLUMN_NAME_IDTALLA,
//                DBContract.tallaEntry.COLUMN_NAME_TALLA,
//                DBContract.tallaEntry.COLUMN_NAME_IDPRODUCTO,
//        };
//
//        String selection = DBContract.tallaEntry.COLUMN_NAME_IDPRODUCTO + " = ?";
//        String[] selectionArgs = { String.valueOf(idProducto) };
//
//        Cursor cursor = db.query(
//                DBContract.tallaEntry.TABLE_NAME,
//                columnas,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//
//        while(cursor.moveToNext()) {
//            int idTalla = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.tallaEntry.COLUMN_NAME_IDTALLA));
//            String talla = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.tallaEntry.COLUMN_NAME_TALLA));
//            int idProd = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.tallaEntry.COLUMN_NAME_IDPRODUCTO));
//
//            listaTallas.add(new Talla(idTalla, talla, idProd));
//        }
//        cursor.close();
//
//        return listaTallas;
//    }

}
