package com.jonss.github.agenda;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jonss.github.agenda.model.Prova;

import java.util.Arrays;
import java.util.List;

/**
 * Created by neuromancer on 09/10/16.
 */
public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> assuntosMat = Arrays.asList("Equação de 2º grau", "Integral");
        List<String> assuntosPort = Arrays.asList("Conjugação","Sintaxe","Modernismo");
        Prova provaPort = new Prova("Português", "22/10/2016", assuntosPort);
        Prova provaMat = new Prova("Matemática", "30/10/2016", assuntosMat);
        List<Prova> provas = Arrays.asList(provaMat, provaPort);

        ListView listaProvas = (ListView) view.findViewById(R.id.provas_lista);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, provas);

        listaProvas.setAdapter(adapter);

        listaProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), prova.getMateria(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ProvaDetalhesActivity.class);
                intent.putExtra("prova", prova);
                startActivity(intent);
            }
        });

        return view;
    }
}
