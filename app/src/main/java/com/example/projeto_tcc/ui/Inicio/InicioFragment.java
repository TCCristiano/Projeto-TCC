package com.example.projeto_tcc.ui.Inicio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto_tcc.Controller;
import com.example.projeto_tcc.Produto;
import com.example.projeto_tcc.R;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import static com.example.projeto_tcc.PagInicial.context;
import static com.example.projeto_tcc.PagInicial.rv;

public class InicioFragment extends Fragment {
    private MainViewModel inicioFragment;
    private GroupAdapter adapter;
    private Controller controller;
    private Produto produto;
    static int e, f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inicioFragment = ViewModelProviders.of(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.inicio_fragment, container, false);
        rv = (RecyclerView) root.findViewById(R.id.recycler);
        controller = new Controller();
        adapter = new GroupAdapter();
        produto = new Produto();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(context));
        e = controller.UltimoProduto();
        for (f = 1; f <= e; ++f) {
           produto = controller.ListarProdutos(f);
           adapter.add(new ProdutoItem(produto));
        }

        return root;
    }

    public class ProdutoItem extends Item<ViewHolder>
    {
        Produto produto;
        private ProdutoItem(Produto produto) {
            this.produto = produto;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {

            TextView nome =  viewHolder.itemView.findViewById(R.id.txtNomeProduto);
            TextView descricao =  viewHolder.itemView.findViewById(R.id.txtDescricaoProduto);

            nome.setText(produto.getNome());
            descricao.setText(produto.getDescricao());
        }

        @Override
        public int getLayout() {
            return R.layout.item_produto;
        }
    }



}
