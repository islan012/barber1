package br.senac.rn.barbe1;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TelaCinco extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cinco);
    }
    public void inicio(View view){
        Intent in = new Intent(TelaCinco.this, TelaQuatro.class);
        startActivity(in);
    }
    public void sobre(View view){
        Intent in = new Intent(TelaCinco.this, TelaCinco.class);
        startActivity(in);
    }

    public void contato (View view) {
        Intent in = new Intent(TelaCinco.this, TelaSeis.class);
        startActivity(in);
    }
    public void plano (View view){
        Intent in = new Intent(TelaCinco.this, TelaSete.class);
        startActivity(in);
    }

    public void banco (View view){
        Intent in = new Intent(TelaCinco.this, ListaUsuario.class);
        startActivity(in);
    }
}