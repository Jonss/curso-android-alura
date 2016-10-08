package com.jonss.github.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jonss.github.agenda.model.Prova;

import java.io.Serializable;

public class ProvaDetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova_detalhes);

        Intent intent = getIntent();
        Prova prova = (Prova) intent.getSerializableExtra("prova");

        TextView materiaText = (TextView) findViewById(R.id.detalhe_prova_materia);
        TextView dataText = (TextView) findViewById(R.id.detalhe_prova_data);
        ListView lista = (ListView) findViewById(R.id.detalhe_prova_lista);

        materiaText.setText(prova.getMateria());
        dataText.setText(prova.getData());


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prova.getAssuntos());
        lista.setAdapter(adapter);

    }
}
