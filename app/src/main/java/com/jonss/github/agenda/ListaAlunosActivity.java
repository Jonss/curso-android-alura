package com.jonss.github.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
    private Aluno aluno;

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
        AlunoAdapter adapter = new AlunoAdapter(this, alunos);
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
        aluno = (Aluno) listView.getItemAtPosition(info.position);

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
            case R.id.menu_ver_no_mapa:
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo=0,0?q=" + aluno.getEndereco()));
                item.setIntent(intentMapa);
                break;
            case R.id.menu_enviar_sms:
                Intent intentSms = new Intent(Intent.ACTION_VIEW);
                intentSms.setData(Uri.parse("sms:" + aluno.getTelefone()));
                item.setIntent(intentSms);
                break;
            case R.id.menu_ligar:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ListaAlunosActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE}, 123);
                        } else {
                            Intent intentLigar = new Intent(Intent.ACTION_CALL);
                            intentLigar.setData(Uri.parse("tel:" + aluno.getTelefone()));
                            startActivity(intentLigar);
                        }
                        return false;
                    }
                });

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
