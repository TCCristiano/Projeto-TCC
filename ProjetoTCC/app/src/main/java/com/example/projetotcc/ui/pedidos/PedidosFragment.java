package com.example.projetotcc.ui.pedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projetotcc.R;
import com.example.projetotcc.ui.perfil.PerfilFragment;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import dominio.entidade.Message;
import dominio.entidade.Usuario;

public class PedidosFragment extends Fragment {

    private PedidosViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);
        // TODO: Use the ViewModel
    }
    public static class MessageItem extends Item<ViewHolder> {

        private final Message message;
        private final Usuario usuario;

        public MessageItem(Message message, Usuario usuario) {
            this.message = message;
            this.usuario = usuario;
        }


        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView userNome = viewHolder.itemView.findViewById(R.id.Nomeusuariopedido);
            TextView txtMsgm = viewHolder.itemView.findViewById(R.id.Ultimotextopedido);
            ImageView imgMsgm = viewHolder.itemView.findViewById(R.id.imageUsuarioPedido);

            txtMsgm.setText(message.getText());
            userNome.setText(usuario.getNome());
            imgMsgm.setImageBitmap(usuario.getImage());
        }

        @Override
        public int getLayout() {
            return  R.layout.item_pedidos;
        }
    }
}