package com.example.projeto_tcc;

import java.io.Serializable;

public class Usuario implements Serializable
{
    private int cod_cliente;
    private String nome_cliente;
    private String email_cliente;
    private String userName_cliente;
    private String senha_cliente;
    private int telefone_cliente;
    private String cpf_cliente;
    private int idade_cliente;
    private int cod_endereco;
    private String estado;
    private String cidade;
    private String rua;
    private String numResidencia;
    private String cep;
    private String sexo;
    private byte[] foto;

    public Usuario() { }


    public int getCod_cliente() { return cod_cliente; }

    public void setCod_cliente(int cod_cliente) { this.cod_cliente = cod_cliente; }

    public String getNome_cliente() { return nome_cliente; }

    public void setNome_cliente(String nome_cliente) { this.nome_cliente = nome_cliente; }

    public String getEmail_cliente() { return email_cliente; }

    public void setEmail_cliente(String email_cliente) { this.email_cliente = email_cliente; }

    public String getUserName_cliente() { return userName_cliente; }

    public void setUserName_cliente(String userName_cliente) { this.userName_cliente = userName_cliente; }

    public String getSenha_cliente() { return senha_cliente; }

    public void setSenha_cliente(String senha_cliente) { this.senha_cliente = senha_cliente; }

    public int getTelefone_cliente() { return telefone_cliente; }

    public void setTelefone_cliente(int telefone_cliente) { this.telefone_cliente = telefone_cliente; }

    public String getCpf_cliente() { return cpf_cliente; }

    public void setCpf_cliente(String cpf_cliente) { this.cpf_cliente = cpf_cliente; }

    public int getIdade_cliente() { return idade_cliente; }

    public void setIdade_cliente(int idade_cliente) { this.idade_cliente = idade_cliente; }

    public int getCod_endereco() { return cod_endereco; }

    public void setCod_endereco(int cod_endereco) { this.cod_endereco = cod_endereco; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getRua() { return rua; }

    public void setRua(String rua) { this.rua = rua; }

    public String getNumResidencia() { return numResidencia; }

    public void setNumResidencia(String numResidencia) { this.numResidencia = numResidencia; }

    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep; }

    public String getSexo() { return sexo; }

    public void setSexo(String sexo) { this.sexo = sexo; }

    public byte[] getFoto() { return foto; }

    public void setFoto(byte[] foto) { this.foto = foto; }
}
