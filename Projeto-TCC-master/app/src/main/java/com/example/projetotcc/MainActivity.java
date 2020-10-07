package com.example.projetotcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.CadastroUsuario.Cadastro1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static RequestQueue requestQueue;
    public static Usuario usuario;
    Controller controller;
    public EditText emailm;
    public EditText senham;
    public static Context context;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.emailm = (EditText)this.findViewById(R.id.emailL);
        this.senham = (EditText)this.findViewById(R.id.senhaL);
        usuario = new Usuario();
        controller = new Controller();
        context = this;
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public void Entrar(View view) throws Exception
    {
         final String Email = this.emailm.getText().toString();
         final String Senha = this.senham.getText().toString();
        controller.Login(new Controller.VolleyCallbackUsuario(){
            @Override
            public void onSuccess(String result, Usuario user) {

                if (user.getEmail() != null) {
                    if (user.isErro() == false) {
                        usuario = user;
                        it = new Intent(context, PaginaUsuario.class);
                        context.startActivity(it);
                    }
                }
                else {
                    Toast.makeText(context.getApplicationContext(), "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        }, Email, Senha);

    }
    public void Cadastrar(View view)
    {
        it = new Intent(this, Cadastro1.class);
        this.startActivity(it);
    }

}