package br.senac.rn.barbe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;

public class TelaTres extends AppCompatActivity {
    EditText editUsuario, editSenha;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tres);

        editUsuario = findViewById(R.id.editUsuario);
        editSenha = findViewById(R.id.editSenha);

        db = openOrCreateDatabase("LoginDb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS login (email VARCHAR, senha VARCHAR);");
    }

    public void oi(View view) {
        String email = editUsuario.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        // Verificar se os campos de e-mail e senha estão preenchidos
        if (email.isEmpty() || senha.isEmpty()) {
            showMessage("Erro", "Preencha corretamente os valores");
            return;
        }

        // Inserir os dados de login no banco de dados utilizando ContentValues para consultas parametrizadas
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("senha", senha);
        db.insert("login", null, values);

        showMessage("Ok", "Login feito com sucesso!!");
        clearText();

        // Se os campos estiverem preenchidos, crie um Intent para iniciar a próxima atividade (TelaQuatro)
        Intent intent = new Intent(TelaTres.this, TelaQuatro.class);

        // Adicione quaisquer dados extras que você deseja passar para a próxima atividade, se necessário
        intent.putExtra("email", email); // Exemplo de passagem de dados extras

        // Inicie a próxima atividade
        startActivity(intent);
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        editUsuario.setText("");
        editSenha.setText("");
    }
}
