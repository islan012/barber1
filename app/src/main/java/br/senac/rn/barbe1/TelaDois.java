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

public class TelaDois extends AppCompatActivity {
    EditText editUsuario, editEmail, editSenha;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dois);

        editUsuario = findViewById(R.id.editUsuario);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        db = openOrCreateDatabase("CriarDb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS criar (usuario VARCHAR, email VARCHAR, senha VARCHAR);");
    }

    public void ola(View view) {
        String usuario = editUsuario.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        // Verificar se os campos de usuário, e-mail e senha estão preenchidos
        if (usuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            showMessage("Erro", "Preencha corretamente os valores");
            return;
        }

        // Inserir os dados no banco de dados utilizando ContentValues para consultas parametrizadas
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("email", email);
        values.put("senha", senha);
        db.insert("criar", null, values);

        // Se os campos estiverem preenchidos, crie um Intent para iniciar a próxima atividade (TelaQuatro)
        Intent intent = new Intent(TelaDois.this, TelaQuatro.class);

        // Adicione quaisquer dados extras que você deseja passar para a próxima atividade, se necessário
        intent.putExtra("usuario", usuario); // Exemplo de passagem de dados extras
        intent.putExtra("email", email);
        intent.putExtra("senha", senha);

        // Inicie a próxima atividade
        startActivity(intent);

        // Limpar os campos e mostrar mensagem após iniciar a próxima atividade
        showMessage("Ok", "Conta criada com sucesso");
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
        editUsuario.setText("");
        editEmail.setText("");
        editSenha.setText("");
    }
}
