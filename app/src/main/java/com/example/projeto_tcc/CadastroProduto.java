package com.example.projeto_tcc;

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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroProduto extends AppCompatActivity {
    Controller controller;
    String getCategoria;
    EditText nome, descricao, preco;
    private Intent it;
    private RequestQueue requestQueue;
    private int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    public static Uri filePath;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        nome = findViewById(R.id.nomeProduto);
        preco = findViewById(R.id.precoProduto);
        descricao = findViewById(R.id.descricaoProduto);
        imageView = (ImageView) findViewById(R.id.ImagemCadProduto) ;
        controller = new Controller();
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategoria);
        String[] Categoria = new String[]{
                "Categoria",
                "Moda",
                "Casa"
        };

        final List<String> SexoList = new ArrayList<>(Arrays.asList(Categoria));
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, SexoList) {
            @Override
            public boolean isEnabled(int position) {
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getCategoria = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selecionado : " + getCategoria, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                Toast.makeText
                        (getApplicationContext(), e.toString(), Toast.LENGTH_SHORT)
                        .show();;
            }
        }
    }
    public void SelecionarImagem(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void CadastrarP(View v) {
        final String Nome, Preco, Descricao;
        Nome = nome.getText().toString();
        Preco = preco.getText().toString();
        Descricao = descricao.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, Constants.insertUrlProduto, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                it = new Intent(CadastroProduto.this, PagInicial.class);
                startActivity(it);
                controller.uploadMultipart(Nome, filePath);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nome", Nome);
                parameters.put("preco", Preco);
                parameters.put("descricao", Descricao);
                return parameters;
            }
        };
        requestQueue.add(request);
    }
}