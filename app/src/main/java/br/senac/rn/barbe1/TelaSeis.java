package br.senac.rn.barbe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;

public class TelaSeis extends AppCompatActivity {
    EditText editNome1, editEmail1, editMensagem1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_seis);

        editNome1 = findViewById(R.id.editNome1);
        editEmail1 = findViewById(R.id.editEmail1);
        editMensagem1 = findViewById(R.id.editMensagem1);

        db = openOrCreateDatabase("MensagemDb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS mensagem (nome VARCHAR, email VARCHAR, mensagem VARCHAR);");
    }

    public void enviar1(View view) {
        String nome = editNome1.getText().toString().trim();
        String email = editEmail1.getText().toString().trim();
        String mensagem = editMensagem1.getText().toString().trim();

        if (nome.isEmpty() || email.isEmpty() || mensagem.isEmpty()) {
            showMessage("Erro", "Preencha corretamente os valores");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("email", email);
        values.put("mensagem", mensagem);
        db.insert("mensagem", null, values);

        // Exibir um Toast para informar que a mensagem foi enviada com sucesso
        Toast.makeText(this, "Mensagem enviada", Toast.LENGTH_SHORT).show();

        // Limpar os campos após o envio da mensagem
        clearText();
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        editNome1.setText("");
        editEmail1.setText("");
        editMensagem1.setText("");
    }

    public void inicio(View view) {
        Intent in = new Intent(TelaSeis.this, TelaQuatro.class);
        startActivity(in);
    }

    public void sobre(View view) {
        Intent in = new Intent(TelaSeis.this, TelaCinco.class);
        startActivity(in);
    }

    public void contato(View view) {
        Intent in = new Intent(TelaSeis.this, TelaSeis.class);
        startActivity(in);
    }

    public void plano(View view) {
        Intent in = new Intent(TelaSeis.this, TelaSete.class);
        startActivity(in);
    }
}
