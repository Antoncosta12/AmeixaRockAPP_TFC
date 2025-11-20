package com.example.ameixarockapp_tfc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameixarockapp_tfc.BD.modelo.Actuacion;
import com.example.ameixarockapp_tfc.R;

import java.util.List;

public class ActuacionAdapter  extends RecyclerView.Adapter<ActuacionAdapter.ActuacionViewHolder>{

    private List<Actuacion> actuaciones;
    private Context context;

    public ActuacionAdapter(List<Actuacion> actuaciones, Context context) {
        this.actuaciones = actuaciones;
        this.context = context;
    }
    public static class ActuacionViewHolder extends RecyclerView.ViewHolder {
        TextView txtArtista;
        TextView txtHora;
        TextView txtLugar;
        public ActuacionViewHolder(@NonNull View actuacionElement) {
            super(actuacionElement);
            txtArtista = actuacionElement.findViewById(R.id.txtArtistaActuacion);
            txtHora = actuacionElement.findViewById(R.id.txtHoraActuacion);
            txtLugar = actuacionElement.findViewById(R.id.txtLugarActuacion);
        }
    }

    @NonNull
    @Override
    public ActuacionAdapter.ActuacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.actuacion_element, parent, false);
        return new ActuacionAdapter.ActuacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActuacionAdapter.ActuacionViewHolder viewHolder, int position) {
        Actuacion a = actuaciones.get(position);

        viewHolder.txtArtista.setText(a.getArtista());
        viewHolder.txtHora.setText(a.getHora());
        viewHolder.txtLugar.setText(a.getLugar());
    }

    @Override
    public int getItemCount() {
        return actuaciones.size();
    }
}
