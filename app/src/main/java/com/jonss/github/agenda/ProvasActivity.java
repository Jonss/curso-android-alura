package com.jonss.github.agenda;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jonss.github.agenda.model.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        tx.replace(R.id.frame_principal, new ListaProvasFragment());
        if(isLandscape()) {
            tx.replace(R.id.frame_secundario, new DetalhesProvaFragment());
        }
        tx.commit();


    }

    private boolean isLandscape() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selecionaProva(Prova prova) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        if(!isLandscape()) {
            DetalhesProvaFragment detalhesfragment = new DetalhesProvaFragment();
            Bundle params = new Bundle();
            params.putSerializable("prova", prova);
            detalhesfragment.setArguments(params);
            tx.replace(R.id.frame_principal, detalhesfragment);
            tx.commit();
        } else {
            DetalhesProvaFragment detalhesFragment =
                    (DetalhesProvaFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesFragment.populaCamposCom(prova);
        }
    }
}
