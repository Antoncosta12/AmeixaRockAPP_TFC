package com.example.ameixarockapp_tfc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EdicionAdapter extends RecyclerView.Adapter<EdicionAdapter.EdicionViewHolder>{

    private List<Integer> ediciones;
    private Context context;
    private View.OnClickListener listener;
    private int layoutFotoId;
    private Map<Integer, List<Foto>> mapeadoEdiciones;

    public EdicionAdapter(Map<Integer, List<Foto>> mapeadoEdiciones, Context context, int layoutFotoId) {
        this.mapeadoEdiciones = mapeadoEdiciones;
        this.context = context;
        this.layoutFotoId = layoutFotoId;
        this.ediciones = new ArrayList<>(mapeadoEdiciones.keySet());
    }

    public void setOnFotoClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public static class EdicionViewHolder extends RecyclerView.ViewHolder {
        TextView txtEdicion;
        RecyclerView recyclerViewFotos;
        public EdicionViewHolder(@NonNull View edicionElement) {
            super(edicionElement);

            txtEdicion = edicionElement.findViewById(R.id.txtEdicion);
            recyclerViewFotos = edicionElement.findViewById(R.id.listaFotosEdicion);
        }
    }

    @NonNull
    @Override
    public EdicionAdapter.EdicionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.edicion_element, parent, false);
        if(listener != null){
            view.setOnClickListener(listener);
        }
        return new EdicionAdapter.EdicionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EdicionAdapter.EdicionViewHolder viewHolder, int position) {
        int edicion = ediciones.get(position);
        viewHolder.txtEdicion.setText(edicion + "ª Edición");

        List<Foto> fotosEdicion = mapeadoEdiciones.get(edicion);

        FotoAdapter adapter = new FotoAdapter(fotosEdicion, context, layoutFotoId);
        viewHolder.recyclerViewFotos.setAdapter(adapter);

        if(listener != null){
            adapter.setOnItemClickListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return ediciones.size();
    }
}
