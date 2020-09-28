package com.example.projetotcc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.CadastroProduto.CadastroProduto;
import com.example.projetotcc.CadastroUsuario.Cadastro5;
import com.example.projetotcc.ui.ListaProdutos;
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
import androidx.recyclerview.widget.RecyclerView;

public class PaginaUsuario extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static RequestQueue requestQueue;
    public static RecyclerView rv;
    public static Context context;
    private MainActivity login;
    private Usuario usuario;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pagina_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.login = new MainActivity();
        this.login = new MainActivity();
        if(MainActivity.usuario != null) {
            this.usuario = MainActivity.usuario;

        }
        else
        {
            this.usuario = Cadastro5.usuario;
        }
        context = this;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        TextView email = (TextView)headerView.findViewById(R.id.ViewEmail);
        TextView nome = (TextView)headerView.findViewById(R.id.ViewNome);
        email.setText(this.usuario.getEmail());
        nome.setText(this.usuario.getNome());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_categoria, R.id.nav_favoritos, R.id.nav_minhaLoja, R.id.nav_pedidos, R.id.nav_perfil, R.id.nav_sair)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pagina_usuario, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Sair(MenuItem menuItem) {
        it = new Intent(this, MainActivity.class);
        this.startActivity(it);
    }

    public void CadastroProduto(View view) {
        it = new Intent(this, CadastroProduto.class);
        this.startActivity(it);
    }
    public void ListaProdutos(View view) {
        it = new Intent(this, ListaProdutos.class);
        this.startActivity(it);
    }
}