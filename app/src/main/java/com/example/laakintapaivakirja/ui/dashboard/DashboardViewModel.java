package com.example.laakintapaivakirja.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("User profile");
    }

    public LiveData<String> getText() {
        return mText;
    }
}