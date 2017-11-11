package com.projeto.note1_0;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Nota extends AppCompatActivity{

    Toolbar toolbar;
    private CharSequence mTitle;
    private AlertDialog alerta;

    int position;
    CriaBanco helpher;
    List<Model> dbList;
    TextView tvId, tvDataCriacao, tvDataEdicao, tvDataExclusao, tvStatus, tvTitulo, tvConteudo;

    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_nota);

        mTitle = getTitle();

        setupToolbar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaCampos_exit();
            }
        });

        setTitle(R.string.title_nota);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");

        tvId = (TextView) findViewById(R.id.tvId);
        tvDataCriacao  = (TextView)findViewById(R.id.tvDataCriacao);
        tvDataEdicao   = (TextView)findViewById(R.id.tvDataEdicao);
        tvStatus   =(TextView)findViewById(R.id.tvStatus);
        tvTitulo   = (EditText)findViewById(R.id.edTitulo);
        tvConteudo = (EditText)findViewById(R.id.edConteudo);

        helpher = new CriaBanco(this);
        dbList= new ArrayList<Model>();
        dbList = helpher.getDataFromBD();

        if(dbList.size() > 0){

            int id= dbList.get(position).getId();
            String dataCriacao = dbList.get(position).getDataCriacao();
            String dataEdicao = dbList.get(position).getDataEdicao();
            String status = dbList.get(position).getStatus();
            String titulo = dbList.get(position).getTitulo();
            String conteudo = dbList.get(position).getConteudo();

            tvId.setText("ID: " + id);
            tvDataCriacao.setText("Data de Criação: " + dataCriacao);
            tvDataEdicao.setText("Data de Edição: " + dataEdicao);
            tvStatus.setText("Status: " + status);
            tvTitulo.setText(titulo);
            tvConteudo.setText(conteudo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editar:
                validaCampos();
                return (true);

            case R.id.excluir:
                confirmaExclusao();
                return (true);
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void setTitle(CharSequence title){
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void editarNota(){
        int codNota = dbList.get(position).getId();
        String tituloNota = tvTitulo.getText().toString();
        String conteudo = tvConteudo.getText().toString();
        String dataEdicao = dataAtual();

        BancoController bd = new BancoController(getBaseContext());

        String mensagem;
        mensagem = bd.editarNota(codNota, tituloNota, conteudo, dataEdicao);
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }

    private void lixeira(){
        int codNota = dbList.get(position).getId();
        String status = "F";
        String dataExclusao = dataAtual();

        BancoController bd = new BancoController(getBaseContext());

        String mensagem;
        mensagem = bd.lixeira(codNota, status, dataExclusao);
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

        Intent home = new Intent(Nota.this, Home.class);
        startActivity(home);
    }

    public void validaCampos(){
        if (tvTitulo.getText().toString().equals("")){
            Toast.makeText(this, "Preencha o campo 'Título'", Toast.LENGTH_SHORT).show();
        }
        else{
            editarNota();

            Intent home = new Intent(Nota.this, Home.class);
            startActivity(home);
        }
    }

    public void validaCampos_exit(){
        if (tvTitulo.getText().toString().equals("")){
            Toast.makeText(this, "Preencha o campo 'Título'", Toast.LENGTH_SHORT).show();
        }
        else{
            editarNota();

            Intent home = new Intent(Nota.this, Home.class);
            startActivity(home);
        }
    }

    static String dataAtual(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    private void confirmaExclusao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Deseja mover a nota para a lixeira?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                lixeira();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerta = builder.create();
        alerta.show();
    }
}
