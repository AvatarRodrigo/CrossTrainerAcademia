package com.academia.crosstrainer.ui.pageRelatorios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageRelatoriosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PageRelatoriosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}