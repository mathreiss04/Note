package com.projeto.note1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CriarConta extends AppCompatActivity {

    Toolbar toolbar;
    private CharSequence mTitle;
    EditText txtLogin;
    EditText txtSenha;
    EditText txtConfirmaSenha;

    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_criar_conta);

        mTitle = getTitle();

        setupToolbar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(CriarConta.this, Home.class);
                startActivity(home);
            }
        });

        setTitle(R.string.title_criar_conta);



        Button button = (Button) findViewById(R.id.btnCriar_conta);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                validarCampos();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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

    public void validarCampos(){

        txtLogin   = (EditText) findViewById(R.id.novoLogin);
        txtSenha = (EditText) findViewById(R.id.novaSenha);
        txtConfirmaSenha = (EditText) findViewById(R.id.confirmaSenha);

        if(txtSenha.getText().toString().trim().matches(txtConfirmaSenha.getText().toString().trim())){
            criarConta();
        }
        else{
            Toast.makeText(getApplicationContext(), "As senhas não correspondem", Toast.LENGTH_SHORT).show();
        }

        if(txtLogin.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Por favor, preencha o campo 'Login'", Toast.LENGTH_SHORT).show();
        }

        if(txtSenha.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Por favor, preencha o campo 'Senha'", Toast.LENGTH_SHORT).show();
        }

        if(txtConfirmaSenha.getText().equals("")){
            Toast.makeText(getApplicationContext(), "'Por favor, preencha o campo 'Repita Senha'", Toast.LENGTH_SHORT).show();
        }
    }

    public void criarConta(){
        String usuario = txtLogin.getText().toString();
        String senha = txtSenha.getText().toString();

        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        resultado = bd.criarConta(usuario, senha);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
    }
}