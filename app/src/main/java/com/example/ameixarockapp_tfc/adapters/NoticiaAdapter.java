package com.example.ameixarockapp_tfc.adapters;

import android.annotation.SuppressLint;
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

import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.R;

import java.util.List;

public class NoticiaAdapter extends BaseAdapter {
    private List<Noticia> noticias;
    private Context context;

    public NoticiaAdapter(@NonNull Context context, List<Noticia> noticias) {
        this.noticias = noticias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater vista = LayoutInflater.from(context);
        View elemento = vista.inflate(R.layout.noticia_element, viewGroup, false);

        TextView txtTitulo = elemento.findViewById(R.id.txtTituloNoticia);
        ImageView imgNoticia = elemento.findViewById(R.id.imgFotoNoticia);

        String imagenB64 = ((Noticia)getItem(i)).getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        imgNoticia.setImageBitmap(imagenDecod);

        txtTitulo.setText(((Noticia)getItem(i)).getTitulo());

        return elemento;
    }
}
