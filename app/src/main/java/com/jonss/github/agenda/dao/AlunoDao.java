package com.jonss.github.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jonss.github.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neuromancer on 12/07/16.
 */
public class AlunoDao extends SQLiteOpenHelper {

    private static final String DATABASE = "Agenda";
    private static final Integer VERSION = 1;
    private static final String TABLE = "alunos";

    public AlunoDao(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "(id INTEGER PRIMARY KEY," +
                " nome TEXT NOT NULL," +
                " endereco TEXT," +
                "telefone TEXT," +
                " site TEXT," +
                " caminho_foto TEXT," +
                " nota REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE " + TABLE + " ADD COLUMN caminho_foto TEXT";
                db.execSQL(sql);
        }
    }

    public void create(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getAlunoContentValues(aluno);

        db.insert(TABLE, null, values);
        this.close();
    }

    @NonNull
    private ContentValues getAlunoContentValues(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminho_foto", aluno.getCaminhoFoto());
        return values;
    }

    public List<Aluno> getAll() {
        String sql = "SELECT * from " + TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminho_foto")));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {aluno.getId().toString()};
        db.delete(TABLE, "id = ?", params);
        this.close();
    }

    public void update(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getAlunoContentValues(aluno);
        String[] params = {aluno.getId().toString()};

        Log.d("ALUNO DB", aluno.getCaminhoFoto());

        db.update(TABLE, values, "id = ?", params);
        this.close();
    }


}

