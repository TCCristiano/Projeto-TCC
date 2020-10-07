package com.example.projetotcc.ui.listaFragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetotcc.Controller;
import com.example.projetotcc.InfoServico;
import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.R;
import com.example.projetotcc.Servico;
import com.example.projetotcc.ui.home.HomeFragment;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;

public class ListaCategoriasFragment extends Fragment {

    private MainViewModel mViewModel;
    public static RecyclerView rv;
    public static Servico servico;
    private Controller controller;
    public static GroupAdapter adapter;

    public static ListaCategoriasFragment newInstance() {
        return new ListaCategoriasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        controller = new Controller();
        adapter = new GroupAdapter();
        controller.idUltimo(new Controller.VolleyCallback()
        {
            @Override
            public void onSuccess(String response) {
                ListarByCategorias(1, "moda");
            }
        });
        View view;
        view = inflater.inflate(R.layout.fragment_lista, container, false);
        rv = view.findViewById(R.id.recyclerListaPrestador);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(PaginaUsuario.context));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(PaginaUsuario.context, InfoServico.class);

                ServicoItem servicoItem = (ServicoItem) item;
                servico = new Servico();
                servico = servicoItem.servico;

                startActivity(intent);
            }
        });
    }

    public static class ServicoItem extends Item<ViewHolder> {
        private final Servico servico;

        public ServicoItem(Servico servico1) {
            this.servico = servico1;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView var3 = (TextView) viewHolder.itemView.findViewById(R.id.descricaoServico);
            TextView var4 = (TextView) viewHolder.itemView.findViewById(R.id.precoServico);

            var3.setText(servico.getNome());
            var4.setText(servico.getPreco() + " " + servico.getID());
        }

        @Override
        public int getLayout() {
            return R.layout.item_servico;
        }
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
}