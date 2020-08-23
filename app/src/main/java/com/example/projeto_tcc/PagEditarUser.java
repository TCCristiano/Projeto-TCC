package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PagEditarUser extends AppCompatActivity {
    private EditText nome;
    private EditText userName;
    private EditText email;
    private EditText senha;
    private EditText cpf;
    private EditText telefone;
    private EditText idade;
    private EditText estado;
    private EditText cidade;
    private EditText rua;
    private EditText numR;
    private EditText cep;
    private Context context;
    private Controller Controller;
    private Usuario usuario;
    private PagLogin login;
    Intent it = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_editar_user);

        Controller = Controller.getInstance(context);
        Integer i = login.getId();
        usuario = new Usuario();
        usuario = Controller.findbyid(i);

        Controller = new Controller();
        nome = findViewById(R.id.nomeEdit);
        userName = findViewById(R.id.userNameEdit);
        email = findViewById(R.id.emailEdit);
        senha = findViewById(R.id.senhaEdit);
        cpf = findViewById(R.id.cpfEdit);
        telefone = findViewById(R.id.telEdit);
        idade = findViewById(R.id.idadeEdit);

        nome.setText(String.valueOf(usuario.getNome_cliente()));
        email.setText(String.valueOf(usuario.getEmail_cliente()));
        userName.setText(String.valueOf(usuario.getUserName_cliente()));
        senha.setText(String.valueOf(usuario.getSenha_cliente()));
        telefone.setText(String.valueOf(usuario.getTelefone_cliente()));
        cpf.setText(String.valueOf(usuario.getCpf_cliente()));
        idade.setText(String.valueOf(usuario.getIdade_cliente()));


    }
    public void Edit(View v)
    {
        usuario = new Usuario();
        usuario.setCod_cliente(login.getId());
        usuario.setNome_cliente(nome.getText().toString());
        usuario.setUserName_cliente(userName.getText().toString());
        usuario.setEmail_cliente(email.getText().toString());
        usuario.setSenha_cliente(senha.getText().toString());
        usuario.setCpf_cliente(cpf.getText().toString());
        usuario.setTelefone_cliente(Integer.parseInt(telefone.getText().toString()));
        usuario.setIdade_cliente(Integer.parseInt(idade.getText().toString()));

        Boolean t = Controller.alterarUsuario(usuario);

        if( t == true)
        {
            Toast.makeText(this, "Usuario atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            this.it = new Intent(PagEditarUser.this, PagInicial.class);
            startActivity(this.it);
        }
        else
        {
            Toast.makeText(this, "Falha ao tentar atualizar o perfil!", Toast.LENGTH_SHORT).show();
        }

    }

}