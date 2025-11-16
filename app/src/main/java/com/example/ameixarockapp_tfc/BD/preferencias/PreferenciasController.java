package com.example.ameixarockapp_tfc.BD.preferencias;

import android.content.SharedPreferences;

import com.example.ameixarockapp_tfc.BD.modelo.Usuario;

public class PreferenciasController {

    public static Usuario guardarLoginUsuario(SharedPreferences preferences, int idUser, String nomUser){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("idUsuario", idUser);
        editor.putString("nomUser", nomUser);
        editor.apply();

        Usuario usuario = new Usuario(idUser, nomUser);
        return usuario;
    }

    public static Usuario cargarUsuarioLogged(SharedPreferences preferences){
        int idUser = preferences.getInt("idUsuario", 0);
        if(idUser == 0){
            return null;
        }

        String nomUser = preferences.getString("nomUser", "NomDefault");

        Usuario usuario = new Usuario(idUser, nomUser);
        return usuario;
    }

    public static void cerrarSesionUsuario(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
