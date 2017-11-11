package com.projeto.note1_0;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CriaBanco extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "note";
    private static final int VERSAO = 1;
    Context context;

    public CriaBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlNota = "CREATE TABLE Nota("
                + "codNota integer Primary Key identity(1,1),"
                + "dataCriacao varchar(9),"
                + "dataEdicao varchar(9),"
                + "dataExclusao varchar(9),"
                + "status char(1),"
                + "tituloNota varchar(20),"
                + "conteudo text)";

        String sqlConta = "CREATE TABLE Conta("
                + "codConta integer Primary Key autoincrement,"
                + "usuario varchar(8),"
                + "senha char(8))";

        db.execSQL(sqlNota);
        db.execSQL(sqlConta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Nota");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS Conta");
        onCreate(db);
    }

    public List<Model> getDataFromBD(){
        List<Model> modelList = new ArrayList<Model>();
        String query = "SELECT * FROM Nota WHERE status = ?";
        String status = "T";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String []{status});

        if(cursor.moveToFirst()){
            do{
                Model model = new Model();

                model.setId(cursor.getInt(0));
                model.setDataCriacao(cursor.getString(1));
                model.setDataEdicao(cursor.getString(2));
                model.setDataExclusao(cursor.getString(3));
                model.setStatus(cursor.getString(4));
                model.setTitulo(cursor.getString(5));
                model.setConteudo(cursor.getString(6));

                modelList.add(model);
            }while (cursor.moveToNext());
        }

        Log.d("Nota", modelList.toString());

        return modelList;
    }

    public List<ModelLixeira> getDataFromBDLixeira(){
        List<ModelLixeira> modelList = new ArrayList<ModelLixeira>();
        String query = "SELECT * FROM Nota WHERE status = ?";
        String status = "F";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String []{status});

        if(cursor.moveToFirst()){
            do{
                ModelLixeira model = new ModelLixeira();

                model.setId(cursor.getInt(0));
                model.setDataCriacao(cursor.getString(1));
                model.setDataEdicao(cursor.getString(2));
                model.setDataExclusao(cursor.getString(3));
                model.setStatus(cursor.getString(4));
                model.setTitulo(cursor.getString(5));
                model.setConteudo(cursor.getString(6));

                modelList.add(model);
            }while (cursor.moveToNext());
        }

        Log.d("Nota", modelList.toString());

        return modelList;
    }
}
