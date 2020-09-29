package com.example.projetotcc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainPrestadorLista extends AppCompatActivity implements PrestadorListener{
    private Button buttonAddListaFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prestadores);
        RecyclerView prestadorRecyclerView = findViewById(R.id.prestadorRecyclerView);
        buttonAddListaFavoritos = findViewById(R.id.buttonAddListaFavoritos);

        final List<Prestador> prestador = new ArrayList<>();

        Prestador carpinteiro = new Prestador();
        carpinteiro.image = R.drawable.carpinteiro;
        carpinteiro.nome = "João";
        carpinteiro.rating = 5f;
        carpinteiro.servico = "Carpinteiro";
        carpinteiro.descricao = "Faço serviços de carpinteiria";
        prestador.add(carpinteiro);

        Prestador eletricista = new Prestador();
        eletricista.image = R.drawable.eletricista;
        eletricista.nome = "Pedro";
        eletricista.rating = 5f;
        eletricista.servico = "eletricista";
        eletricista.descricao = "Faço serviços na parte elétrica";
        prestador.add(eletricista);

        Prestador eletricistas = new Prestador();
        eletricistas.image = R.drawable.eletricistas;
        eletricistas.nome = "Marcos e Vitor";
        eletricistas.rating = 5f;
        eletricistas.servico = "eletricistas";
        eletricistas.descricao = "Fazemos serviços na parte elétrica";
        prestador.add(eletricistas);

        Prestador encanador = new Prestador();
        encanador.image = R.drawable.encanador;
        encanador.nome = "Miguel";
        encanador.rating = 4f;
        encanador.servico = "encanador";
        encanador.descricao = "Fazemos serviços na parte de encanação";
        prestador.add(encanador);

        Prestador entregador = new Prestador();
        entregador.image = R.drawable.entregador;
        entregador.nome = "Lucas";
        entregador.rating = 3f;
        entregador.servico = "entregador";
        entregador.descricao = "Faço serviços de entregas";
        prestador.add(entregador);

        Prestador entregador2 = new Prestador();
        entregador2.image = R.drawable.mudanca2;
        entregador2.nome = "Leonardo";
        entregador2.rating = 4f;
        entregador2.servico = "entregador";
        entregador2.descricao = "Faço serviços de grandes mudanças";
        prestador.add(entregador2);

        Prestador entregador3 = new Prestador();
        entregador3.image = R.drawable.mudanca3;
        entregador3.nome = "Luiz";
        entregador3.rating = 4f;
        entregador3.servico = "entregador";
        entregador3.descricao = "Faço serviços de pequenas mudanças";
        prestador.add(entregador3);

        Prestador pintor = new Prestador();
        pintor.image = R.drawable.pintor;
        pintor.nome = "Luiz";
        pintor.rating = 2f;
        pintor.servico = "pintor";
        pintor.descricao = "Faço serviços de pintura";
        prestador.add(pintor);

        Prestador pintor2 = new Prestador();
        pintor2.image = R.drawable.pintor2;
        pintor2.nome = "Luiz";
        pintor2.rating = 5f;
        pintor2.servico = "pintor";
        pintor2.descricao = "Faço serviços de pintura";
        prestador.add(pintor2);

        final PrestadorAdapter prestadorAdapter = new PrestadorAdapter(prestador, this);
        prestadorRecyclerView.setAdapter(prestadorAdapter);

        buttonAddListaFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Prestador> selectedPrestador = prestadorAdapter.getSelectedPrestador();
                StringBuilder prestadorNomes = new StringBuilder();
                for (int i = 0; i < selectedPrestador.size(); i++){
                    if(i == 0){
                        prestadorNomes.append(selectedPrestador.get(i).nome);
                    }
                    else{
                        prestadorNomes.append("\n").append(selectedPrestador.get(i).nome);
                    }
                }
                Toast.makeText(MainPrestadorLista.this, prestadorNomes.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPrestadorAction(Boolean isSelected) {

        if(isSelected){
            buttonAddListaFavoritos.setVisibility(View.VISIBLE);
        }
        else{
            buttonAddListaFavoritos.setVisibility(View.GONE);
        }
    }
}
