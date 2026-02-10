package com.example.march.compras;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {
    TextView usuari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        usuari=(TextView)findViewById(R.id.lblUsuario3);
        String user=getIntent().getStringExtra("user").toString();
        usuari.setText(user);
    }

    public void Comprar(View v) {
        Intent ven=new Intent(this,Comprar.class);
        ven.putExtra("user2", usuari.getText().toString().trim());
        startActivity(ven);
    }

    public void Lista(View v) {
        Intent ven=new Intent(this,Lista.class);
        ven.putExtra("user4", usuari.getText().toString().trim());
        startActivity(ven);
    }
    public void Cerrar(View v) {
        Intent ven=new Intent(this,Login.class);
        startActivity(ven);
    }
}
