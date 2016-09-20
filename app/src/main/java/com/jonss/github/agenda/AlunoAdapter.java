package com.jonss.github.agenda;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonss.github.agenda.model.Aluno;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by neuromancer on 25/07/16.
 */
public class AlunoAdapter extends BaseAdapter {

    private Context mContext;
    private List<Aluno> mAlunos;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        mContext = context;
        mAlunos = alunos;
    }

    @Override
    public int getCount() {
        return mAlunos.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mAlunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = mAlunos.get(position);

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = convertView;
        if(convertView == null) {
             view = inflater.inflate(R.layout.lista_item, parent, false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_image);

        TextView campoEndereco = (TextView) view.findViewById(R.id.item_endereco);
        if (campoEndereco != null){
            campoEndereco.setText(aluno.getEndereco());
        }

        TextView campoSite = (TextView) view.findViewById(R.id.item_site);
        if (campoSite != null){
            campoSite.setText(aluno.getSite());
        }

        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap scaledBitmap = bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(scaledBitmap);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
