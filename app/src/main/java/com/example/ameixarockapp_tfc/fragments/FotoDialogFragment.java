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
import com.example.ameixarockapp_tfc.BD.DAO.FavoritoFotoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.FavoritoProductoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.TallaDAO;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoFoto;
import com.example.ameixarockapp_tfc.BD.modelo.FavoritoProducto;
import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.BD.modelo.Talla;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;
import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;

import java.util.List;

public class FotoDialogFragment extends DialogFragment {
    private Foto foto;
    private DBHelper dbHelper;
    private FavoritoFotoDAO favoritosFotoDAO;
    private boolean favorito;

    public FotoDialogFragment(Foto foto) {
        this.foto = foto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foto_detalle_fragment, parent, false);

        ImageView imgProducto = view.findViewById(R.id.imgFotoDetail);

        String imagenB64 = foto.getFoto();
        Bitmap imagenDecod = BitmapFactory.decodeByteArray(Base64.decode(imagenB64, Base64.DEFAULT), 0, Base64.decode(imagenB64, Base64.DEFAULT).length);
        imgProducto.setImageBitmap(imagenDecod);

        Context context = getContext();

        dbHelper = new DBHelper(context);
        favoritosFotoDAO = new FavoritoFotoDAO(dbHelper);

        SharedPreferences sharedPrefsLogin = context.getSharedPreferences(
                getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
        );

        Usuario user = PreferenciasController.cargarUsuarioLogged(sharedPrefsLogin);

        List<FavoritoFoto> favFotos = favoritosFotoDAO.obtenerFavoritosFotoUsuario(user.getId());

        ImageButton btnFavoritos = view.findViewById(R.id.estrellaFavoritos);
        favorito = false;

        for(FavoritoFoto favFoto : favFotos){
            if(favFoto.getIdFoto() == foto.getId()){
                favorito = true;
                break;
            }
        }
        if(favorito){
            btnFavoritos.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btnFavoritos.setImageResource(android.R.drawable.btn_star_big_off);
        }

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorito = !favorito;

                if(favorito){
                    btnFavoritos.setImageResource(android.R.drawable.btn_star_big_on);
                    try {
                        favoritosFotoDAO.insertarFotoFavoritos(new FavoritoFoto(user.getId(), foto.getId()));
                    } catch (Exception e) {
                        Toast.makeText(context, "ERROR: No se ha podido añadir la foto a favoritos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    btnFavoritos.setImageResource(android.R.drawable.btn_star_big_off);
                    try {
                        favoritosFotoDAO.eliminarFotoFavorito(user.getId(), foto.getId());
                    } catch (Exception e) {
                        Toast.makeText(context, "ERROR: No se ha podido añadir la foto a favoritos", Toast.LENGTH_SHORT).show();
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
