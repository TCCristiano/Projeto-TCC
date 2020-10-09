package com.example.projetotcc.CadastroServico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.R;
import com.example.projetotcc.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CadastroServico extends AppCompatActivity {
    public static Uri filePath;
    public static RequestQueue requestQueue;
    private int PICK_IMAGE_REQUEST = 1;
    private Context context;
    private Controller controller;
    private EditText descricao;
    private String image;
    private ImageView imageView;
    private EditText nome;
    private EditText preco;
    private String selectedItemText;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);

        this.nome = (EditText)this.findViewById(R.id.nomeServico);
        this.preco = (EditText)this.findViewById(R.id.precoServico);
        this.descricao = (EditText)this.findViewById(R.id.descricaoServico);
        this.imageView = (ImageView)this.findViewById(R.id.imgProduto);
        this.controller = new Controller();
        context = this;
        usuario = PaginaUsuario.usuario;
        Spinner spinner = (Spinner)this.findViewById(R.id.tipoProduto);

        String[] StringTipo = new String[]{
                "Tipo", "Eletricista", "Encanador", "Pedreiro", "Pintor", "Carpinteiro", "Entregador", "Faxineiro", "Babá", "WashCar", "Ar-condicinado"
        };

        final List<String> sexoSelect = new ArrayList<>(Arrays.asList(StringTipo));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.activity_cadastro_servico,sexoSelect){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
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
                 selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public void CadastrarServico(View view) {
        final String nome = this.nome.getText().toString();
        final String preco = this.preco.getText().toString();
        final String descricao = this.descricao.getText().toString();
        controller.CadastrarServico(new Controller.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                Intent it = new Intent(CadastroServico.this, PaginaUsuario.class);
                context.startActivity(it);
            }
        }, nome, preco, descricao, selectedItemText, usuario, image);
    }

    public void SelecionarImagem(View view) {
        this.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI), this.PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        try {
            filePath = intent.getData();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap((ContentResolver)this.getContentResolver(), (Uri)filePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)byteArrayOutputStream);
            this.image = Base64.encodeToString((byte[])byteArrayOutputStream.toByteArray(), (int)0);
            this.imageView.setImageBitmap(bitmap);
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}