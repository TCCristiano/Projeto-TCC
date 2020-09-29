package com.example.projetotcc.ui.minhaLoja;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projetotcc.R;
import com.example.projetotcc.ui.perfil.PerfilFragment;

public class MinhaLojaFragment extends Fragment {

    private MinhaLojaViewModel mViewModel;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_minha_loja, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MinhaLojaViewModel.class);
        // TODO: Use the ViewModel
    }
}