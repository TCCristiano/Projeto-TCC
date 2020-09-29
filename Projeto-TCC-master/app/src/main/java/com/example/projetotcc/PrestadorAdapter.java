package com.example.projetotcc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PrestadorAdapter extends RecyclerView.Adapter<PrestadorAdapter.PrestadorViewHolder>{

    private List<Prestador> prestador;
    private PrestadorListener prestadorListener;

    public PrestadorAdapter(List<Prestador> prestador, PrestadorListener prestadorListener) {
        this.prestador = prestador;
        this.prestadorListener = prestadorListener;
    }

    @NonNull
    @Override
    public PrestadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrestadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_prestador,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PrestadorViewHolder holder, int position) {

        holder.bindPrestador(prestador.get(position));
    }

    @Override
    public int getItemCount() {
        return prestador.size();
    }

    public List<Prestador> getSelectedPrestador(){
        List<Prestador> selectedPrestador = new ArrayList<>();
        for (Prestador prestador : prestador){
            if(prestador.isSelected){
                selectedPrestador.add(prestador);
            }
        }
        return selectedPrestador;
    }

    class PrestadorViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutPrestador;
        View viewBackground;
        RoundedImageView imagePrestador;
        TextView textNome, textServico, textDescricao;
        RatingBar ratingBar;
        ImageView imageSelected;


        public PrestadorViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPrestador = itemView.findViewById(R.id.layoutPrestador);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePrestador = itemView.findViewById(R.id.imagePrestador);
            textNome = itemView.findViewById(R.id.textNome);
            textServico = itemView.findViewById(R.id.textServico);
            textDescricao = itemView.findViewById(R.id.textDescricao);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        void bindPrestador (final Prestador prestador){

            imagePrestador.setImageResource(prestador.image);
            textNome.setText(prestador.nome);
            textServico.setText(prestador.servico);
            textDescricao.setText(prestador.descricao);
            ratingBar.setRating(prestador.rating);
            if(prestador.isSelected) {

                viewBackground.setBackgroundResource(R.drawable.prestador_selected_background);
                imageSelected.setVisibility(View.VISIBLE);

            }
            else{
                viewBackground.setBackgroundResource(R.drawable.prestador_background);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPrestador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(prestador.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.prestador_background);
                        imageSelected.setVisibility(View.GONE);
                        prestador.isSelected = false;
                        if (getSelectedPrestador().size() == 0){
                            prestadorListener.onPrestadorAction(false);
                        }
                    }
                    else{
                        viewBackground.setBackgroundResource(R.drawable.prestador_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        prestador.isSelected = true;
                        prestadorListener.onPrestadorAction(true);
                    }
                }
            });
        }

    }
}
