package com.example.projetotcc.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetotcc.R;
import com.example.projetotcc.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Cadastro2 extends AppCompatActivity {
    private static String data;
    private static String sexo;
    public static Usuario usuario;
    private String ano, dia, mes;
    private Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_2);

        usuario = Cadastro1.usuario;

        String[] StringSexo = new String[]{
                "SEXO",
                "Masculino",
                "Feminino"
        };
        String[] StringDia = new String[]{
                "Dia",
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31"
        };
        String[] StringMes = new String[]{
                "Mês",
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12"
        };
        String[] StringAno = new String[2020];
        StringAno[0] = "Ano";
        int n = 1;
        for (int i = 2020; i > 1; --i) {
            StringAno[n] = String.valueOf(i);
            ++n;
        }
        Spinner spinner = (Spinner) findViewById(R.id.sexoUserCadastro);
        final List<String> sexoSelect = new ArrayList<>(Arrays.asList(StringSexo));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.activity_cadastro_2, sexoSelect) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(0xFF858585);
                } else {
                    tv.setTextColor(0xFF000000);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, sexoSelect));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sexo = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    Toast.makeText(getApplicationContext(), "Sexo : " + sexo, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
        final List<String> diaSelect = new ArrayList<>(Arrays.asList(StringDia));
        final ArrayAdapter<String> spinnerArrayAdapterDia = new ArrayAdapter<String>(this, R.layout.activity_cadastro_2, diaSelect) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(0xFF858585);
                } else {
                    tv.setTextColor(0xFF000000);
                }
                return view;
            }
        };
        spinnerArrayAdapterDia.setDropDownViewResource(R.layout.spinner_item);
        spinnerDia.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, diaSelect));

        spinnerDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mes = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
        final List<String> MesSelect = new ArrayList<>(Arrays.asList(StringMes));
        final ArrayAdapter<String> spinnerArrayAdapterMes = new ArrayAdapter<String>(this, R.layout.activity_cadastro_2, MesSelect) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapterMes.setDropDownViewResource(R.layout.spinner_item);
        spinnerMes.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, MesSelect));

        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mes = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Spinner spinnerAno = (Spinner) findViewById(R.id.spinnerAno);
        final List<String> Anoselecte = new ArrayList<>(Arrays.asList(StringMes));
        final ArrayAdapter<String> spinnerArrayAdapterAno = new ArrayAdapter<String>(this, R.layout.activity_cadastro_2, Anoselecte) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapterAno.setDropDownViewResource(R.layout.spinner_item);
        spinnerAno.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, Anoselecte));

        spinnerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ano = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void Cadastrar(View view) {

        if (ano == "Ano" && mes == "Mês" && dia == "Dia") {
            Toast.makeText(this, "data está vazio", Toast.LENGTH_SHORT).show();
        } else {
            if (sexo == "SEXO") {
                Toast.makeText(this, "sexo está vazio", Toast.LENGTH_SHORT).show();
            } else {
                usuario.setSexo(sexo);
                data = dia + mes + ano;
                usuario.setIdade(Integer.parseInt(data));
                it = new Intent(this, Cadastro3.class);
                this.startActivity(it);
            }
        }
    }
}