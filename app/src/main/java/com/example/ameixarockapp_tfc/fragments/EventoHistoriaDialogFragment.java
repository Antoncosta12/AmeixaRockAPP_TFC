package com.example.ameixarockapp_tfc.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ameixarockapp_tfc.BD.modelo.EventoHistoria;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.R;

public class EventoHistoriaDialogFragment  extends DialogFragment {
    private EventoHistoria eventoHistoria;

    public EventoHistoriaDialogFragment(EventoHistoria eventoHistoria) {
        this.eventoHistoria = eventoHistoria;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.evento_detalle_fragment, parent, false);

        TextView txtTitulo = view.findViewById(R.id.txtTituloEventoDetail);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcionEventoDetail);
        ImageView imgEvento = view.findViewById(R.id.imgFotoEventoDetail);

        String imagenB64 = eventoHistoria.getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        imgEvento.setImageBitmap(imagenDecod);

        txtTitulo.setText(eventoHistoria.getTitulo().toUpperCase());
        txtDescripcion.setText(eventoHistoria.getDescripcion());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getDialog() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
