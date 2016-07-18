package com.jonss.github.agenda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jonss.github.agenda.dao.AlunoDao;
import com.jonss.github.agenda.model.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listView;
    private AlunoDao alunoDao = new AlunoDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listView = (ListView) findViewById(R.id.lista_alunos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(position);
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

        Button novoAlunoButton = (Button) findViewById(R.id.novo_aluno);
        novoAlunoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        List<Aluno> alunos = alunoDao.getAll();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listView.setAdapter(adapter);
    }


    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_formulario_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_share);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno aluno = (Aluno) listView.getItemAtPosition(info.position);

        switch (item.getItemId()) {
            case R.id.menu_visitar_site:
                String site = aluno.getSite();
                if (!site.startsWith("http://")) {
                    site = "http://" + site;
                }
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                intentSite.setData(Uri.parse(site));
                item.setIntent(intentSite);
                break;
            case R.id.menu_deletar:
                alunoDao.deleta(aluno);
                carregaLista();
                Toast.makeText(this, "Aluno " + aluno.getNome() + "deletado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                startActivity(sendSiteFrom(aluno));
                break;
        }
        return super.onContextItemSelected(item);
    }

    @NonNull
    private Intent sendSiteFrom(Aluno aluno) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, aluno.getSite());
        return intent;
    }
}
