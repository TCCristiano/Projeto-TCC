package com.example.projeto_tcc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PagInicial extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private PagLogin login;
    private Context context;
    private Controller Controller;
    private Usuario usuario;

    private static int id;
    Intent it = null;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PagInicial.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_inicial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        context = this;
        Controller = Controller.getInstance(context);
        usuario = new Usuario();
        login = new PagLogin();
        Integer i = login.getId();
        usuario = Controller.findbyid(i);
        String textNome = usuario.getNome_cliente();
        String textEmail = usuario.getEmail_cliente();

        NavigationView navigationView;
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        TextView Email = (TextView) headerView.findViewById(R.id.ViewEmail);
        TextView Nome = (TextView) headerView.findViewById(R.id.ViewNome);
        Email.setText(textEmail);
        Nome.setText(textNome);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_pedido, R.id.nav_perfil, R.id.nav_loja)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pag_inicial, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Deletar(View v)
    {
        Boolean t =Controller.deleteUsuario(usuario.getCod_cliente());
        if( t == true)
        {
            Toast.makeText(this, "Usuario deletado com sucesso!", Toast.LENGTH_SHORT).show();
            this.it = new Intent(PagInicial.this, PagLogin.class);
            startActivity(this.it);
        }
        else
        {
            Toast.makeText(this, "Falha ao tentar deletar o perfil!", Toast.LENGTH_SHORT).show();
        }
    }
    public void Editar(View v)
    {
        this.id = usuario.getCod_cliente();
        this.it = new Intent(PagInicial.this, PagEditarUser.class);
        startActivity(this.it);
    }
}