package com.example.ameixarockapp_tfc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.FotoDAO;
import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.adapters.EdicionAdapter;
import com.example.ameixarockapp_tfc.adapters.FotoAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GaleriaFragment extends Fragment {
    private EdicionAdapter adapter;
    private DBHelper dbHelper;
    private FotoDAO fotoDAO;

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

        List<Foto> fotosTotales = fotoDAO.obtenerFotos();

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
        adapter = new EdicionAdapter(mapeadoEdiciones, getContext(), R.layout.foto_galeria_element);
        recyclerView.setAdapter(adapter);

        adapter.setOnFotoClickListener(view1 -> {
            Foto foto = (Foto) view1.getTag();
            if(foto != null){
                FotoDialogFragment dialogFragment = new FotoDialogFragment(foto);
                dialogFragment.show(getParentFragmentManager(), null);
            }
        });

        return view;
    }
}