package com.academia.crosstrainer.ui.pageAtividades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageAtividadesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PageAtividadesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}