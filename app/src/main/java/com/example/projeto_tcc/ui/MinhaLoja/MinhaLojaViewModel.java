package com.example.projeto_tcc.ui.MinhaLoja;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MinhaLojaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MinhaLojaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Minha loja");
    }

    public LiveData<String> getText() {
        return mText;
    }
}