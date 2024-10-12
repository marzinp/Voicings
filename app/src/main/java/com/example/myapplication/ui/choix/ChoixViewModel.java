package com.example.myapplication.ui.choix;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChoixViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ChoixViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tables fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}