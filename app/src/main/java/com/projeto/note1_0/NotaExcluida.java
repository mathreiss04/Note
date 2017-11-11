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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 01/06/2017.
 */

public class NotaExcluida extends AppCompatActivity {

    Toolbar toolbar;
    private CharSequence mTitle;
    private AlertDialog alerta;


    int position;
    CriaBanco helpher;
    List<ModelLixeira> dbList;
    TextView tvId, tvDataCriacao, tvDataEdicao, tvDataExclusao, tvStatus, tvTitulo, tvConteudo;

    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_nota_excluida);

        mTitle = getTitle();

        setupToolbar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(NotaExcluida.this, Home.class);
                startActivity(home);
            }
        });

        setTitle(R.string.title_notaExcluida);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");

        tvId = (TextView) findViewById(R.id.tvIdExcluida);
        tvDataCriacao  = (TextView)findViewById(R.id.tvDataCriacaoExcluida);
        tvDataEdicao   = (TextView)findViewById(R.id.tvDataEdicaoExcluida);
        tvStatus   =(TextView)findViewById(R.id.tvStatusExcluida);
        tvTitulo   = (TextView) findViewById(R.id.tvTituloExcluida);
        tvConteudo = (TextView) findViewById(R.id.tvConteudoExcluida);

        helpher = new CriaBanco(this);
        dbList= new ArrayList<ModelLixeira>();
        dbList = helpher.getDataFromBDLixeira();

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
        getMenuInflater().inflate(R.menu.menu_nota_excluida, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.recuperar:
                confirmaRecuperacao();
                return (true);

            case R.id.deletar:
                confirmaDelete();
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

    private void recuperaNota(){
        int codNota = dbList.get(position).getId();
        String status = "T";

        BancoController bd = new BancoController(getBaseContext());

        String mensagem;
        mensagem = bd.recuperaNota(codNota, status);
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

        Intent home = new Intent(NotaExcluida.this, Home.class);
        startActivity(home);
    }

    private void deletaNota(){
        int codNota = dbList.get(position).getId();

        BancoController bd = new BancoController(getBaseContext()) ;
        String mensagem ;

        mensagem = bd.deletaNota(codNota) ;

        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

        Intent home = new Intent(NotaExcluida.this, Home.class);
        startActivity(home);
    }

    private void confirmaRecuperacao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Deseja recuperar a nota?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recuperaNota();
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

    private void confirmaDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Deseja deletar a nota permanentemente?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deletaNota();
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
