package com.example.projetotcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.CadastroUsuario.Cadastro1;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static JSONObject jsonObject;
    public static RequestQueue requestQueue;
    public static Usuario usuario;
    public EditText emailm;
    public EditText senham;
    private Controller controller;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.emailm = (EditText)this.findViewById(R.id.emailL);
        this.senham = (EditText)this.findViewById(R.id.senhaL);
        usuario = new Usuario();
        controller = new Controller();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public void Entrar(View view) throws Exception
    {
         String Email = this.emailm.getText().toString();
         String Senha = this.senham.getText().toString();
         controller.Login(Email, Senha, usuario);


        if(usuario.getNome() == null)
        {
            Toast.makeText(this, "Falha" + usuario.getEmail() , Toast.LENGTH_LONG).show();
        }
        else if(usuario.getNome().isEmpty() && usuario.getEmail().isEmpty()){Toast.makeText(this, " Email ou senha não encontrados", Toast.LENGTH_LONG).show();}
        else
        {
            it = new Intent(this, PaginaUsuario.class);
            this.startActivity(it);
        }
    }

    public void Cadastrar(View view)
    {
        it = new Intent(this, Cadastro1.class);
        this.startActivity(it);
    }


}