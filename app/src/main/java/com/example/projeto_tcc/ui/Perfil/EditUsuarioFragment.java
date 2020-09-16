package com.example.projeto_tcc.ui.Perfil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projeto_tcc.PagLogin;
import com.example.projeto_tcc.R;
import com.example.projeto_tcc.ui.Pedidos.PedidosViewModel;

public class EditUsuarioFragment extends Fragment {
    private EditUsuarioViewModel editUsuarioViewModel;
    private PedidosViewModel pedidosViewModel;
    private PagLogin login;
    private String ViewEmail, ViewNome;
    private static Context context;
    public Button btn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        editUsuarioViewModel = ViewModelProviders.of(this).get(EditUsuarioViewModel.class);
        View root = inflater.inflate(R.layout.perfil_fragment, container, false);

        final TextView textView = root.findViewById(R.id.text_gallery);
        login = new PagLogin();
         btn = (Button) root.findViewById(R.id.btn_deletar);
        TextView nome = root.findViewById(R.id.NomeUpdate);
        TextView email = root.findViewById(R.id.EmailUpdate);
        TextView username = root.findViewById(R.id.NUsuarioUpdate);
        TextView senha = root.findViewById(R.id.SenhaUpdate);
        TextView telefone = root.findViewById(R.id.TelefoneUpdate);
        TextView cpf = root.findViewById(R.id.CpfUpdate);
        TextView idade = root.findViewById(R.id.IdadeUpdate);


        editUsuarioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}