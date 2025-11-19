package com.example.ameixarockapp_tfc.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.FavoritoFotoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.FotoDAO;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoFoto;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoProducto;
import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;
import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.EdicionAdapter;
import com.example.ameixarockapp_tfc.adapters.FotoAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GaleriaFragment extends Fragment {
    private EdicionAdapter adapterEdicion;
    private FotoAdapter adapterFoto;
    private DBHelper dbHelper;
    private FotoDAO fotoDAO;
    private FavoritoFotoDAO favoritoFotoDAO;
    private Boolean favoritos;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    public static GaleriaFragment newInstance() {
        GaleriaFragment fragment = new GaleriaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);
        dbHelper = new DBHelper(getContext());
        fotoDAO = new FotoDAO(dbHelper);
        favoritoFotoDAO = new FavoritoFotoDAO(dbHelper);
        favoritos = false;

        List<Foto> fotosTotales = fotoDAO.obtenerFotos();

        Context context = getContext();
        SharedPreferences sharedPrefsLogin = context.getSharedPreferences(
                getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
        );
        Usuario user = PreferenciasController.cargarUsuarioLogged(sharedPrefsLogin);

        Map<Integer, List<Foto>> mapeadoEdiciones = new TreeMap<>(Collections.reverseOrder());
        for(Foto f : fotosTotales){
            if(mapeadoEdiciones.containsKey(f.getEdicion())){
                mapeadoEdiciones.get(f.getEdicion()).add(f);
            } else {
                mapeadoEdiciones.put(f.getEdicion(), new ArrayList<>());
                mapeadoEdiciones.get(f.getEdicion()).add(f);
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.listaEdiciones);
        adapterEdicion = new EdicionAdapter(mapeadoEdiciones, getContext(), R.layout.foto_galeria_element);
        recyclerView.setAdapter(adapterEdicion);

        adapterEdicion.setOnFotoClickListener(view1 -> {
            Foto foto = (Foto) view1.getTag();
            if(foto != null){
                FotoDialogFragment dialogFragment = new FotoDialogFragment(foto);
                dialogFragment.show(getParentFragmentManager(), null);
            }
        });

        ImageButton btnAtras = view.findViewById(R.id.btnAtrasGaleria);
        ImageButton btnFavoritos = view.findViewById(R.id.btnFavoritosGaleria);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!favoritos){
                    List<FavoritoFoto> favFotos = favoritoFotoDAO.obtenerFavoritosFotoUsuario(user.getId());
                    List<Foto> fotosFavoritas = new ArrayList<>();

                    for (FavoritoFoto favoritoFoto : favFotos){
                        Foto foto = fotoDAO.obtenerFotoPorId(favoritoFoto.getIdFoto());
                        if(foto != null){
                            fotosFavoritas.add(foto);
                        }
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapterFoto = new FotoAdapter(fotosFavoritas, getContext(), R.layout.foto_favoritos_element);
                    recyclerView.setAdapter(adapterFoto);

                    adapterFoto.setOnItemClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int posicion = recyclerView.getChildAdapterPosition(view);
                            if(posicion >= 0){
                                Foto foto = fotosFavoritas.get(posicion);
                                if(foto != null){
                                    FotoDialogFragment dialogFragment = new FotoDialogFragment(foto);
                                    dialogFragment.show(requireActivity().getSupportFragmentManager(), null);
                                }
                            }
                        }
                    });

                    btnFavoritos.setImageResource(R.drawable.estrella_grande_on);
                    favoritos = true;
                } else {
                    recyclerView.setAdapter(adapterEdicion);
                    btnFavoritos.setImageResource(R.drawable.estrella_grande_off);
                    favoritos = false;
                }

            }
        });

        return view;
    }
}