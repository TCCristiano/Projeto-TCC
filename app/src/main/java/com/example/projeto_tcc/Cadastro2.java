package com.example.projeto_tcc;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cadastro2 extends AppCompatActivity {
    Usuario usuario;
    private EditText data1, sexo;
    private static int dataget;
    private static String sexoget, Data;
    Intent it = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);
        data1 = findViewById(R.id.data);
        Spinner spinner = (Spinner) findViewById(R.id.sexo);
        String[] sexoS = new String[]{
                "SEXO",
                "Masculino",
                "Feminino"
        };

        final List<String> SexoList = new ArrayList<>(Arrays.asList(sexoS));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,SexoList){
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
                View view = super.getDropDownView(position, convertView, parent);
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
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sexoget = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selecionado : " + sexoget, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void Cadastrar(View v)
    {
        Data = data1.getText().toString() + "";
        if (Data == "") {
            Toast.makeText(this,"data está vazio",Toast.LENGTH_SHORT).show();
        }
        else if (sexoget == "SEXO")
        {
        Toast.makeText(this,"sexo está vazio",Toast.LENGTH_SHORT).show();
        }
        else
       {
            dataget = Integer.parseInt(Data);
            this.it = new Intent(Cadastro2.this,Cadastro3.class);
            startActivity(this.it);
        }

    }

    public int getDataget() {
        return dataget;
    }

    public void setDataget(int dataget) {
        this.dataget = dataget;
    }

    public String getSexoget() {
        return sexoget;
    }

    public void setSexoget(String sexoget) {
        this.sexoget = sexoget;
    }
}