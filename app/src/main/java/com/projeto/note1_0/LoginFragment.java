package com.projeto.note1_0;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment{

    EditText txtUsuario;
    EditText txtSenha;

    public LoginFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });

        Button btnCriarConta = (Button) rootView.findViewById(R.id.btnCriarConta);
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent criarConta = new Intent(getActivity(), CriarConta.class);
                getActivity().startActivity(criarConta);
            }
        });

       txtUsuario = (EditText) rootView.findViewById(R.id.txtUsuario);
        txtSenha = (EditText) rootView.findViewById(R.id.txtSenha);

        return rootView;
    }

    public void logar(){
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        BancoController bd = new BancoController(getActivity());

        Cursor dados = bd.loginConta(usuario, senha);

        if (dados.moveToFirst()){
            Toast.makeText(getActivity(), "Login Efetuado com Sucesso", Toast.LENGTH_SHORT).show();
            sincronizacao();
        }
        else{
            Toast.makeText(getActivity(), "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }

    public void validarCampos(){

        if (txtUsuario.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        if (txtSenha.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        else{
            logar();
        }
    }

    public void sincronizacao(){
        Fragment fragment = new SincronizacaoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

}
