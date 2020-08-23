package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PagLogin extends AppCompatActivity  {
    Intent it = null;
    public EditText emailm;
    public EditText senham;
    private Context context;
    private Controller Controller;
    private Usuario usuario;
    private static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_login);
        context = this;
        usuario = new Usuario();
        Controller = Controller.getInstance(context);
        emailm = findViewById(R.id.emailL);
        senham = findViewById(R.id.senhaL);
    }
    public void Entrar(View v) throws Exception {

            String email = emailm.getText().toString();
            String senha = senham.getText().toString();
            usuario = Controller.validaLogin(email, senha);
            if (usuario.getNome_cliente() != null)
            {
                this.id = usuario.getCod_cliente();
                this.it = new Intent(PagLogin.this, PagInicial.class);
                startActivity(this.it);
            } else {
                Toast.makeText(this, "Verifique usuario e senha!", Toast.LENGTH_SHORT).show();
            }

    }
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PagLogin.id = id;
    }
}