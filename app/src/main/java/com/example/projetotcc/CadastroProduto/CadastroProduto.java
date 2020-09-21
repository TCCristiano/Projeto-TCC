package com.example.projetotcc.CadastroProduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.Controller;
import com.example.projetotcc.MainActivity;
import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CadastroProduto extends AppCompatActivity {
    public static Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;
    Controller controller;
    EditText descricao;
    String getCategoria;
    ImageView imageView;
    EditText nome;
    Bitmap bitmap;
    EditText preco;
    public static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        this.nome = (EditText)this.findViewById(R.id.nomeProduto);
        this.preco = (EditText)this.findViewById(R.id.precoProduto);
        this.descricao = (EditText)this.findViewById(R.id.descricaoProduto);
        this.imageView = (ImageView)this.findViewById(R.id.imgProduto);
        this.controller = new Controller();
        Spinner spinner = (Spinner)this.findViewById(R.id.tipoProduto);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.activity_cadastro_produto, new ArrayList(Arrays.asList(new String[]{"Categoria", "Moda", "Casa"}))) {
            public View getDropDownView(int var1, View var2, ViewGroup var3) {
                View var4 = super.getDropDownView(var1, var2, var3);
                TextView var5 = (TextView)var4;
                if (var1 == 0) {
                    var5.setTextColor(-7829368);
                    return var4;
                } else {
                    var5.setTextColor(-16777216);
                    return var4;
                }
            }

            public boolean isEnabled(int var1) {
                return var1 != 0;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.activity_cadastro_produto);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                CadastroProduto.this.getCategoria = (String)var1.getItemAtPosition(var3);
                if (var3 > 0) {
                    Context var6 = CadastroProduto.this.getApplicationContext();
                    StringBuilder var7 = new StringBuilder();
                    var7.append("Selecionado : ");
                    var7.append(CadastroProduto.this.getCategoria);
                    Toast.makeText(var6, var7.toString(), Toast.LENGTH_SHORT).show();
                }

            }
            public void onNothingSelected(AdapterView var1) {
            }
        });

        this.requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public void CadastrarProduto(View view) {
        final String nome = this.nome.getText().toString();
        final String preco = this.preco.getText().toString();
        final String descricao = this.descricao.getText().toString();
        controller.CadastrarP(nome, preco, descricao);
        Intent it = new Intent(CadastroProduto.this, PaginaUsuario.class);
        this.startActivity(it);

    }

    public void SelecionarImagem(View view) {
        Intent var2 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        var2.setType("image/*");
        this.startActivityForResult(Intent.createChooser(var2, "Select Picture"), this.PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int var1, int var2, Intent var3) {
        super.onActivityResult(var1, var2, var3);
        if (var1 == this.PICK_IMAGE_REQUEST && var2 == -1 && var3 != null && var3.getData() != null) {
            filePath = var3.getData();

            try {
                Bitmap var5 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                this.bitmap = var5;
                this.imageView.setImageBitmap(var5);
                return;
            } catch (IOException var6) {
                Toast.makeText(this.getApplicationContext(), var6.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}