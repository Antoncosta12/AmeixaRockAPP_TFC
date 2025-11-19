package com.example.ameixarockapp_tfc.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ameixarockapp_tfc.BD.modelo.Actuacion;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.R;

import java.util.List;

public class ActuacionAdapter extends BaseAdapter {
    private List<Actuacion> actuacionList;

    private Context context;

    public ActuacionAdapter(@NonNull Context context, List<Actuacion> actuacionList) {
        this.actuacionList = actuacionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return actuacionList.size();
    }

    @Override
    public Object getItem(int i) {
        return actuacionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater vista = LayoutInflater.from(context);
        View elemento = vista.inflate(R.layout.actuacion_element, viewGroup, false);

        TextView txtArtista= elemento.findViewById(R.id.txtArtistaActuacion);
        TextView txtHora= elemento.findViewById(R.id.txtHoraActuacion);
        TextView txtLugar= elemento.findViewById(R.id.txtLugarActuacion);

        txtArtista.setText(((Actuacion)getItem(i)).getArtista());
        txtHora.setText(((Actuacion)getItem(i)).getHora());
        txtLugar.setText(((Actuacion)getItem(i)).getLugar());

        return elemento;
    }
}
