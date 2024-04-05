package br.senac.rn.barbe1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaUsuario extends AppCompatActivity {

    ListView usersListView;
    ArrayList<String> userList;
    ArrayAdapter<String> adapter;
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);

        usersListView = findViewById(R.id.usersListView);
        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        usersListView.setAdapter(adapter);

        // Abre ou cria o banco de dados
        myDB = openOrCreateDatabase("CriarDb", MODE_PRIVATE, null);

        // Verifica se a tabela "criar" já existe, se não existir, ela será criada
        myDB.execSQL("CREATE TABLE IF NOT EXISTS criar (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)");

        loadUsers();

        // Configura o evento de clique longo em um item da lista para excluir o usuário
        usersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedItem = (String) parent.getItemAtPosition(position);
                // Supondo que o nome do usuário é único e usamos ele para excluir
                final String userName = selectedItem.split(" - ")[0];

                new AlertDialog.Builder(ListaUsuario.this)
                        .setTitle("Excluir Usuário")
                        .setMessage("Você tem certeza que deseja excluir este usuário?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteUser(userName);
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();

                return true;
            }
        });
    }

    // Método para carregar os usuários do banco de dados e exibi-los na lista
    private void loadUsers() {
        Cursor cursor = myDB.rawQuery("SELECT * FROM criar", null);
        userList.clear();
        if (cursor.moveToFirst()) {
            do {
                String user = cursor.getString(1); // Obtém o nome do usuário da segunda coluna
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    // Método para excluir um usuário do banco de dados
    private void deleteUser(String userName) {
        int result = myDB.delete("criar", "nome=?", new String[]{userName});
        if (result > 0) {
            Toast.makeText(ListaUsuario.this, "Usuário excluído com sucesso", Toast.LENGTH_SHORT).show();
            loadUsers(); // Recarrega a lista após a exclusão
        } else {
            Toast.makeText(ListaUsuario.this, "Erro ao excluir usuário", Toast.LENGTH_SHORT).show();
        }
    }
}
