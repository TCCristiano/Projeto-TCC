package com.example.projetotcc.controllers;

import android.database.sqlite.SQLiteDatabase;

import com.example.projetotcc.ChatUsuario;
import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.models.CallBacks;
import com.example.projetotcc.models.MensagemModel;
import com.example.projetotcc.ui.pedidos.PedidosFragment;

import database.DadosOpenHelperMessage;
import dominio.entidade.Message;
import dominio.repositorio.ManterLogadoRepositorio;

public class MensagemPaginaUsuario  extends PaginaUsuario {

    private DadosOpenHelperMessage dadosOpenHelper;
    private SQLiteDatabase conexao;
    private ManterLogadoRepositorio manterLogadoRepositorio;
    private MensagemModel mensagemModel;

    public void SelecionarMensagem()
    {
        mensagemModel = new MensagemModel(requestQueue);
        criarConexaoInterna();
        mensagemModel.SelecionarMensagem(new CallBacks.VolleyCallbackMensagem() {
            @Override
            public void onSuccess(String result, Message message) {
                if(message.getText()!= "erro")
                {
                    manterLogadoRepositorio.inserirMensagem(message);
                    adapter.add(new PedidosFragment.MessageItem(message, usuario));

                }
                SelecionarMensagem();
            }
        }, String.valueOf(usuario.getCod()));
    }
    private void criarConexaoInterna() {
        try {
            dadosOpenHelper = new DadosOpenHelperMessage(ChatUsuario.context);
            conexao = dadosOpenHelper.getWritableDatabase();
            manterLogadoRepositorio = new ManterLogadoRepositorio(conexao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
