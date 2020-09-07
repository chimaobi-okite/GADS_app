package com.example.gadsleaderboardapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gadsleaderboardapp.services.Repository;

import java.util.ArrayList;
import java.util.List;

public class GadsViewModel extends AndroidViewModel {
    private static final String TAG = "GadsViewModel";
    // TODO: Implement the ViewModel
    private LiveData<List<Skill>> mySkills;
    private LiveData<List<Hours>> mDataHours;

    public LiveData<List<Skill>> getMySkills() {
        return mySkills;
    }


    public GadsViewModel(@NonNull Application application) {
        super(application);
        Repository myRepo = new Repository(application);
        mySkills = myRepo.getSkillList();
        mDataHours = myRepo.getLearningLeaders();
    }

    public LiveData<List<Hours>> getDataHours() {
        return mDataHours;
    }
}