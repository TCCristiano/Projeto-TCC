package com.example.projetotcc.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import java.util.List;

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

        String[] StringSexo = new String[]{
                "SEXO",
                "Masculino",
                "Feminino"
        };

        Spinner spinner = (Spinner)findViewById(R.id.sexoUserCadastro);
        final List<String> sexoSelect = new ArrayList<>(Arrays.asList(StringSexo));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.activity_cadastro_2,sexoSelect){
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
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, sexoSelect));

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