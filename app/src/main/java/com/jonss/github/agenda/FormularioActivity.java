package com.jonss.github.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jonss.github.agenda.dao.AlunoDao;
import com.jonss.github.agenda.helper.FormularioHelper;
import com.jonss.github.agenda.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper mFormularioHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        mFormularioHelper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null) {
            mFormularioHelper.setAluno(aluno);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = mFormularioHelper.getAluno();
                AlunoDao alunoDao = new AlunoDao(this);
                if(aluno.getId() == null) {
                    alunoDao.create(aluno);
                } else {
                    alunoDao.update(aluno);
                }
                alunoDao.close();
                Toast.makeText(this, "Aluno " + aluno.getNome() + " salvo " + aluno.getNota(), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}
