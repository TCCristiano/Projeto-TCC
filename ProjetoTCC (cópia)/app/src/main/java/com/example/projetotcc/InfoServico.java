package com.example.projetotcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.projetotcc.ui.home.HomeFragment;
import com.example.projetotcc.ui.listaFragment.ListaCategoriasFragment;

public class InfoServico extends AppCompatActivity {

    Servico servico;

    TextView nome, preco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_servico);
        servico = ListaCategoriasFragment.servico;

        nome = findViewById(R.id.NomeInfoServico);
        preco = findViewById(R.id.PrecoInfoServico);

        Log.i("Aqui", "Meu Serviço: "+ servico.getTipo());

        nome.setText(servico.getNome());
        preco.setText(servico.getPreco() + "R$");

    }
}