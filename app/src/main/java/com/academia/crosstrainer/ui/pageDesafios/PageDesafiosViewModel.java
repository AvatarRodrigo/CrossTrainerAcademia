package com.academia.crosstrainer.ui.pageDesafios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageDesafiosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PageDesafiosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}