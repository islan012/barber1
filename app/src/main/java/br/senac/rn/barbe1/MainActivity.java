package br.senac.rn.barbe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   public void teladois(View view){
        Intent in = new Intent(MainActivity.this, TelaDois.class);
        startActivity(in);
    }
   public void telatres(View view){
        Intent in = new Intent(MainActivity.this, TelaTres.class);
        startActivity(in);
}

}