package com.example.projeto_tcc.ui.EditUsuario;

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

import com.example.projeto_tcc.Controller;
import com.example.projeto_tcc.PagLogin;
import com.example.projeto_tcc.R;
import com.example.projeto_tcc.Usuario;
import com.example.projeto_tcc.UsuarioDAO;
import com.example.projeto_tcc.ui.Pedidos.PedidosViewModel;

public class EditUsuarioFragment extends Fragment {
    private EditUsuarioViewModel editUsuarioViewModel;
    private PedidosViewModel pedidosViewModel;
    private UsuarioDAO usuarioDAO;
    private PagLogin login;
    private Usuario usuario;
    private String ViewEmail, ViewNome;
    private Context context;
    private Controller controller;
    public Button btn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        editUsuarioViewModel = ViewModelProviders.of(this).get(EditUsuarioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        final TextView textView = root.findViewById(R.id.text_gallery);
        controller = Controller.getInstance(context);
        usuario = new Usuario();
        login = new PagLogin();
        Integer i = login.getId();
        usuario = controller.findbyid(i);

         btn = (Button) root.findViewById(R.id.btn_deletar);
        TextView nome = root.findViewById(R.id.NomeUpdate);
        TextView email = root.findViewById(R.id.EmailUpdate);
        TextView username = root.findViewById(R.id.NUsuarioUpdate);
        TextView senha = root.findViewById(R.id.SenhaUpdate);
        TextView telefone = root.findViewById(R.id.TelefoneUpdate);
        TextView cpf = root.findViewById(R.id.CpfUpdate);
        TextView idade = root.findViewById(R.id.IdadeUpdate);

        nome.setText(String.valueOf(usuario.getNome_cliente()));
        email.setText(String.valueOf(usuario.getEmail_cliente()));
        username.setText(String.valueOf(usuario.getUserName_cliente()));
        senha.setText(String.valueOf(usuario.getSenha_cliente()));
        telefone.setText(String.valueOf(usuario.getTelefone_cliente()));
        cpf.setText(String.valueOf(usuario.getCpf_cliente()));
        idade.setText(String.valueOf(usuario.getIdade_cliente()));

        editUsuarioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}