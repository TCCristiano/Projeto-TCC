package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Intent it = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Entrar(View v)
    {
        this.it = new Intent(MainActivity.this, PagLogin.class);
        startActivity(this.it);
    }
    public void Cadastrar(View v)
    {
        this.it = new Intent(MainActivity.this, CadastroUsuario.class);
        startActivity(this.it);
    }
}