package com.example.ameixarockapp_tfc.fragments;

import static java.lang.Character.toUpperCase;

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

import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.R;

public class NoticiaDialogFragment extends DialogFragment {
    private Noticia noticia;

    public NoticiaDialogFragment(Noticia noticia) {
        this.noticia = noticia;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noticia_detalle_fragment, parent, false);

        TextView txtTitular = view.findViewById(R.id.txtTitularDetail);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcionNoticiaDetail);
        TextView txtFecha = view.findViewById(R.id.txtFechaNoticiaDetail);
        ImageView imgNoticia = view.findViewById(R.id.imgFotoNoticiaDetail);

        String imagenB64 = noticia.getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        imgNoticia.setImageBitmap(imagenDecod);

        txtTitular.setText(noticia.getTitulo().toUpperCase());
        txtDescripcion.setText(noticia.getContenido());
        txtFecha.setText(noticia.getFecha());

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
