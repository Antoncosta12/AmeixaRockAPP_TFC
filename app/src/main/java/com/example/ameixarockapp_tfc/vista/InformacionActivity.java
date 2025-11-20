package com.example.ameixarockapp_tfc.vista;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.ActuacionDAO;
import com.example.ameixarockapp_tfc.BD.modelo.Actuacion;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.ActuacionAdapter;
import com.example.ameixarockapp_tfc.adapters.FotoAdapter;
import com.example.ameixarockapp_tfc.adapters.NoticiaAdapter;
import com.example.ameixarockapp_tfc.base.BaseActivity;

import java.util.List;

public class InformacionActivity extends BaseActivity {
    private ActuacionDAO actuacionDAO;
    private DBHelper dbHelper;
    private ActuacionAdapter actuacionAdapterDia1;
    private ActuacionAdapter actuacionAdapterDia2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        configMenuToolbar();
        dbHelper = new DBHelper(this);
        actuacionDAO = new ActuacionDAO(dbHelper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable logo = getDrawable(R.drawable.logo_ameixarock2);
        Bitmap bitmap = ((BitmapDrawable) logo).getBitmap();
        Drawable logo_Escalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
        toolbar.setNavigationIcon(logo_Escalado);
        toolbar.setPadding(0,30,0,20);

        List<Actuacion> actuacionesSabado = actuacionDAO.obtenerActuacionesPorDia("Sábado 6");
        List<Actuacion> actuacionesDomigo = actuacionDAO.obtenerActuacionesPorDia("Domingo 7");

        RecyclerView recyclerView1 = findViewById(R.id.listaActuacionesDia1);
        actuacionAdapterDia1 = new ActuacionAdapter(actuacionesSabado, this);
        recyclerView1.setAdapter(actuacionAdapterDia1);

        RecyclerView recyclerView2 = findViewById(R.id.listaActuacionesDia2);
        actuacionAdapterDia2 = new ActuacionAdapter(actuacionesDomigo, this);
        recyclerView2.setAdapter(actuacionAdapterDia2);

        ImageView imgMapa = findViewById(R.id.imgMapa);
        imgMapa.setImageResource(R.drawable.mapaameixarock2025);
        ImageView imgCartel = findViewById(R.id.imgCartel);
        imgCartel.setImageResource(R.drawable.ameixarock_cartel_2025);
        ImageView imgNormas = findViewById(R.id.imgNormas);
        imgNormas.setImageResource(R.drawable.normas_ameixarock);
    }
}