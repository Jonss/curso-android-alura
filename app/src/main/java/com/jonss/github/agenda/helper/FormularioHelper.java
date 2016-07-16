package com.jonss.github.agenda.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import com.jonss.github.agenda.FormularioActivity;
import com.jonss.github.agenda.R;
import com.jonss.github.agenda.model.Aluno;

/**
 * Created by neuromancer on 11/07/16.
 */
public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private Aluno aluno;


    public FormularioHelper(FormularioActivity formularioActivity) {
        campoNome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) formularioActivity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) formularioActivity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) formularioActivity.findViewById(R.id.formulario_site);
        campoNota = (RatingBar) formularioActivity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoTelefone.setText(aluno.getTelefone());
        campoNota.setRating(aluno.getNota().floatValue());
        this.aluno = aluno;
    }
}

