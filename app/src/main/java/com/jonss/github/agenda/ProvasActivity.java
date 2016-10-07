package com.jonss.github.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jonss.github.agenda.model.Prova;

import java.util.Arrays;
import java.util.List;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> assuntosMat = Arrays.asList("Equação de 2º grau", "Integral");
        List<String> assuntosPort = Arrays.asList("Conjugação","Sintaxe","Modernismo");
        Prova provaPort = new Prova("Português", "22/10/2016", assuntosPort);
        Prova provaMat = new Prova("Matemática", "30/10/2016", assuntosMat);
        List<Prova> provas = Arrays.asList(provaMat, provaPort);

        ListView listaProvas = (ListView) findViewById(R.id.provas_lista);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, provas);

        listaProvas.setAdapter(adapter);


        listaProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(ProvasActivity.this, prova.getMateria(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
