package com.example.ameixarockapp_tfc.vista;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.EventoHistoriaDAO;
import com.example.ameixarockapp_tfc.BD.DAO.FotoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.ProductoDAO;
import com.example.ameixarockapp_tfc.BD.modelo.EventoHistoria;
import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.FotoAdapter;
import com.example.ameixarockapp_tfc.adapters.ProductoAdapter;
import com.example.ameixarockapp_tfc.base.BaseActivity;
import com.example.ameixarockapp_tfc.fragments.EventoHistoriaDialogFragment;
import com.example.ameixarockapp_tfc.fragments.FotoDialogFragment;
import com.example.ameixarockapp_tfc.fragments.GaleriaFragment;
import com.example.ameixarockapp_tfc.fragments.ProductoDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoriaActivity extends BaseActivity {
    private FotoAdapter adapter;
    private DBHelper dbHelper;
    private FotoDAO fotoDAO;
    private EventoHistoriaDAO eventoDAO;
    private List<Foto> fotosMuestra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        configMenuToolbar();
        dbHelper = new DBHelper(this);
        fotoDAO = new FotoDAO(dbHelper);
        eventoDAO = new EventoHistoriaDAO(dbHelper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable logo = getDrawable(R.drawable.logo_ameixarock2);
        Bitmap bitmap = ((BitmapDrawable) logo).getBitmap();
        Drawable logo_Escalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
        toolbar.setNavigationIcon(logo_Escalado);
        toolbar.setPadding(0,30,0,20);

        Button btnIrAGaleria = findViewById(R.id.btnAccederGaleria);
        ImageButton btnEv1 = findViewById(R.id.btnEv1);
        ImageButton btnEv2 = findViewById(R.id.btnEv2);
        ImageButton btnEv3 = findViewById(R.id.btnEv3);
        ImageButton btnEv4 = findViewById(R.id.btnEv4);

        fotosMuestra = fotoDAO.obtenerFotos();
        if(fotosMuestra.size() > 15){
            fotosMuestra = fotosMuestra.subList(0, 15);
        }

        RecyclerView recyclerView = findViewById(R.id.listaInicialFotos);
        adapter = new FotoAdapter(fotosMuestra, this, R.layout.foto_element);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = recyclerView.getChildAdapterPosition(view);
                if(posicion >= 0){
                    Foto foto = fotosMuestra.get(posicion);
                    if(foto != null){
                        FotoDialogFragment dialogFragment = new FotoDialogFragment(foto);
                        dialogFragment.show(getSupportFragmentManager(), null);
                    }
                }
            }
        });

        btnIrAGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GaleriaFragment galeriaFragment = GaleriaFragment.newInstance();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, galeriaFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnEv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventoHistoria e = eventoDAO.obtenerEventoPorId(1);
                if(e != null){
                    EventoHistoriaDialogFragment dialogFragment = new EventoHistoriaDialogFragment(e);
                    dialogFragment.show(getSupportFragmentManager(), null);
                }
            }
        });

        btnEv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventoHistoria e = eventoDAO.obtenerEventoPorId(2);
                if(e != null){
                    EventoHistoriaDialogFragment dialogFragment = new EventoHistoriaDialogFragment(e);
                    dialogFragment.show(getSupportFragmentManager(), null);
                }
            }
        });

        btnEv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventoHistoria e = eventoDAO.obtenerEventoPorId(3);
                if(e != null){
                    EventoHistoriaDialogFragment dialogFragment = new EventoHistoriaDialogFragment(e);
                    dialogFragment.show(getSupportFragmentManager(), null);
                }
            }
        });

        btnEv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventoHistoria e = eventoDAO.obtenerEventoPorId(4);
                if(e != null){
                    EventoHistoriaDialogFragment dialogFragment = new EventoHistoriaDialogFragment(e);
                    dialogFragment.show(getSupportFragmentManager(), null);
                }
            }
        });
    }
}