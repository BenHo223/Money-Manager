package com.example.moneymanager.ui.Balance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BalanceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BalanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Balance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}