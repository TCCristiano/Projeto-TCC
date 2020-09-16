package com.example.projeto_tcc;

public class Produto {
    private String nome;
    private String descricao;
    private String preco;


    public Produto(){}
    public Produto(String n, String d){
        nome = n;
        descricao = d;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

}

