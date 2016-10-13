package com.jonss.github.agenda;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jonss.github.agenda.model.Prova;

import java.io.Serializable;


public class DetalhesProvaFragment extends Fragment {

    private TextView campoMateria;
    private TextView campoData;
    private ListView listaTopicos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        campoMateria = (TextView) view.findViewById(R.id.detalhe_prova_materia);
        campoData = (TextView) view.findViewById(R.id.detalhe_prova_data);
        listaTopicos = (ListView) view.findViewById(R.id.detalhe_prova_lista);

        Bundle arguments = getArguments();
        if(arguments != null) {
            Prova prova = (Prova) arguments.getSerializable("prova");
            populaCamposCom(prova);
        }

        return view;
    }

    public void populaCamposCom(Prova prova) {
        campoMateria.setText(prova.getMateria());
        campoData.setText(prova.getData());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, prova.getTopicos());
        listaTopicos.setAdapter(adapter);
    }
}
