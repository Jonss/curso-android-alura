package com.jonss.github.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                " nota REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

}
