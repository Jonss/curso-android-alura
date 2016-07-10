package com.jonss.github.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Danzig", "Doyle", "Jerry Only", "Michalle Graves", "Robo","Danzig", "Doyle", "Jerry Only", "Michalle Graves", "Robo","Danzig", "Doyle", "Jerry Only", "Michalle Graves", "Robo"};

        ListView listView = (ListView) findViewById(R.id.lista_alunos);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listView.setAdapter(adapter);

        Button novoAlunoButton = (Button) findViewById(R.id.novo_aluno);
        novoAlunoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

    }

}
