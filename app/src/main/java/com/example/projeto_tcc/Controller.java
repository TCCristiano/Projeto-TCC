package com.example.projeto_tcc;

import android.content.Context;
import android.content.Intent;

public class Controller
{
    private static UsuarioDAO usuarioDAO;
    private static Controller instance;
    private static Usuario usuario;

    public static Controller getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new Controller();
            usuarioDAO = new UsuarioDAO(context);
        }
        return instance;
    }

    public long insert(Usuario usuario)
    {
        return usuarioDAO.inserirCliente(usuario);
    }

    public boolean deleteUsuario(Integer id)
    {
       usuario = usuarioDAO.DeleteUsuario(id);

       if(usuario == null)
       {
           return false;
       }
       else
       {
           return true;
       }
    }

    public boolean alterarUsuario(Usuario usuario)
    {
        return usuarioDAO.UpdateUsuario(usuario);
    }

    public Usuario findbyid(Integer i){ usuario = usuarioDAO.FindByID(i); return usuario;}

    public Usuario validaLogin(String email, String senha) throws Exception
    {
         usuario = usuarioDAO.validarLogin(email, senha);

        if (usuario == null || usuario.getEmail_cliente() == null || usuario.getSenha_cliente() == null) {  }
        String informado = email + senha;
        String esperado = usuario.getEmail_cliente() + usuario.getSenha_cliente();

        if (informado.equals(esperado)) {return usuario;}
        return usuario;
    }
}
