package com.example.ameixarockapp_tfc.vista;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ameixarockapp_tfc.R;
import com.example.ameixarockapp_tfc.base.BaseActivity;

public class ContactoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        configMenuToolbar();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable logo = getDrawable(R.drawable.logo_ameixarock2);
        Bitmap bitmap = ((BitmapDrawable) logo).getBitmap();
        Drawable logo_Escalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
        toolbar.setNavigationIcon(logo_Escalado);
        toolbar.setPadding(0,30,0,20);
    }
}