package com.example.projetotcc.CadastroProduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import java.util.List;

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

        String[] StringTipo = new String[]{
                "Tipo",
                "Moda",
                "Isso"
        };

        final List<String> sexoSelect = new ArrayList<>(Arrays.asList(StringTipo));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.activity_cadastro_produto,sexoSelect){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, StringTipo));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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