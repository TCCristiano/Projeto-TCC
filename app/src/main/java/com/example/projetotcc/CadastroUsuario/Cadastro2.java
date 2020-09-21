package com.example.projetotcc.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetotcc.R;
import com.example.projetotcc.Usuario;

import java.util.ArrayList;
import java.util.Arrays;

public class Cadastro2 extends AppCompatActivity {
    private static String Data;
    private static String sexoget;
    public static Usuario usuario;
    private EditText data1;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_2);

        this.data1 = (EditText)this.findViewById(R.id.dataUserCadastro);
        usuario = Cadastro1.usuario;

        Spinner spinner = (Spinner)this.findViewById(R.id.sexoUserCadastro);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.activity_cadastro_2, new ArrayList(Arrays.asList(new String[]{"SEXO", "Masculino", "Feminino"}))) {
            public View getDropDownView(int var1, View var2, ViewGroup var3)
            {
                View var4 = super.getDropDownView(var1, var2, var3);
                TextView var5 = (TextView)var4;
                if (var1 == 0)
                {
                    var5.setTextColor(-7829368);
                    return var4;
                } else
                {
                    var5.setTextColor(-16777216);
                    return var4;
                }
            }
            public boolean isEnabled(int var1) {
                return var1 != 0;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.activity_cadastro_2);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView var1, View var2, int var3, long var4)
            {
                Cadastro2.sexoget = (String)var1.getItemAtPosition(var3);
                if (var3 > 0)
                {
                    Context var7 = Cadastro2.this.getApplicationContext();
                    StringBuilder var8 = new StringBuilder();
                    var8.append("Selecionado : ");
                    var8.append(Cadastro2.sexoget);
                    Toast.makeText(var7, var8.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            public void onNothingSelected(AdapterView var1){}
        });
    }

    public void Cadastrar(View view) {
        Data = String.valueOf(data1.getText() + "");
        if (Data == "") {
            Toast.makeText(this, "data está vazio", Toast.LENGTH_SHORT).show();
        } else {
            String sexo = sexoget;
            if (sexo == "SEXO") {
                Toast.makeText(this, "sexo está vazio", Toast.LENGTH_SHORT).show();
            } else {
                usuario.setSexo(sexo);
                usuario.setIdade(Integer.parseInt(Data));
                it = new Intent(this, Cadastro3.class);
                this.startActivity(it);
            }
        }
    }
}