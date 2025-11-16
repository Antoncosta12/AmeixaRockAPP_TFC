package com.example.ameixarockapp_tfc.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.vista.ContactoActivity;
import com.example.ameixarockapp_tfc.vista.HistoriaActivity;
import com.example.ameixarockapp_tfc.vista.HomeActivity;
import com.example.ameixarockapp_tfc.vista.InformacionActivity;
import com.example.ameixarockapp_tfc.vista.LoginActivity;
import com.example.ameixarockapp_tfc.vista.MerchandisingActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void configMenuToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulateral,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Class<?> currentScreen = this.getClass();

        if(id==R.id.HomeScreen) {
            if(currentScreen == HomeActivity.class){
                Toast.makeText(this, "Xa estás na pantalla Home", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            return true;
        } else if (id==R.id.MerchandisingScreen) {
            if(currentScreen == MerchandisingActivity.class){
                Toast.makeText(this, "Xa estás na pantalla Merchandising", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MerchandisingActivity.class);
                startActivity(intent);
            }
            return true;
        } else if (id==R.id.InformacionScreen) {
            if(currentScreen == InformacionActivity.class){
                Toast.makeText(this, "Xa estás na pantalla Información", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, InformacionActivity.class);
                startActivity(intent);
            }
            return true;
        } else if (id==R.id.HistoriaScreen) {
            if(currentScreen == HistoriaActivity.class){
                Toast.makeText(this, "Xa estás na pantalla Historia", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, HistoriaActivity.class);
                startActivity(intent);
            }
            return true;
        } else if (id==R.id.ContactoScreen) {
            if(currentScreen == ContactoActivity.class){
                Toast.makeText(this, "Xa estás na pantalla Contacto", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, ContactoActivity.class);
                startActivity(intent);
            }
            return true;
        } else if (id==R.id.CerrarSesion) {
            SharedPreferences sharedPrefsLogin = getSharedPreferences(
                    getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
            );
            PreferenciasController.cerrarSesionUsuario(sharedPrefsLogin);

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
