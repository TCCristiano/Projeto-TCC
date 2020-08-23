package com.example.projeto_tcc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro4 extends AppCompatActivity {
    private EditText senha, senhaC;
    private String senha1, senhaC1;
    private static String senhaget;

    private Usuario usuario;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro4);

        senha = findViewById(R.id.senhaC);
        senhaC = findViewById(R.id.senhacC);
    }
    public void Cadastrar(View v)
    {
        senhaget = senha.getText().toString() + "";
        if (senhaget == "")
        {Toast.makeText(this," senha ou confirmar senha está vazio",Toast.LENGTH_SHORT).show();}
        else {
            if (senha.getText().toString().equals(senhaC.getText().toString())) {
                this.senhaget = senha.getText().toString();

                this.it = new Intent(Cadastro4.this,CadastroImage.class);
                startActivity(this.it);
            } else {
                Toast.makeText(this,"As senhas não correspondem!",Toast.LENGTH_SHORT).show();
            }
        }
        }
    public void voltar(View v)
    {
        this.it = new Intent(Cadastro4.this,Cadastro3.class);
        startActivity(this.it);
    }


        public String getSenha() {
        return senhaget;
    }

        public void setSenha(String senha) {
        this.senhaget = senhaget;
    }
}
