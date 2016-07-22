package com.jonss.github.agenda.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
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
    private final ImageView campoFoto;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity formularioActivity) {
        campoNome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) formularioActivity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) formularioActivity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) formularioActivity.findViewById(R.id.formulario_site);
        campoNota = (RatingBar) formularioActivity.findViewById(R.id.formulario_nota);
        campoFoto = (ImageView) formularioActivity.findViewById(R.id.formulario_imagem);
        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setCaminhoFoto((String) campoFoto.getTag());

        return aluno;
    }

    public void setAluno(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoTelefone.setText(aluno.getTelefone());
        campoNota.setRating(aluno.getNota().floatValue());
        setFoto(aluno.getCaminhoFoto());
        Log.d("ALUNO: ", aluno.getCaminhoFoto() + " nome: " + aluno.getNome());
        this.aluno = aluno;
    }

    public void setFoto(String caminhoFoto) {
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap scaledBitmap = bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(scaledBitmap);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}

