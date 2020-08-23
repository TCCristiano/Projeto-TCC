package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {
    private EditText nome;
    private EditText sobrenome;
    private EditText cpf;
    private static String nomeget;
    private static String cpfget;
    private Context context = this;
    private Controller Controller;
    Intent it = null;
    public Usuario usuario;
    private static String Nome, Sobrenome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        nome = findViewById(R.id.nomeC);
        sobrenome = findViewById(R.id.Sobrenome);
        cpf = findViewById(R.id.cpfC);

    }
    public void Cadastrar(View v)
    {
       Nome = nome.getText().toString() + ""; Sobrenome = sobrenome.getText().toString() + "";
       cpfget = cpf.getText().toString() + "";
       if(Nome == "" || Sobrenome == "")
       {
           Toast.makeText(this," nome ou sobrenome está vazio",Toast.LENGTH_SHORT).show();
       }
       else if(cpfget == "")
       {
           Toast.makeText(this," cpf esta vazio",Toast.LENGTH_SHORT).show();
       }
       else
       {
               nomeget = Nome+" "+Sobrenome;
               this.it = new Intent(CadastroUsuario.this, Cadastro2.class);
               startActivity(this.it);
       }
    }
    public String getNomeget() {
        return nomeget;
    }

    public void setNomeget(String nomeget) {
        this.nomeget = nomeget;
    }

    public String getCpfget() {
        return cpfget;
    }

    public void setCpfget(String cpfget) {
        this.cpfget = cpfget;
    }
}