package com.example.projetotcc.ui.pedidos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.projetotcc.PaginaUsuario;
import com.example.projetotcc.R;
import com.example.projetotcc.adapterView.AdapterView;
import com.example.projetotcc.adapterView.AdapterViewPedidos;
import com.example.projetotcc.ui.categorias.CategoriasFragment;
import com.example.projetotcc.ui.chatUsuario.ChatUsuarioFragment;
import com.example.projetotcc.ui.infoServico.InfoServicoFragment;
import com.example.projetotcc.ui.listaFragment.ListaCategoriasFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.OnItemLongClickListener;
import com.xwray.groupie.ViewHolder;

import java.util.List;

import dominio.entidade.Pedido;
import dominio.entidade.Servico;
import dominio.entidade.Usuario;

public class PedidosFragment extends Fragment {

    private PedidosViewModel mViewModel;
    public static GroupAdapter adapter, adapter2;
    private SQLiteDatabase conexao;
    private Drawable drawablegreen, drawablered;
    private RecyclerView recyclerView;
    public static Pedido pedido;
    public static Usuario usuario;
    public static FragmentActivity application;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pedidos, container, false);
        drawablered = getResources().getDrawable(R.color.red);
        drawablegreen = getResources().getDrawable(R.color.green);
        adapter = new GroupAdapter();
        adapter2 = new GroupAdapter();
        application = getActivity();
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.pagerPedidos);
        try {
            ChatUsuarioFragment.registration2.remove();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        AdapterViewPedidos adapterView = new AdapterViewPedidos(PaginaUsuario.context, adapter, adapter2);
        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(adapterView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InfoServicoFragment.validar = false;
        ProcurarMensagem();
        mViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                PedidosFragment.PedidoItem pedidoItem = (PedidosFragment.PedidoItem) item;
                pedido = new Pedido();
                pedido = pedidoItem.pedido;
                FirebaseFirestore.getInstance().collection("/users")
                        .document(pedido.getUuid())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                usuario = new Usuario();
                                usuario = documentSnapshot.toObject(Usuario.class);
                                Log.i("teste", usuario.getId());
                                try {
                                    ChatUsuarioFragment.registration.remove();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                fragmentTransaction.replace(R.id.nav_host_fragment, new ChatUsuarioFragment()).commit();
                            }
                        });
            }
        });
        adapter2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                PedidosFragment.PedidoItem pedidoItem = (PedidosFragment.PedidoItem) item;
                pedido = new Pedido();
                pedido = pedidoItem.pedido;
                FirebaseFirestore.getInstance().collection("/users")
                        .document(pedido.getUuid())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                usuario = new Usuario();
                                usuario = documentSnapshot.toObject(Usuario.class);
                                Log.i("teste", usuario.getId());
                                try {
                                    ChatUsuarioFragment.registration.remove();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                fragmentTransaction.replace(R.id.nav_host_fragment, new ChatUsuarioFragment()).commit();
                            }
                        });
            }
        });
        adapter2.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull Item item, @NonNull View view) {
                PedidosFragment.PedidoItem pedidoItem = (PedidosFragment.PedidoItem) item;
                pedido = new Pedido();
                pedido = pedidoItem.pedido;
                new AlertDialog.Builder(PaginaUsuario.getContext)
                        .setTitle("Finalizar Servico")
                        .setMessage("Tem certeza que deseja finalizar o serviço?")
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PaginaUsuario.rStar.StartRat();
                            }
                        }).setNegativeButton("não", null).show();
                return false;
            }
        });
    }
    private void ProcurarMensagem() {

        FirebaseFirestore.getInstance().collection("/ultima-mensagem")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("pedidos")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();

                            if (documentChanges != null) {
                                for (DocumentChange doc : documentChanges) {
                                    if (doc.getType() == DocumentChange.Type.ADDED) {
                                        final Pedido pedido = doc.getDocument().toObject(Pedido.class);
                                        if(pedido.isServidor() == true) {
                                            adapter.add(new PedidoItem(pedido));
                                        }else
                                        {
                                            adapter2.add(new PedidoItem(pedido));
                                        }
                                    }
                                }
                            }
                        }else
                        {
                            new AlertDialog.Builder(PaginaUsuario.getContext)
                                    .setTitle("Pedidos Vazio")
                                    .setMessage("Nenhum serviço solicitado, solicite algo que necessite!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new CategoriasFragment()).commit();

                                        } }).setIcon(R.drawable.ic_chat) .show();
                        }
                    }

                });
    }
    private class PedidoItem extends Item<ViewHolder> {

        private Pedido pedido;

        private PedidoItem(Pedido pedido) {
            this.pedido = pedido;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            final TextView username = viewHolder.itemView.findViewById(R.id.Nomeusuariopedido);
            final TextView message = viewHolder.itemView.findViewById(R.id.Ultimotextopedido);
            final TextView quantidade = viewHolder.itemView.findViewById(R.id.Qmensagem);
            final ImageView online = viewHolder.itemView.findViewById(R.id.onlinePedido);
            ImageView imgPhoto = viewHolder.itemView.findViewById(R.id.imageUsuarioPedido);
            imgPhoto.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    FirebaseFirestore.getInstance().collection("/servico")
                            .document(pedido.getUuid())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    ListaCategoriasFragment.servico  = documentSnapshot.toObject(Servico.class);
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                    fragmentTransaction.replace(R.id.nav_host_fragment, new InfoServicoFragment()).commit();
                                }
                            });
                    return false;
                }
            });


            Picasso.get()
                    .load(pedido.getPhotoUrl())
                    .into(imgPhoto);
            FirebaseFirestore.getInstance().collection("/users")
                                            .document(pedido.getUuid())
                                            .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                                    usuario = new Usuario();
                                                    usuario = value.toObject(Usuario.class);
                                                    username.setText(usuario.getNome());
                                                    if (usuario.isOnline()) {
                                                        Log.e("Teste", usuario.getNome());

                                                        online.setImageDrawable(drawablegreen);
                                                    } else {
                                                        Log.e("Teste", usuario.getNome());

                                                        online.setImageDrawable(drawablered);
                                                    }
                                                }
                                            });
            FirebaseFirestore.getInstance().collection("/ultima-mensagem")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("pedidos")
                    .document(pedido.getUuid())
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            pedido = value.toObject(Pedido.class);
                            message.setText(pedido.getLastMessage());
                        }
                    });
            FirebaseFirestore.getInstance().collection("/noti")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection(pedido.getUuid())
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (!value.isEmpty()) {
                                List<DocumentChange> documentChanges = value.getDocumentChanges();
                                int i = 0;
                                for (DocumentChange doc : documentChanges) {
                                    i++;
                                }
                                quantidade.setText(String.valueOf(i));
                            }else{
                                quantidade.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
        }

        @Override
        public int getLayout() {
            return R.layout.item_pedidos;
        }
    }

}