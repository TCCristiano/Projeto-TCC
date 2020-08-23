package com.example.projeto_tcc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class UsuarioDAO
{
    private final String TABLE = "tbl_usuario";
    private SQLiteDatabase banco;
    private SQLiteDatabase bancoR;
    public Usuario usuario;
    private Conexao conexao;
    private Context context;



    public UsuarioDAO(Context context )
    {
        super();
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
        bancoR = conexao.getReadableDatabase();

    }
    public long inserirCliente(Usuario usuario)
    {
        ContentValues values = new ContentValues();

        values.put("nome_usuario", usuario.getNome_cliente());
        values.put("email_usuario", usuario.getEmail_cliente());
        values.put("userName_usuario", usuario.getUserName_cliente());
        values.put("senha_usuario", usuario.getSenha_cliente());
        values.put("telefone_usuario", usuario.getTelefone_cliente());
        values.put("cpf_usuario", usuario.getCpf_cliente());
        values.put("idade_usuario", usuario.getIdade_cliente());

        return banco.insert("tbl_usuario", null, values);
    }

    public long inserirEndereco(Usuario usuario)
    {
        ContentValues values = new ContentValues();

        values.put("estado", usuario.getEstado());
        values.put("cidade", usuario.getCidade());
        values.put("rua", usuario.getRua());
        values.put("numResidencia", usuario.getNumResidencia());
        values.put("cep", usuario.getCep());

         return banco.insert("tbl_endereco", null, values);
    }

    public long inserirPrestador(Prestador prestador)
    {
        ContentValues values = new ContentValues();

        values.put("estado", prestador.getCod_prestador());
        values.put("cidade", prestador.getEspecializacao());
        values.put("rua", prestador.getAvaliacao());

        return banco.insert("tbl_prestador", null, values);
    }

    public Usuario montaUsuario(Cursor cursor)
    {
        if (cursor.getCount() == 0) { return null; }
        usuario.setCod_cliente(cursor.getInt(cursor.getColumnIndex("cod_usuario")));
        usuario.setNome_cliente(cursor.getString(cursor.getColumnIndex("nome_usuario")));
        usuario.setEmail_cliente(cursor.getString(cursor.getColumnIndex("email_usuario")));
        usuario.setUserName_cliente(cursor.getString(cursor.getColumnIndex("userName_usuario")));
        usuario.setSenha_cliente(cursor.getString(cursor.getColumnIndex("senha_usuario")));
        usuario.setTelefone_cliente(cursor.getInt(cursor.getColumnIndex("telefone_usuario")));
        usuario.setCpf_cliente(cursor.getString(cursor.getColumnIndex("cpf_usuario")));
        usuario.setIdade_cliente(cursor.getInt(cursor.getColumnIndex("idade_usuario")));

        return  usuario;
    }

    public Usuario DeleteUsuario(Integer id)
    {
        banco.delete(TABLE, "cod_usuario = ?", new String[]{ String.valueOf(id) });

        return usuario = FindByID(id);
    }

    public boolean UpdateUsuario(Usuario usuario)
    {

        ContentValues values = new ContentValues();

        values.put("nome_usuario", usuario.getNome_cliente());
        values.put("email_usuario", usuario.getEmail_cliente());
        values.put("userName_usuario", usuario.getUserName_cliente());
        values.put("senha_usuario", usuario.getSenha_cliente());
        values.put("telefone_usuario", usuario.getTelefone_cliente());
        values.put("cpf_usuario", usuario.getCpf_cliente());
        values.put("idade_usuario", usuario.getIdade_cliente());

        banco.update("tbl_usuario", values, "cod_usuario = ?", new String[]{String.valueOf(usuario.getCod_cliente())});

        return true;
    }

    public Usuario FindByID(Integer id){
        String sql = "SELECT * FROM " + TABLE + " WHERE cod_usuario = ?";
        String[] selectionArgs = new String[] { "" + id};
        Cursor cursor = bancoR.rawQuery(sql, selectionArgs);
        Usuario usuario = new Usuario();
        while (cursor.moveToNext())
        {
            if (cursor.getCount() == 0) {
                return null;
            }
            usuario.setCod_cliente(cursor.getInt(0));
            usuario.setNome_cliente(cursor.getString(1));
            usuario.setEmail_cliente(cursor.getString(2));
            usuario.setUserName_cliente(cursor.getString(3));
            usuario.setSenha_cliente(cursor.getString(4));
            usuario.setTelefone_cliente(cursor.getInt(5));
            usuario.setCpf_cliente(cursor.getString(6));
            usuario.setIdade_cliente(cursor.getInt(7));
        }
        return usuario;
    }
    public Usuario validarLogin(String email, String senha){

    String sql = "SELECT * FROM " + TABLE + " WHERE (email_usuario = ? OR userName_usuario = ?) AND senha_usuario = ?";
    String[] selectionArgs = new String[] { email, email, senha };
    Cursor cursor = bancoR.rawQuery(sql, selectionArgs);
    Usuario usuario = new Usuario();
    while (cursor.moveToNext())
    {

        if (cursor.getCount() == 0) {
            return null;
        }
        usuario.setCod_cliente(cursor.getInt(0));
        usuario.setNome_cliente(cursor.getString(1));
        usuario.setEmail_cliente(cursor.getString(2));
        usuario.setUserName_cliente(cursor.getString(3));
        usuario.setSenha_cliente(cursor.getString(4));
        usuario.setTelefone_cliente(cursor.getInt(5));
        usuario.setCpf_cliente(cursor.getString(6));
        usuario.setIdade_cliente(cursor.getInt(7));
    }
    return usuario;
}

}
