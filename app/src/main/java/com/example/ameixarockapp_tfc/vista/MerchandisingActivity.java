package com.example.ameixarockapp_tfc.vista;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.FavoritoProductoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.ProductoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.TallaDAO;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoProducto;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.BD.modelo.Talla;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;
import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.ProductoAdapter;
import com.example.ameixarockapp_tfc.base.BaseActivity;
import com.example.ameixarockapp_tfc.fragments.ProductoDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MerchandisingActivity extends BaseActivity {
    private ProductoAdapter adapter;
    private DBHelper dbHelper;
    private ProductoDAO productoDAO;
    private FavoritoProductoDAO favoritosProdDAO;
    private List<FavoritoProducto> favProds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_merchandising);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        configMenuToolbar();
        dbHelper = new DBHelper(this);
        productoDAO = new ProductoDAO(dbHelper);
        favoritosProdDAO = new FavoritoProductoDAO(dbHelper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable logo = getDrawable(R.drawable.logo_ameixarock3);
        Bitmap bitmap = ((BitmapDrawable) logo).getBitmap();
        Drawable logo_Escalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
        toolbar.setNavigationIcon(logo_Escalado);
        toolbar.setPadding(0,30,0,20);

        ChipGroup chipGroupFiltro = findViewById(R.id.chipGroupFiltrosProducto);
        Chip chipTodos = findViewById(R.id.chipTodos);
        Chip chipCamisolas = findViewById(R.id.chipCamisolas);
        Chip chipSuadoiros = findViewById(R.id.chipSuadoiros);
        Chip chipBolsas = findViewById(R.id.chipBolsas);
        Chip chipComplementos = findViewById(R.id.chipComplementos);
        Chip chipFavoritos = findViewById(R.id.chipFavoritos);

        List<Producto> productos = productoDAO.obtenerProductos();
        List<Producto> prodFiltrados = new ArrayList<>(productos);

        Context context = this;
        SharedPreferences sharedPrefsLogin = context.getSharedPreferences(
                getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
        );
        Usuario user = PreferenciasController.cargarUsuarioLogged(sharedPrefsLogin);

        favProds = favoritosProdDAO.obtenerFavoritosProdUsuario(user.getId());

        RecyclerView recyclerView = findViewById(R.id.listaProdutos);
        adapter = new ProductoAdapter(prodFiltrados, this);
        recyclerView.setAdapter(adapter);

        chipGroupFiltro.check(chipTodos.getId());

        chipGroupFiltro.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup chipGroup, @NonNull List<Integer> list) {
                if(!list.isEmpty()){
                    int id = list.get(0);
                    prodFiltrados.clear();

                    if(id == chipTodos.getId()){
                        prodFiltrados.addAll(productos);
                    } else if(id == chipCamisolas.getId()){
                        for(Producto p : productos){
                            if(p.getCategoria().equals("Camisola")){
                                prodFiltrados.add(p);
                            }
                        }
                    } else if(id == chipSuadoiros.getId()){
                        for(Producto p : productos){
                            if(p.getCategoria().equals("Suadoiro")){
                                prodFiltrados.add(p);
                            }
                        }
                    } else if(id == chipBolsas.getId()){
                        for(Producto p : productos){
                            if(p.getCategoria().equals("Bolsa")){
                                prodFiltrados.add(p);
                            }
                        }
                    } else if(id == chipComplementos.getId()){
                        for(Producto p : productos){
                            if(p.getCategoria().equals("Complementos")){
                                prodFiltrados.add(p);
                            }
                        }
                    } else if(id == chipFavoritos.getId()){
                        favProds = favoritosProdDAO.obtenerFavoritosProdUsuario(user.getId());
                        for (FavoritoProducto favoritoProducto : favProds){
                            Producto producto = productoDAO.obtenerProductoPorId(favoritoProducto.getIdProducto());
                            if(producto != null){
                                prodFiltrados.add(producto);
                            }
                        }
                    }
                    adapter.actualizarListaProductos(prodFiltrados);
                }
            }
        });

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = recyclerView.getChildAdapterPosition(view);
                if(posicion >= 0){
                    Producto producto = prodFiltrados.get(posicion);
                    ProductoDialogFragment dialogFragment = new ProductoDialogFragment(producto);
                    dialogFragment.show(getSupportFragmentManager(), null);
                }
            }
        });
    }
}