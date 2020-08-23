package com.example.projeto_tcc.ui.MinhaLoja;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projeto_tcc.R;

public class MinhaLojaFragment extends Fragment {

    private MinhaLojaViewModel minhaLojaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        minhaLojaViewModel =
                ViewModelProviders.of(this).get(MinhaLojaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_minhaloja, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        minhaLojaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}