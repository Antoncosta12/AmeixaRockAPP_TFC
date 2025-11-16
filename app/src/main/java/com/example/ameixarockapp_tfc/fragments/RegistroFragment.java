package com.example.ameixarockapp_tfc.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.UsuarioDAO;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;
import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.vista.HomeActivity;
import com.example.ameixarockapp_tfc.vista.LoginActivity;

import java.security.MessageDigest;

public class RegistroFragment extends Fragment {
    private DBHelper dbHelper;
    private UsuarioDAO usuarioDAO;

    public RegistroFragment() {
        // Required empty public constructor
    }

    public static RegistroFragment newInstance() {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        dbHelper = new DBHelper(getContext());
        usuarioDAO = new UsuarioDAO(dbHelper);

        ImageButton btnAtras = view.findViewById(R.id.btnAtrasRegistro);
        EditText txtNombre = view.findViewById(R.id.txtNombreRegistro);
        EditText txtCorreoElectronico = view.findViewById(R.id.txtCorreoRegistro);
        EditText txtNomUser = view.findViewById(R.id.txtNomUserRegistro);
        EditText txtPassword = view.findViewById(R.id.txtPasswordRegistro);
        Spinner opsOrigen = view.findViewById(R.id.cmbOrigenRegistro);
        Button btnRegistro = view.findViewById(R.id.btnRegistro);

        String[] origenes = {"Redes Sociales","Medios de comunicación","Familiares/Amigos","Publicidad","Otros"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), R.layout.spinner_element, origenes);
        opsOrigen.setAdapter(spinnerAdapter);

        Context context = getContext();
        SharedPreferences sharedPrefsLogin = context.getSharedPreferences(
                getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
        );

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String correo = txtCorreoElectronico.getText().toString();
                String nomUser = txtNomUser.getText().toString();
                String campoPassword = txtPassword.getText().toString();
                String origen = opsOrigen.getSelectedItem().toString();

                if(nombre.isBlank() || correo.isBlank() || nomUser.isBlank() || campoPassword.isBlank() || origen.isBlank()){
                    Toast.makeText(getContext(), "Debe rellenar todos los campos del formulario", Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = encriptarPassword(txtPassword.getText().toString());

                Usuario nomExistente = usuarioDAO.obtenerUsuarioPorNomUser(nomUser);
                Usuario correoExistente = usuarioDAO.obtenerUsuarioPorCorreoElectronico(correo);

                if(nomExistente == null){
                    if(correoExistente == null){
                        Usuario usuarioNuevo = new Usuario(nombre, nomUser, password, correo, origen);
                        usuarioDAO.insertarUsuario(usuarioNuevo);
                        Toast.makeText(getContext(), "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                        usuarioNuevo = usuarioDAO.obtenerUsuarioPorNomUser(nomUser);

                        PreferenciasController.guardarLoginUsuario(sharedPrefsLogin, usuarioNuevo.getId(), usuarioNuevo.getNomUser());

                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        startActivity(intent);

                        if(getActivity() != null){
                            getActivity().finish();
                        }
                    } else {
                        Toast.makeText(getContext(), "Ya existe un usuario asociado a ese correo electrónico", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Ya existe un usuario con ese nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    public String encriptarPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}