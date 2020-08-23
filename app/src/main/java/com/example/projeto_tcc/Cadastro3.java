package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro3 extends AppCompatActivity {
    private EditText email, username, tel;
    private static String emailget;
    private static String usernameget;
    private static int telget;
    Intent it = null;
    private Usuario usuario;
    Context context;
    Boolean E, U, T;
    UsuarioDAO usuarioDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro3);

        email = findViewById(R.id.emailC);
        username = findViewById(R.id.nomeUserC);
        tel = findViewById(R.id.TelC);
    }
    public void Cadastrar(View v)
    {
        this.emailget = email.getText().toString();
        this.usernameget = username.getText().toString();
        this.telget = Integer.parseInt(tel.getText().toString());


        if(emailget == null)
        {
            Toast.makeText(this," email está vazio",Toast.LENGTH_SHORT).show();
        }
        else if (usernameget == null)
        {
            Toast.makeText(this," userName está vazio",Toast.LENGTH_SHORT).show();
        }
        else if (telget == 0) {
            Toast.makeText(this," telefone está vazio",Toast.LENGTH_SHORT).show();
        }
        else
        {
            this.it = new Intent(Cadastro3.this,Cadastro4.class);
            startActivity(this.it);
        }
    }
    public void voltar(View v)
    {
        this.it = new Intent(Cadastro3.this,Cadastro2.class);
        startActivity(this.it);
    }

    public String getEmailget() {
        return emailget;
    }

    public void setEmailget(String emailget) {
        this.emailget = emailget;
    }

    public String getUsernameget() {
        return usernameget;
    }

    public void setUsernameget(String usernameget) {
        this.usernameget = usernameget;
    }

    public int getTelget() {
        return telget;
    }

    public void setTelget(int telget) {
        this.telget = telget;
    }
}