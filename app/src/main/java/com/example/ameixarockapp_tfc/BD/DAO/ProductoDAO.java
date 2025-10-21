package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private DBHelper dbHelper;

    public ProductoDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarProducto(Producto producto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.productEntry.COLUMN_NAME_IDPRODUCTO, producto.getId());
        values.put(DBContract.productEntry.COLUMN_NAME_NOMPROD, producto.getNombreProducto());
        values.put(DBContract.productEntry.COLUMN_NAME_DESCRPROD, producto.getDescripProducto());
        values.put(DBContract.productEntry.COLUMN_NAME_FOTO, producto.getFoto());
        values.put(DBContract.productEntry.COLUMN_NAME_PRECIO, producto.getPrecio());
        values.put(DBContract.productEntry.COLUMN_NAME_TALLAS, producto.getTallas());

        long newRowId = db.insert(DBContract.productEntry.TABLE_NAME, null, values);
    }

    public void insertarListaProductos(List<Producto> productos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(Producto producto : productos){
            values = new ContentValues();
            values.put(DBContract.productEntry.COLUMN_NAME_IDPRODUCTO, producto.getId());
            values.put(DBContract.productEntry.COLUMN_NAME_NOMPROD, producto.getNombreProducto());
            values.put(DBContract.productEntry.COLUMN_NAME_DESCRPROD, producto.getDescripProducto());
            values.put(DBContract.productEntry.COLUMN_NAME_FOTO, producto.getFoto());
            values.put(DBContract.productEntry.COLUMN_NAME_PRECIO, producto.getPrecio());
            values.put(DBContract.productEntry.COLUMN_NAME_TALLAS, producto.getTallas());

            newRowId = db.insert(DBContract.productEntry.TABLE_NAME, null, values);
        }
    }

    public List<Producto> obtenerProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.productEntry.COLUMN_NAME_IDPRODUCTO,
                DBContract.productEntry.COLUMN_NAME_NOMPROD,
                DBContract.productEntry.COLUMN_NAME_DESCRPROD,
                DBContract.productEntry.COLUMN_NAME_FOTO,
                DBContract.productEntry.COLUMN_NAME_PRECIO,
                DBContract.productEntry.COLUMN_NAME_TALLAS
        };

        Cursor cursor = db.query(
                DBContract.productEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_IDPRODUCTO));
            String nomProd = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_NOMPROD));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_DESCRPROD));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_FOTO));
            double precio = cursor.getDouble(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_PRECIO));
            String tallas = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_TALLAS));
            listaProductos.add(new Producto(id, nomProd, descripcion, foto, precio, tallas));
        }
        cursor.close();

        return new ArrayList<>();
    }

    public Producto obtenerProductoPorId(int idProducto) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Producto producto = null;

        String[] columnas = {
                DBContract.productEntry.COLUMN_NAME_IDPRODUCTO,
                DBContract.productEntry.COLUMN_NAME_NOMPROD,
                DBContract.productEntry.COLUMN_NAME_DESCRPROD,
                DBContract.productEntry.COLUMN_NAME_FOTO,
                DBContract.productEntry.COLUMN_NAME_PRECIO,
                DBContract.productEntry.COLUMN_NAME_TALLAS
        };

        String selection = DBContract.productEntry.COLUMN_NAME_IDPRODUCTO + " = ?";
        String[] selectionArgs = { String.valueOf(idProducto) };

        Cursor cursor = db.query(
                DBContract.productEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_IDPRODUCTO));
            String nomProd = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_NOMPROD));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_DESCRPROD));
            String foto = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_FOTO));
            double precio = cursor.getDouble(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_PRECIO));
            String tallas = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.productEntry.COLUMN_NAME_TALLAS));
            producto = new Producto(id, nomProd, descripcion, foto, precio, tallas);
        }
        cursor.close();
        return producto;
    }

}
