package com.jonss.github.agenda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jonss.github.agenda.dao.AlunoDao;
import com.jonss.github.agenda.helper.FormularioHelper;
import com.jonss.github.agenda.model.Aluno;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    public static final int PHOTO_CODE = 123;
    private FormularioHelper mFormularioHelper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        mFormularioHelper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null) {
            mFormularioHelper.setAluno(aluno);
        }

        Button botaoCamera = (Button) findViewById(R.id.formulario_camera_button);
        botaoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/ " + System.currentTimeMillis() + " .jpg";
                File file = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intentCamera, PHOTO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_CODE) {
                ImageView image = (ImageView) findViewById(R.id.formulario_imagem);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap scaledBitmap = bitmap.createScaledBitmap(bitmap, 300, 300, true);
                image.setImageBitmap(scaledBitmap);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
            }
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
                if (aluno.getId() == null) {
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
