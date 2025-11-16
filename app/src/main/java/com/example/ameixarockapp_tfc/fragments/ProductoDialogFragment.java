package com.example.ameixarockapp_tfc.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.FavoritoProductoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.ProductoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.TallaDAO;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoProducto;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.BD.modelo.Talla;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;
import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoDialogFragment extends DialogFragment {
    private Producto producto;
    private DBHelper dbHelper;
    private TallaDAO tallaDAO;
    private FavoritoProductoDAO favoritosProdDAO;
    private boolean favorito;

    public ProductoDialogFragment(Producto producto) {
        this.producto = producto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.producto_detalle_fragment, parent, false);

        TextView txtNomProd = view.findViewById(R.id.txtNombreProductoDetail);
        TextView txtPrecioProd = view.findViewById(R.id.txtPrecioProductoDetail);
        TextView txtDescripcionProd = view.findViewById(R.id.txtDescripcionProductoDetail);
        TextView txtTallasProd = view.findViewById(R.id.txtTallasProductoDetail);
        TextView txtObsProd = view.findViewById(R.id.txtObservacionProductoDetail);
        TextView txtMaterialProd = view.findViewById(R.id.txtMaterialProductoDetail);
        ImageView imgProducto = view.findViewById(R.id.imgFotoProductoDetail);

        String imagenB64 = producto.getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        imgProducto.setImageBitmap(imagenDecod);

        txtNomProd.setText(producto.getNombreProducto().toUpperCase());
        txtPrecioProd.setText(String.valueOf(producto.getPrecio() + "€"));
        txtDescripcionProd.setText(producto.getDescripProducto());

        Context context = getContext();

        dbHelper = new DBHelper(context);
        tallaDAO = new TallaDAO(dbHelper);
        favoritosProdDAO = new FavoritoProductoDAO(dbHelper);

        SharedPreferences sharedPrefsLogin = context.getSharedPreferences(
                getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
        );

        Usuario user = PreferenciasController.cargarUsuarioLogged(sharedPrefsLogin);

        List<FavoritoProducto> favProds = favoritosProdDAO.obtenerFavoritosProdUsuario(user.getId());

        ImageButton btnFavoritos = view.findViewById(R.id.estrellaFavoritos);
        favorito = false;

        for(FavoritoProducto favProd : favProds){
            if(favProd.getIdProducto() == producto.getId()){
                favorito = true;
                break;
            }
        }
        if(favorito){
            btnFavoritos.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btnFavoritos.setImageResource(android.R.drawable.btn_star_big_off);
        }

        List<Talla> tallas = tallaDAO.obtenerTallasProducto(producto.getId());
        String tallasUnidas = "";

        if(tallas.isEmpty()){
            txtTallasProd.setText(tallasUnidas);
        } else {
            for(Talla talla : tallas){
                tallasUnidas += talla.getTalla() + ", ";
            }

            txtTallasProd.setText(" Tallas:\n" + tallasUnidas);
        }

        txtObsProd.setText(producto.getObservacionProducto());
        txtMaterialProd.setText(producto.getMaterialProducto());

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorito = !favorito;

                if(favorito){
                    btnFavoritos.setImageResource(android.R.drawable.btn_star_big_on);
                    try {
                        favoritosProdDAO.insertarProdFavorito(new FavoritoProducto(user.getId(), producto.getId()));
                    } catch (Exception e) {
                        Toast.makeText(context, "ERROR: No se ha podido añadir el producto a favoritos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    btnFavoritos.setImageResource(android.R.drawable.btn_star_big_off);
                    try {
                        favoritosProdDAO.eliminarProdFavorito(user.getId(), producto.getId());
                    } catch (Exception e) {
                        Toast.makeText(context, "ERROR: No se ha podido añadir el producto a favoritos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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
