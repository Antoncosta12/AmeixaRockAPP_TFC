package com.example.ameixarockapp_tfc.BD.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ameixarockapp_tfc.BD.BD.DBContract;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private DBHelper dbHelper;

    public UsuarioDAO(DBHelper dbHelper) {this.dbHelper = dbHelper;}

    public void insertarUsuario(Usuario usuario){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO, usuario.getNombreCompleto());
        values.put(DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER, usuario.getNomUser());
        values.put(DBContract.usuarioEntry.COLUMN_NAME_PASSWORD, usuario.getPassword());
        values.put(DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO, usuario.getCorreoElectronico());
        values.put(DBContract.usuarioEntry.COLUMN_NAME_ORIGEN, usuario.getOrigen());

        long newRowId = db.insert(DBContract.usuarioEntry.TABLE_NAME, null, values);
    }

    public void insertarListaUsuarios(List<Usuario> usuarios){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        ContentValues values;

        for(Usuario usuario : usuarios){
            values = new ContentValues();
            values.put(DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO, usuario.getNombreCompleto());
            values.put(DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER, usuario.getNomUser());
            values.put(DBContract.usuarioEntry.COLUMN_NAME_PASSWORD, usuario.getPassword());
            values.put(DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO, usuario.getCorreoElectronico());
            values.put(DBContract.usuarioEntry.COLUMN_NAME_ORIGEN, usuario.getOrigen());

            newRowId = db.insert(DBContract.usuarioEntry.TABLE_NAME, null, values);
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBContract.usuarioEntry.COLUMN_NAME_ID,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER,
                DBContract.usuarioEntry.COLUMN_NAME_PASSWORD,
                DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO,
                DBContract.usuarioEntry.COLUMN_NAME_ORIGEN
        };

        Cursor cursor = db.query(
                DBContract.usuarioEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO));
            String nomUser = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_PASSWORD));
            String correoElectronico = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO));
            String origen = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ORIGEN));
            listaUsuarios.add(new Usuario(id, nombre, nomUser, password, correoElectronico, origen));
        }
        cursor.close();

        return listaUsuarios;
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Usuario usuario = null;

        String[] columnas = {
                DBContract.usuarioEntry.COLUMN_NAME_ID,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER,
                DBContract.usuarioEntry.COLUMN_NAME_PASSWORD,
                DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO,
                DBContract.usuarioEntry.COLUMN_NAME_ORIGEN
        };

        String selection = DBContract.usuarioEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = { String.valueOf(idUsuario) };

        Cursor cursor = db.query(
                DBContract.usuarioEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO));
            String nomUser = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_PASSWORD));
            String correoElectronico = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO));
            String origen = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ORIGEN));
            usuario = new Usuario(id, nombre, nomUser, password, correoElectronico, origen);
        }
        cursor.close();
        return usuario;
    }

    public Usuario obtenerUsuarioPorNomUser(String nomUsuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Usuario usuario = null;

        String[] columnas = {
                DBContract.usuarioEntry.COLUMN_NAME_ID,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER,
                DBContract.usuarioEntry.COLUMN_NAME_PASSWORD,
                DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO,
                DBContract.usuarioEntry.COLUMN_NAME_ORIGEN
        };

        String selection = DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER + " = ?";
        String[] selectionArgs = { String.valueOf(nomUsuario) };

        Cursor cursor = db.query(
                DBContract.usuarioEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO));
            String nomUser = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_PASSWORD));
            String correoElectronico = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO));
            String origen = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ORIGEN));
            usuario = new Usuario(id, nombre, nomUser, password, correoElectronico, origen);
        }
        cursor.close();
        return usuario;
    }

    public Usuario obtenerUsuarioPorCorreoElectronico(String correo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Usuario usuario = null;

        String[] columnas = {
                DBContract.usuarioEntry.COLUMN_NAME_ID,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO,
                DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER,
                DBContract.usuarioEntry.COLUMN_NAME_PASSWORD,
                DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO,
                DBContract.usuarioEntry.COLUMN_NAME_ORIGEN
        };

        String selection = DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO + " = ?";
        String[] selectionArgs = { String.valueOf(correo) };

        Cursor cursor = db.query(
                DBContract.usuarioEntry.TABLE_NAME,
                columnas,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBRECOMPLETO));
            String nomUser = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_NOMBREUSER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_PASSWORD));
            String correoElectronico = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_CORREOELECTRONICO));
            String origen = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.usuarioEntry.COLUMN_NAME_ORIGEN));
            usuario = new Usuario(id, nombre, nomUser, password, correoElectronico, origen);
        }
        cursor.close();
        return usuario;
    }
}
