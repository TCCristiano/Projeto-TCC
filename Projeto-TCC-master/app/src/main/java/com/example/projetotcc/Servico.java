package com.example.projetotcc;

public class Servico {
   private int ID;
   private int IDUser;
   private String descricao;
   private String nome;
   private String preco;
   private String Tipo;
   private boolean continuar;

   public Servico() {
   }

   public Servico(String Nome, String Descricao, String Preco, int id) {
      this.nome = Nome;
      this.descricao = Descricao;
      this.ID = id;
      this.preco = Preco;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public String getNome() {
      return this.nome;
   }

   public String getPreco() {
      return this.preco;
   }

   public void setDescricao(String var1) {
      this.descricao = var1;
   }

   public void setNome(String var1) {
      this.nome = var1;
   }

   public void setPreco(String var1) {
      this.preco = var1;
   }

   public int getID() {
      return ID;
   }

   public void setID(int ID) {
      this.ID = ID;
   }

   public String getTipo() {
      return Tipo;
   }

   public void setTipo(String tipo) {
      Tipo = tipo;
   }

   public int getIDUser() {
      return IDUser;
   }

   public void setIDUser(int IDUser) {
      this.IDUser = IDUser;
   }

   public boolean isContinuar() {
      return continuar;
   }

   public void setContinuar(boolean continuar) {
      this.continuar = continuar;
   }
}
