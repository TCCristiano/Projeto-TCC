package com.example.projeto_tcc.ui.EditUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditUsuarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EditUsuarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Perfil");
    }

    public LiveData<String> getText() {
        return mText;
    }


}