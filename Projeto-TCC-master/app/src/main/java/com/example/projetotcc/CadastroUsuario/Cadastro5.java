package com.example.projetotcc.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.Controller;
import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.R;
import com.example.projetotcc.Usuario;

public class Cadastro5 extends AppCompatActivity {
    public static RequestQueue requestQueue;
    Context context;
    public static Usuario usuario;
    private Controller controller;
    private Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_5);

        usuario = Cadastro4.usuario;
        context = this;
        this.controller = new Controller();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public void Cadastrar(View var1) {
        controller.CadastrarUser(new Controller.VolleyCallbackUsuario() {
            @Override
            public void onSuccess(String response, Usuario user) {
                if(user == null) {
                    Toast.makeText(context.getApplicationContext(), usuario.getEmail() , Toast.LENGTH_SHORT).show();
                }
                else if(user.getEmail() == usuario.getEmail())
                {
                    Toast.makeText(context.getApplicationContext(), usuario.getEmail() , Toast.LENGTH_SHORT).show();
                    it = new Intent(context, PaginaUsuario.class);
                    context.startActivity(it);
                }
            }
        }, usuario);
    }

    protected void onActivityResult(int var1, int var2, Intent var3) {
        super.onActivityResult(var1, var2, var3);
    }
}