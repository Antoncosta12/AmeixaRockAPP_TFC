package com.example.ameixarockapp_tfc.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private List<Producto> productos;
    private Context context;
    private View.OnClickListener listener;

    public ProductoAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreProducto;
        ImageView fotoProducto;
        TextView txtPrecioProducto;
        public ProductoViewHolder(@NonNull View productoElement) {
            super(productoElement);
            txtNombreProducto = productoElement.findViewById(R.id.txtNombreProducto);
            fotoProducto = productoElement.findViewById(R.id.imgFotoProducto);
            txtPrecioProducto = productoElement.findViewById(R.id.txtPrecioProducto);
        }
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.producto_element, parent, false);
        if(listener != null){
            view.setOnClickListener(listener);
        }
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder viewHolder, int position) {
        Producto p = productos.get(position);

        String imagenB64 = p.getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        viewHolder.fotoProducto.setImageBitmap(imagenDecod);

        viewHolder.txtNombreProducto.setText(p.getNombreProducto());
        viewHolder.txtPrecioProducto.setText(String.valueOf(p.getPrecio() + "€"));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void actualizarListaProductos(List<Producto> productosActualizados){
        this.productos = new ArrayList<>(productosActualizados);
        notifyDataSetChanged();
    }
}
