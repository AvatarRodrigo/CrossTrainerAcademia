package com.academia.crosstrainer.ui.pageConquistas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageConquistasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PageConquistasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}