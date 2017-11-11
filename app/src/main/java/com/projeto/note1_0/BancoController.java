package com.projeto.note1_0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
        db = banco.getWritableDatabase();
    }

    public String salvaNota(String dataCriacao, String dataEdicao, String dataExclusao, String status, String tituloNota, String conteudo){
        ContentValues valores;
        long resultado;

        valores = new ContentValues();
        valores.put("dataCriacao", dataCriacao);
        valores.put("dataEdicao", dataEdicao);
        valores.put("dataExclusao", dataExclusao);
        valores.put("status", status);
        valores.put("tituloNota", tituloNota);
        valores.put("conteudo", conteudo);

        resultado = db.insert("Nota", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public String criarConta(String usuario, String senha){
        ContentValues valores;

        long resultado;

        valores = new ContentValues();
        valores.put("usuario", usuario);
        valores.put("senha", senha);

        resultado = db.insert("Conta", null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao criar conta";
        else
            return "Conta criada com sucesso";
    }

    public Cursor loginConta(String usuario, String senha) {
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM Conta WHERE usuario = ? AND senha = ?", new String[]{usuario, senha});

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String editarNota(int codNota, String tituloNota, String conteudo, String dataEdicao){
        db = banco.getWritableDatabase();

        ContentValues valores;

        valores = new ContentValues();
        valores.put("tituloNota", tituloNota);
        valores.put("conteudo", conteudo);
        valores.put("dataEdicao", dataEdicao);

        String condicao = "codNota = " + codNota;

        int linha = db.update("Nota", valores, condicao, null);
        db.close();

        if (linha > 0){
            return "Dados alterados com sucesso!";
        }
        else{
            return "Erro ao alterar";
        }
    }

    public String lixeira(int codNota, String status, String dataExclusao){
        db = banco.getWritableDatabase();

        ContentValues valores;

        valores = new ContentValues();
        valores.put("status", status);
        valores.put("dataExclusao", dataExclusao);

        String condicao = "codNota = " + codNota;

        int linha = db.update("Nota", valores, condicao, null);
        db.close();

        if(linha > 0){
            return "Nota movida para a lixeira";
        }
        else{
            return "Erro ao mover para a lixeira";
        }
    }

    public String recuperaNota(int codNota, String status){
        db = banco.getWritableDatabase();

        ContentValues valores;

        valores = new ContentValues();
        valores.put("status", status);

        String condicao = "codNota = " + codNota;

        int linha = db.update("Nota", valores, condicao, null);
        db.close();

        if(linha > 0){
            return "Nota recuperada com sucesso";
        }
        else{
            return "Erro ao recuperar nota";
        }
    }

    public String deletaNota(int codNota) {

        db = banco.getWritableDatabase();

        String condicao = "codNota = " + codNota ;

        int linha = db.delete("Nota", condicao, null) ;
        db.close() ;

        if(linha > 0 ){
            return "Nota deletada com sucesso!";
        }else{
            return "Erro ao deletar";
        }


    }
}
