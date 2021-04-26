package com.example.test.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MealTrackerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MealTrackerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("0");
    }

    public LiveData<String> getText() {
        return mText;
    }
}