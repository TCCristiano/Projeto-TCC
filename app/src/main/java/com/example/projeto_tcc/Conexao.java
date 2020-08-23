package com.example.projeto_tcc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Conexao extends SQLiteOpenHelper
{
    private static final String name = "BD";
    private static final int version = 1;
    protected SQLiteDatabase database;

    public Conexao(@Nullable Context context)
    {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table if not exists tbl_prestador(cod_prestador integer primary key autoincrement, especializacao varchar(100), avaliacao varchar(2))");
        db.execSQL("create table if not exists tbl_endereco(cod_endereco integer primary key autoincrement, estado VARCHAR(100) NOT NULL, cidade VARCHAR(100) NOT NULL, rua VARCHAR(100) NOT NULL,numResidencia VARCHAR NOT NULL, cep VARCHAR(8) NOT NULL)");
        db.execSQL("create table if not exists tbl_usuario( cod_usuario integer primary key autoincrement not null,foto_usuario BLOB , nome_usuario varchar(100) not null, email_usuario  varchar(100)  not null, userName_usuario varchar(100)  not null, senha_usuario varchar(100) not null, telefone_usuario int  not null, cpf_usuario varchar(11)  not null, idade_usuario int, cod_endereco int, cod_prestador int, FOREIGN KEY (cod_endereco) REFERENCES tbl_endereco (cod_endereco), FOREIGN KEY (cod_prestador) REFERENCES tbl_prestador (cod_prestador))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

}

