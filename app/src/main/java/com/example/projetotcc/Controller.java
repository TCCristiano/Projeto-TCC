package com.example.projetotcc;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.projetotcc.Constants;
import com.example.projetotcc.CadastroProduto.CadastroProduto;
import com.example.projetotcc.CadastroUsuario.Cadastro5;
import com.example.projetotcc.ui.ListaProdutos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Controller extends AppCompatActivity {
   private static RequestQueue requestQueue;
   public static int result;
   JSONObject jsonObject;

   public void CadastrarUser(final Usuario usuario) {
      requestQueue = Cadastro5.requestQueue;
      StringRequest stringRequest = new StringRequest(Constants.insertUrlUsuario, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
            System.out.println(response);
         }
      }, new ErrorListener() {
         public void onErrorResponse(VolleyError error) {
            System.out.println(error);
         }
      }) {
         protected Map getParams() throws AuthFailureError {
            HashMap hashMap = new HashMap();
            hashMap.put("nome", usuario.getNome());
            hashMap.put("email", usuario.getEmail());
            hashMap.put("username", usuario.getUsername());
            hashMap.put("cpf", usuario.getCpf());
            hashMap.put("senha", usuario.getSenha());
            hashMap.put("tel", String.valueOf(usuario.getTel()));
            hashMap.put("idade", String.valueOf(usuario.getIdade()));
            return hashMap;
         }
      };
      requestQueue.add(stringRequest);
   }
   public Produto ListarProdutos(final int id) {
      final String cod = String.valueOf(id);
      requestQueue = ListaProdutos.requestQueue;
      final Produto produto = new Produto();
      StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.Urlprodutos,
              new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
            System.out.println(response);
            try {
               JSONObject jsonObject = new JSONObject(response);
               produto.setNome(jsonObject.getString("nome_produto"));
               produto.setDescricao(jsonObject.getString("descricao_produto"));
               produto.setPreco(jsonObject.getString("preco_produto"));
               produto.setID(jsonObject.getInt("cod_produto"));
               Log.v("LogLogin", produto.getNome());
            } catch (JSONException e) {
               e.printStackTrace();
            }
         }
      }, new ErrorListener() {
         public void onErrorResponse(VolleyError error) {
            System.out.println(error);
         }
      }) {
         @Override
         protected Map<String, String> getParams()
         {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("cod", cod);

            return params;
         }
      };
      requestQueue.add(postRequest);
      return produto;
   }
   public int UltimoProduto() {
      requestQueue = PaginaUsuario.requestQueue;
      StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.produtos, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
            System.out.println(response);
            Controller.result = Integer.parseInt(response);
         }
      }, new ErrorListener() {
         public void onErrorResponse(VolleyError error) {
            System.out.println(error);
         }
      });
      requestQueue.add(stringRequest);
      return result;
   }

   public void CadastrarP(final String nome, final String preco, final String descricao) {
      requestQueue = CadastroProduto.requestQueue;
      StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.insertUrlProduto, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
            System.out.println(response);
         }
      }, new ErrorListener() {
         public void onErrorResponse(VolleyError error) {
            System.out.println(error);
         }
      }) {
         protected Map getParams() throws AuthFailureError {
            HashMap hashMap = new HashMap();
            hashMap.put("nome", nome);
            hashMap.put("preco", preco);
            hashMap.put("descricao", descricao);
            return hashMap;
         }
      };
      requestQueue.add(stringRequest);
   }
   public Usuario Login(final String email, final String senha, final Usuario usuario) {
      requestQueue = MainActivity.requestQueue;
      StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.login,
              new Response.Listener<String>()
              {
                 @Override
                 public void onResponse(String response) {
                    Log.d("Response", response);
                    try {

                       MainActivity.jsonObject = new JSONObject(response);
                       usuario.setEmail(MainActivity.jsonObject.getString("email_usuario"));
                       usuario.setNome(MainActivity.jsonObject.getString("nome_usuario"));
                       Log.v("LogLogin", usuario.getEmail());

                    } catch (Exception error) {
                       Log.v("LogLogin", String.valueOf(error));
                    }
                 }
              },
              new Response.ErrorListener()
              {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                    // error
                    Log.d("Error.Response", error.toString());
                 }
              }
      ) {
         @Override
         protected Map<String, String> getParams()
         {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("email", email);
            params.put("senha", senha);

            return params;
         }
      };
      requestQueue.add(postRequest);
      return usuario;
   }

}
