package com.academia.crosstrainer.ui.pageRanking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageRankingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PageRankingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}