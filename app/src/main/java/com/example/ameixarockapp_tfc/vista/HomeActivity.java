package com.example.ameixarockapp_tfc.vista;

import static android.provider.MediaStore.Images.Media.getBitmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.NoticiaDAO;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.NoticiaAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {
    private NoticiaAdapter noticiaAdapter;
    private DBHelper dbHelper;
    private NoticiaDAO noticiaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(this);
        noticiaDAO = new NoticiaDAO(dbHelper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable logo = getDrawable(R.drawable.logo_ameixarock);
        Bitmap bitmap = ((BitmapDrawable) logo).getBitmap();
        Drawable logo_Escalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
        toolbar.setNavigationIcon(logo_Escalado);
        toolbar.setPadding(0,30,0,20);

        ImageButton btnFacebook = (ImageButton) findViewById(R.id.btnFacebook);
        ImageButton btnInstagram = (ImageButton) findViewById(R.id.btnInstagram);
        ImageButton btnYoutube = (ImageButton) findViewById(R.id.btnYoutube);
        ImageButton btnTwitter = (ImageButton) findViewById(R.id.btnTwitter);

        //********Simulación de llamada a API e inserción de los datos en la DB********
        try {
            InputStream is = getAssets().open("noticiasameixarock.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            br.readLine();
            while((line = br.readLine()) != null){
                String[] propiedades = line.split(";", -1);

                if(propiedades.length >= 5){
                    int id = Integer.parseInt(propiedades[0].trim());
                    String titulo = propiedades[1].trim();
                    String contenido = propiedades[2].trim();
                    String fecha = propiedades[3].trim();
                    String foto = propiedades[4].trim();
                    if(noticiaDAO.obtenerNoticiaPorId(id) == null){
                        noticiaDAO.insertarNoticia(new Noticia(id, titulo, contenido, fecha, foto));
                    }
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Error de lectura de datos: " + e.getMessage(),e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error de formato de ID: " + e.getMessage(),e);
        }
        //*****************************************************************************

        List<Noticia> noticias = noticiaDAO.obtenerNoticias();
        ListView listaNoticias = (ListView) findViewById(R.id.listaNoticias);
        noticiaAdapter = new NoticiaAdapter(this, noticias);
        listaNoticias.setAdapter(noticiaAdapter);

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri linkFacebook = Uri.parse("https://www.facebook.com/ameixarock/?locale=es_ES");
                Intent openFacebook = new Intent(Intent.ACTION_VIEW, linkFacebook);
                startActivity(openFacebook);
            }
        });

        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri linkFacebook = Uri.parse("https://www.instagram.com/ameixarockcarril/?hl=es");
                Intent openFacebook = new Intent(Intent.ACTION_VIEW, linkFacebook);
                startActivity(openFacebook);
            }
        });

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri linkFacebook = Uri.parse("https://www.youtube.com/@ameixarock4313");
                Intent openFacebook = new Intent(Intent.ACTION_VIEW, linkFacebook);
                startActivity(openFacebook);
            }
        });

        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri linkFacebook = Uri.parse("https://x.com/AmeixaRock");
                Intent openFacebook = new Intent(Intent.ACTION_VIEW, linkFacebook);
                startActivity(openFacebook);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulateral,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.HomeScreen) {
            Toast.makeText(this, "Ya estás en la pantalla principal", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id==R.id.MerchandisingScreen) {
            Intent intent = new Intent(HomeActivity.this, MerchandisingActivity.class);
            startActivity(intent);
            return true;
        } else if (id==R.id.InformacionScreen) {
            Intent intent = new Intent(HomeActivity.this, InformacionActivity.class);
            startActivity(intent);
            return true;
        } else if (id==R.id.HistoriaScreen) {
            Intent intent = new Intent(HomeActivity.this, HistoriaActivity.class);
            startActivity(intent);
            return true;
        } else if (id==R.id.ContactoScreen) {
            Intent intent = new Intent(HomeActivity.this, ContactoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}