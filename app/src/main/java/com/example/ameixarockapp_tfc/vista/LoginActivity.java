package com.example.ameixarockapp_tfc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ameixarockapp_tfc.BD.BD.DBHelper;
import com.example.ameixarockapp_tfc.BD.DAO.FotoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.NoticiaDAO;
import com.example.ameixarockapp_tfc.BD.DAO.ProductoDAO;
import com.example.ameixarockapp_tfc.BD.DAO.TallaDAO;
import com.example.ameixarockapp_tfc.BD.DAO.UsuarioDAO;
import com.example.ameixarockapp_tfc.BD.modelo.Foto;
import com.example.ameixarockapp_tfc.BD.modelo.Noticia;
import com.example.ameixarockapp_tfc.BD.modelo.Producto;
import com.example.ameixarockapp_tfc.BD.modelo.Talla;
import com.example.ameixarockapp_tfc.BD.modelo.Usuario;
import com.example.ameixarockapp_tfc.BD.preferencias.PreferenciasController;
import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.fragments.RegistroFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductoDAO productoDAO;
    private TallaDAO tallaDAO;
    private NoticiaDAO noticiaDAO;
    private UsuarioDAO usuarioDAO;
    private FotoDAO fotoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(this);
        productoDAO = new ProductoDAO(dbHelper);
        tallaDAO = new TallaDAO(dbHelper);
        noticiaDAO = new NoticiaDAO(dbHelper);
        usuarioDAO = new UsuarioDAO(dbHelper);
        fotoDAO = new FotoDAO(dbHelper);

        Context context = this;
        SharedPreferences sharedPrefsLogin = context.getSharedPreferences(
                getString(R.string.preferenceLogin_file_key), Context.MODE_PRIVATE
        );

        Usuario user = PreferenciasController.cargarUsuarioLogged(sharedPrefsLogin);
        if(user != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        Button btnIniciarSesion = findViewById(R.id.btnLogin);
        Button btnRegistrarse = findViewById(R.id.btnCrearCuenta);
        EditText nomUser = findViewById(R.id.txtNomUserLogin);
        EditText password = findViewById(R.id.txtPasswordLogin);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomUsuario = nomUser.getText().toString();
                String passwordUsuario = password.getText().toString();

                if(nomUsuario.isBlank() || passwordUsuario.isBlank()){
                    Toast.makeText(getBaseContext(), "Debe rellenar todos los datos para iniciar sesión", Toast.LENGTH_SHORT).show();
                    return;
                }

                Usuario usuario = usuarioDAO.obtenerUsuarioPorNomUser(nomUsuario);
                String passwordEncode = encriptarPassword(passwordUsuario);
                if(usuario != null){
                    if(usuario.getPassword().equals(passwordEncode)){
                        PreferenciasController.guardarLoginUsuario(sharedPrefsLogin, usuario.getId(), usuario.getNomUser());

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "No existe ningún usuario con ese nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistroFragment registroFragment = RegistroFragment.newInstance();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentocontenedor, registroFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        //********Simulación de llamada a API e inserción de los datos en la DB********
        try {
            InputStream is = getAssets().open("produtosameixarock.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            br.readLine();
            while((line = br.readLine()) != null){
                String[] propiedades = line.split(";", -1);

                if(propiedades.length >= 8){
                    int id = Integer.parseInt(propiedades[0].trim());
                    String nomProd = propiedades[1].trim();
                    String material = propiedades[2].trim();
                    String observacion = propiedades[3].trim();
                    String descripcion = propiedades[4].trim();
                    double precio = Double.parseDouble(propiedades[5].trim());
                    String foto = propiedades[6].trim();
                    String categoria = propiedades[7].trim();

                    if(productoDAO.obtenerProductoPorId(id) == null){
                        productoDAO.insertarProducto(new Producto(id, nomProd, material, observacion, descripcion, foto, precio, categoria));
                    }
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Error de lectura de datos: " + e.getMessage(),e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error de formato de ID: " + e.getMessage(),e);
        }

        try {
            InputStream is = getAssets().open("tallasprodutosameixarock.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            br.readLine();

            while((line = br.readLine()) != null){
                String[] propiedades = line.split(";", -1);

                if(propiedades.length >= 3){
                    int idTalla = Integer.parseInt(propiedades[0].trim());
                    String talla = propiedades[1].trim();
                    int idProducto = Integer.parseInt(propiedades[2].trim());

                    boolean existe = false;

                    List<Talla> tallasInsertadas = tallaDAO.obtenerTallasProducto(idProducto);
                    for(Talla t : tallasInsertadas){
                        if(t.getTalla().equalsIgnoreCase(talla)){
                            existe = true;
                            break;
                        }
                    }

                    if(!existe){
                        tallaDAO.insertarTalla(new Talla(talla, idProducto));
                    }
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Error de lectura de datos: " + e.getMessage(),e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error de formato de ID: " + e.getMessage(),e);
        }

        try {
            InputStream is = getAssets().open("noticiasameixarock.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            br.readLine();
            while((line = br.readLine()) != null){
                String[] propiedades = line.split(";", -1);

                if(propiedades.length >= 5){
                    int id = Integer.parseInt(propiedades[0].trim());
                    String titulo = propiedades[1].trim();
                    String contenido = propiedades[2].trim();
                    String fecha = propiedades[3].trim();
                    String foto = propiedades[4].trim();
                    if(noticiaDAO.obtenerNoticiaPorId(id) == null){
                        noticiaDAO.insertarNoticia(new Noticia(id, titulo, contenido, fecha, foto));
                    }
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Error de lectura de datos: " + e.getMessage(),e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error de formato de ID: " + e.getMessage(),e);
        }

        try {
            InputStream is = getAssets().open("fotosameixarock.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            br.readLine();
            while((line = br.readLine()) != null){
                String[] propiedades = line.split(";", -1);

                if(propiedades.length >= 3){
                    int id = Integer.parseInt(propiedades[0].trim());
                    String foto = propiedades[1].trim();
                    int edicion = Integer.parseInt(propiedades[2].trim());
                    if(fotoDAO.obtenerFotoPorId(id) == null){
                        fotoDAO.insertarFoto(new Foto(id, foto, edicion));
                    }
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Error de lectura de datos: " + e.getMessage(),e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error de formato de ID: " + e.getMessage(),e);
        }

//        try {
//            InputStream is = getAssets().open("usuariosameixarock.csv");
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String line;
//
//            br.readLine();
//            while((line = br.readLine()) != null){
//                String[] propiedades = line.split(";", -1);
//
//                if(propiedades.length >= 5){
//                    int id = Integer.parseInt(propiedades[0].trim());
//                    String nombre = propiedades[1].trim();
//                    String apellido = propiedades[2].trim();
//                    String nomUsuario = propiedades[3].trim();
//                    String passwordUser = propiedades[4].trim();
//                    String correoElectronico = propiedades[5].trim();
//                    String origen = propiedades[6].trim();
//                    if(usuarioDAO.obtenerUsuarioPorId(id) == null){
//                        usuarioDAO.insertarUsuario(new Usuario(id, nombre, apellido, nomUsuario, passwordUser, correoElectronico, origen));
//                    }
//                }
//            }
//            br.close();
//            is.close();
//        } catch (IOException e) {
//            throw new RuntimeException("Error de lectura de datos: " + e.getMessage(),e);
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("Error de formato de ID: " + e.getMessage(),e);
//        }
        //*****************************************************************************
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