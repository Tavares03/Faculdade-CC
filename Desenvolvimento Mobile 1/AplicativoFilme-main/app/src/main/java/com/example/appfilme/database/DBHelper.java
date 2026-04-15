package com.example.appfilme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appfilme.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "filmes.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favoritos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "sinopse TEXT, " +
                "nota REAL, " +
                "poster TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void inserir(Filme filme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", filme.title);
        values.put("sinopse", filme.overview);
        values.put("nota", filme.vote_average);
        values.put("poster", filme.poster_path);
        db.insert("favoritos", null, values);
    }


    public List<Filme> listar() {
        List<Filme> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM favoritos", null);

        if (cursor.moveToFirst()) {
            do {
                Filme filme = new Filme();

                filme.title = cursor.getString(1);
                filme.overview = cursor.getString(2);
                filme.vote_average = cursor.getDouble(3);
                filme.poster_path = cursor.getString(4);
                lista.add(filme);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }
    public void deletar(String titulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("favoritos", "titulo = ?", new String[]{titulo});
    }
}