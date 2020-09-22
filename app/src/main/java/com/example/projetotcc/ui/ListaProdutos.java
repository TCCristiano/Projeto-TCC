package com.example.projetotcc.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.projetotcc.Controller;
import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.Produto;
import com.example.projetotcc.R;
import com.example.projetotcc.Usuario;
import com.example.projetotcc.ui.home.HomeFragment;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import org.json.JSONObject;

import java.util.List;

public class ListaProdutos extends AppCompatActivity {
    static int e;
    private GroupAdapter adapter;
    private Controller controller;
    Produto produto;

    RecyclerView rv;
    public static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        RecyclerView rv = findViewById(R.id.recycler);

        produto = new Produto();
        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        controller = new Controller();
        e = controller.UltimoProduto();
        fetchUsers(e);
    }

    private void fetchUsers(int e)
    {
        for(int i = 1; i <= e;) {
            produto = controller.ListarProdutos(i);
            adapter.add(new ProdutoItem(produto));
            adapter.notifyDataSetChanged();
            i++;
        }
    }

        public class ProdutoItem extends Item<ViewHolder> {
        private final Produto produto;

        ProdutoItem(Produto produtoi) {
            this.produto = produtoi;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView var3 = (TextView) viewHolder.itemView.findViewById(R.id.nomeProduto);
            TextView var4 = (TextView) viewHolder.itemView.findViewById(R.id.precoProduto);

            var3.setText(produto.getNome());
            var4.setText(produto.getPreco() + " " +produto.getID());
        }

        @Override
        public int getLayout() {
            return R.layout.item_produto;
        }
    }
}