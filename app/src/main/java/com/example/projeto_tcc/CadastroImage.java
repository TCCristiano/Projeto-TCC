package com.example.projeto_tcc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.jar.JarFile;

public class CadastroImage extends AppCompatActivity {
    private static int id;
    private int j;
    private ImageView i;
    private Controller Controller;
    private CadastroUsuario cadastro1;
    private Cadastro2 cadastro2;
    private Cadastro3 cadastro3;
    private Cadastro4 cadastro4;
    private static Usuario usuario;
    private Context context;
    Intent img, it = null;
    Image k;
    private Uri nuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_image);
        context = this;
        Controller = Controller.getInstance(context);
        usuario = new Usuario();
        Controller = new Controller();
        cadastro1 = new CadastroUsuario();
        cadastro2 = new Cadastro2();
        cadastro3 = new Cadastro3();
        cadastro4 = new Cadastro4();

        i = findViewById(R.id.Image);
    }

    public void get(View v) {
        img = new Intent(Intent.ACTION_PICK);
        img.setType("image/*");
        startActivityForResult(img, 0);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 0)
        {
            nuri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), nuri);
                i.setImageDrawable( new BitmapDrawable(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Cadastrar(View v) {
        usuario.setNome_cliente(cadastro1.getNomeget());
        usuario.setEmail_cliente(cadastro3.getEmailget());
        usuario.setUserName_cliente(cadastro3.getUsernameget());
        usuario.setSenha_cliente(cadastro4.getSenha());
        usuario.setTelefone_cliente(cadastro3.getTelget());
        usuario.setCpf_cliente(cadastro1.getCpfget());
        usuario.setIdade_cliente(cadastro2.getDataget());

        long id = Controller.insert(usuario);

        Toast.makeText(this,"Cliente inserido com sucesso ID: " + id,Toast.LENGTH_SHORT).show();
        this.it = new Intent(CadastroImage.this,PagLogin.class);
        startActivity(this.it);
    }

    public void voltar(View v) {
        this.it = new Intent(CadastroImage.this,Cadastro4.class);
        startActivity(this.it);
    }
}



