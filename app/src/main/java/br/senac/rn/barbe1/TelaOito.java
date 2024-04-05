package br.senac.rn.barbe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;

public class TelaOito extends AppCompatActivity {
    EditText editUsuario, editData, editHora;
    Spinner spinnerFormaPagamento;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_oito);

        editUsuario = findViewById(R.id.editUsuario);
        editData = findViewById(R.id.editData);
        editHora = findViewById(R.id.editHora);
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);

        db = openOrCreateDatabase("AgendamentoDb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS agendamento (usuario VARCHAR, data VARCHAR, hora VARCHAR, forma_pagamento VARCHAR);");
    }

    public void agendar1(View view) {
        String usuario = editUsuario.getText().toString().trim();
        String data = editData.getText().toString().trim();
        String hora = editHora.getText().toString().trim();
        String formaPagamento = spinnerFormaPagamento.getSelectedItem().toString(); // Correção aqui

        if (usuario.isEmpty() || data.isEmpty() || hora.isEmpty() || formaPagamento.isEmpty()) {
            showMessage("Erro", "Preencha corretamente os valores");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("data", data);
        values.put("hora", hora);
        values.put("forma_pagamento", formaPagamento);
        db.insert("agendamento", null, values);

        Intent intent = new Intent(TelaOito.this, TelaNove.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("data", data);
        intent.putExtra("hora", hora);
        intent.putExtra("formaPagamento", formaPagamento);
        startActivity(intent);

        showMessage("Ok", "Agendameto realizado com sucesso");
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
        editData.setText("");
        editHora.setText("");
    }
}
