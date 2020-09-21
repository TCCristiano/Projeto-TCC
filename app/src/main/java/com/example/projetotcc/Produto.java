package com.example.projetotcc;

public class Produto {
   private int ID;
   private String descricao;
   private String nome;
   private String preco;

   public Produto() {
   }

   public Produto(String Nome, String Descricao,  String Preco, int id) {
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
}
