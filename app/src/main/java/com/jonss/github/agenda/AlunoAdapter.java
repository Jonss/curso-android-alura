package com.jonss.github.agenda;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jonss.github.agenda.model.Aluno;

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
        TextView textView = new TextView(mContext);
        Aluno aluno = mAlunos.get(position);
        textView.setText(aluno.toString());
        return textView;
    }
}
