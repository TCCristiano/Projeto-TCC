package com.example.projeto_tcc.ui.Pedidos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projeto_tcc.PagLogin;
import com.example.projeto_tcc.R;
import com.example.projeto_tcc.Usuario;
import com.example.projeto_tcc.UsuarioDAO;

public class PedidosFragment extends Fragment {

    private PedidosViewModel pedidosViewModel;
    private UsuarioDAO usuarioDAO;
    private PagLogin id;
    private Usuario usuario;
    private TextView ViewEmail, ViewNome;
    private Context context;
    private com.example.projeto_tcc.Controller Controller;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel =
                ViewModelProviders.of(this).get(PedidosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pedidos, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        pedidosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}