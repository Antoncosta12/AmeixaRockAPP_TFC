package com.example.ameixarockapp_tfc.vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.NoticiaDAO;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.NoticiaAdapter;
import com.example.ameixarockapp_tfc.base.BaseActivity;
import com.example.ameixarockapp_tfc.fragments.NoticiaDialogFragment;
import com.example.ameixarockapp_tfc.fragments.ProductoDialogFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HomeActivity extends BaseActivity {
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
        configMenuToolbar();
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

        listaNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Noticia noticia = noticias.get(i);
                NoticiaDialogFragment dialogFragment = new NoticiaDialogFragment(noticia);
                dialogFragment.show(getSupportFragmentManager(), null);
            }
        });
    }
}