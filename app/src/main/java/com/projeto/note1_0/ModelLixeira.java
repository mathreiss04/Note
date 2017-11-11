package com.projeto.note1_0;

/**
 * Created by Matheus on 01/06/2017.
 */

public class ModelLixeira {

    private int id;
    private String dataCriacao;
    private String dataEdicao;
    private String dataExclusao;
    private String status;
    private String titulo;
    private String conteudo;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDataCriacao(){
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao){
        this.dataCriacao = dataCriacao;
    }

    public String getDataEdicao(){
        return dataEdicao;
    }

    public void setDataEdicao(String dataEdicao){
        this.dataEdicao = dataEdicao;
    }

    public String getDataExclusao(){
        return dataExclusao;
    }

    public void setDataExclusao(String dataExclusao){
        this.dataExclusao = dataExclusao;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getConteudo(){
        return conteudo;
    }

    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
}

