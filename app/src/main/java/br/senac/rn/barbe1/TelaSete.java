package br.senac.rn.barbe1;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TelaSete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sete);
    }
    public void inicio (View view){
        Intent in = new Intent(TelaSete.this, TelaQuatro.class);
        startActivity(in);
    }

    public void sobre(View view){
        Intent in = new Intent(TelaSete.this, TelaCinco.class);
        startActivity(in);
    }
    public void contato(View view){
        Intent in = new Intent(TelaSete.this, TelaSeis.class);
        startActivity(in);
    }
    public  void plano (View view){
        Intent in = new Intent(TelaSete.this, TelaSete.class);
        startActivity(in);
    }
    public void basico(View view){
        Intent in = new Intent(TelaSete.this, TelaOito.class);
        startActivity(in);
    }
    public void pro(View view){
        Intent in = new Intent(TelaSete.this, TelaOito.class);
        startActivity(in);
    }
    public void premium(View view){
        Intent in = new Intent(TelaSete.this, TelaOito.class);
        startActivity(in);
    }
}