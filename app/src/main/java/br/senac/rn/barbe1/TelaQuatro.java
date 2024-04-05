package br.senac.rn.barbe1;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TelaQuatro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quatro);
    }
    public void inicio (View view){
        Intent in = new Intent(TelaQuatro.this, TelaQuatro.class);
        startActivity(in);
    }
    public void sobre (View view){
        Intent in = new Intent(TelaQuatro.this, TelaCinco.class);
        startActivity(in);
    }
    public void contato (View view){
        Intent in = new Intent(TelaQuatro.this, TelaSeis.class);
        startActivity(in);
    }
    public void plano (View view){
        Intent in = new Intent(TelaQuatro.this, TelaSete.class);
        startActivity(in);
    }
}