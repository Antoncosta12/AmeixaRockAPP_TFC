package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoFoto;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoProducto;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.BD.modelo.Talla;

import java.util.ArrayList;
import java.util.List;

public class FavoritoProductoDAO {
    private DBHelper dbHelper;

    public FavoritoProductoDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarProdFavorito(FavoritoProducto favoritoProducto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO, favoritoProducto.getIdUsuario());
        values.put(DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO, favoritoProducto.getIdProducto());

        long newRowId = db.insert(DBContract.favoritoProductoEntry.TABLE_NAME, null, values);
    }

    public void insertarFavoritosEnProd(List<FavoritoProducto> favoritoProductos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(FavoritoProducto favProduct : favoritoProductos){
            values = new ContentValues();
            values.put(DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO, favProduct.getIdUsuario());
            values.put(DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO, favProduct.getIdProducto());

            newRowId = db.insert(DBContract.favoritoProductoEntry.TABLE_NAME, null, values);
        }
    }

    public void eliminarProdFavorito(int idUsuario, int idProducto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO + " = ? AND " +
                DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO + " = ?";
        String[] selectionArgs = { String.valueOf(idUsuario), String.valueOf(idProducto) };


        long newRowId = db.delete(DBContract.favoritoProductoEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<FavoritoProducto>  obtenerFavoritosProdUsuario(int idUsuario) {
        List<FavoritoProducto> listaFavsProductos= new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.favoritoProductoEntry.COLUMN_NAME_ID,
                DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO,
                DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO,
        };

        String selection = DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO + " = ?";
        String[] selectionArgs = { String.valueOf(idUsuario) };

        Cursor cursor = db.query(
                DBContract.favoritoProductoEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int idFavorito = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.favoritoProductoEntry.COLUMN_NAME_ID));
            int idUser = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.favoritoProductoEntry.COLUMN_NAME_IDUSUARIO));
            int idProd = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.favoritoProductoEntry.COLUMN_NAME_IDPRODUCTO));

            listaFavsProductos.add(new FavoritoProducto(idFavorito, idUser, idProd));
        }
        cursor.close();

        return listaFavsProductos;
    }

}
