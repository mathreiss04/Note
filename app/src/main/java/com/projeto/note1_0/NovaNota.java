package com.projeto.note1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NovaNota extends AppCompatActivity{

    Toolbar toolbar;
    private CharSequence mTitle;
    EditText txtTitulo;
    EditText txtConteudo;

    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_nova_nova);

        mTitle = getTitle();

        setupToolbar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaCampos_exit();
            }
        });

        setTitle(R.string.title_nova_nova);

        txtTitulo   = (EditText) findViewById(R.id.tituloNota);
        txtConteudo = (EditText) findViewById(R.id.conteudoNota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_nova_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.salvar:
                validaCampos();
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

   /* @SuppressLint("NewApi")
    private Connection getConnection(){

        String usuario = "matrix";
        String senha = "12345";
        String banco = "note";
        String server = "JARVIS";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection con = null;
        String conURL;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conURL = "jdbc:jtds:sqlserver://" + server + ";" + banco + ";user=" + usuario + ";password=" + senha + ";";
            con = DriverManager.getConnection(conURL);

            Log.e("Banco","Conexão Aberta");

        }
        catch (SQLException se){
            Log.e("Erro 1", se.getMessage());
        }

        catch (ClassNotFoundException e){
            Log.e("Erro 2", e.getMessage());
        }

        catch (Exception e){
            Log.e("Erro 3", e.getMessage());
        }

        return con;
    } */

    public void salvarNota(){
        String dataCriacao = dataAtual();
        String dataEdicao = null;
        String dataExclusao = null;
        String status = "T";
        String tituloNota = txtTitulo.getText().toString();
        String conteudo = txtConteudo.getText().toString();

        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        resultado = bd.salvaNota(dataCriacao, dataEdicao, dataExclusao, status, tituloNota, conteudo);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
    }

    static String dataAtual(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public void limpaCampos(){
        txtTitulo.setText("");
        txtConteudo.setText("");
    }

    public void validaCampos_exit(){
        if (txtTitulo.getText().toString().equals("") && txtConteudo.getText().toString().equals("")){
            Intent home = new Intent(NovaNota.this, Home.class);
            startActivity(home);
        }
        else{
            salvarNota();

            Intent home = new Intent(NovaNota.this, Home.class);
            startActivity(home);
        }
    }

    public void validaCampos(){
        if (txtTitulo.getText().toString().equals("")){
            Toast.makeText(this, "Preencha o campo 'Título'", Toast.LENGTH_SHORT).show();
        }
        else{
            salvarNota();
            limpaCampos();
        }
    }
}
