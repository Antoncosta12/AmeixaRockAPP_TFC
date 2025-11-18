package com.example.ameixarockapp_tfc.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.R;

import java.util.List;

public class FotoAdapter  extends RecyclerView.Adapter<FotoAdapter.FotoViewHolder>{

    private List<Foto> fotos;
    private Context context;
    private View.OnClickListener listener;
    private int layoutElementId;

    public FotoAdapter(List<Foto> fotos, Context context, int layoutElementId) {
        this.fotos = fotos;
        this.context = context;
        this.layoutElementId = layoutElementId;
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoView;
        public FotoViewHolder(@NonNull View fotoElement) {
            super(fotoElement);
            fotoView = fotoElement.findViewById(R.id.fotoAccesoPrevio);
        }
    }

    @NonNull
    @Override
    public FotoAdapter.FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutElementId, parent, false);
        if(listener != null){
            view.setOnClickListener(listener);
        }
        return new FotoAdapter.FotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoAdapter.FotoViewHolder viewHolder, int position) {
        Foto f = fotos.get(position);

        String imagenB64 = f.getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        viewHolder.fotoView.setImageBitmap(imagenDecod);

        viewHolder.itemView.setTag(f);
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }
}
