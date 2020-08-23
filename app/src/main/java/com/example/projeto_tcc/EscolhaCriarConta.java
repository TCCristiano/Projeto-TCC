package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EscolhaCriarConta extends AppCompatActivity {
    Intent it = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_criar_conta);
    }
    public void Prestador(View v)
    {
        this.it = new Intent(EscolhaCriarConta.this, PagInicial.class);
        startActivity(this.it);
    }
    public void Cliente(View v)
    {
        this.it = new Intent(EscolhaCriarConta.this, CadastroUsuario.class);
        startActivity(this.it);
    }
}