package com.example.projetotcc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.CadastroServico.CadastroServico;
import com.example.projetotcc.CadastroUsuario.Cadastro5;
import com.example.projetotcc.ui.categorias.CategoriasFragment;
import com.example.projetotcc.ui.home.HomeFragment;
import com.example.projetotcc.ui.listaFragment.ListaCategoriasFragment;
import com.google.android.material.navigation.NavigationView;
import com.xwray.groupie.GroupAdapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
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
    public static Usuario usuario;
    public static Servico servico, servicop;
    public  static int view;
    public  static boolean viewcategoria = false;
    public boolean continuar = false;
    private Controller controller;
    public static GroupAdapter adapter;
    private int e;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        servicop = new Servico();
        adapter = new GroupAdapter();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        this.login = new MainActivity();
        if (MainActivity.usuario != null) {
            this.usuario = MainActivity.usuario;

        } else {
            this.usuario = Cadastro5.usuario;
        }
        controller = new Controller();
        String cod = String.valueOf(usuario.getCod());

        controller.findServicoById(new Controller.VolleyCallbackProduto()
        {
            @Override
            public void onSuccess(String response, Servico p) {
                servicop = p;
                Log.i("Aqui", "Meu Serviço: "+ servicop.getDescricao());
            }
        }, cod);

        setContentView(R.layout.activity_pagina_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        TextView email = (TextView) headerView.findViewById(R.id.ViewEmail);
        TextView nome = (TextView) headerView.findViewById(R.id.ViewNome);
        email.setText(this.usuario.getEmail());
        nome.setText(this.usuario.getNome());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_categoria, R.id.nav_favoritos, R.id.nav_minhaLoja, R.id.nav_pedidos, R.id.nav_perfil, R.id.nav_lista, R.id.nav_sair)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    int i = 1;
    public void ListarByCategorias(int e,String tipo) {
        do{
            controller.Listar(new Controller.VolleyCallbackProduto() {
                @Override
                public void onSuccess(String result, Servico p) {
                    servico = p;
                    adapter.add(new ListaCategoriasFragment.ServicoItem(servico));
                    adapter.notifyDataSetChanged();
                    i = servico.getID();
                }
            }, String.valueOf(i), tipo);
           i++;
        }while(i <= e);
    }

    public void findbyCategoriaModa(View v) {
        servico = new Servico();

        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new ListaCategoriasFragment()).commit();
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
        it = new Intent(this, CadastroServico.class);
        this.startActivity(it);
    }


}
