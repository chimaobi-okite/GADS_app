package com.example.gadsleaderboardapp.services;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gadsleaderboardapp.GadsRoomDatabase;
import com.example.gadsleaderboardapp.Hours;
import com.example.gadsleaderboardapp.GadsDao;
import com.example.gadsleaderboardapp.MainActivity;
import com.example.gadsleaderboardapp.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static final String TAG = "Repository";
    private GadsDao mGadsDao;


    private Skillservice mSkillservice;

    public Repository(Application application) {
        GadsRoomDatabase db = GadsRoomDatabase.getDatabase(application);
        mSkillservice = RetrofitInstance.buildService(Skillservice.class);
        mGadsDao = db.mHoursDao();
    }

    public LiveData<List<Skill>> getSkillList(){

       if(MainActivity.isConnected) {

           mSkillservice.getSkills().enqueue(new Callback<List<Skill>>() {
               @Override
               public void onResponse(Call<List<Skill>> call, Response<List<Skill>> response) {
                   new DeleteAysncTask(mGadsDao, 2).execute();
                   new InsertSkillAsyncTask(mGadsDao).execute(response.body());
               }

               @Override
               public void onFailure(Call<List<Skill>> call, Throwable t) {

               }
           });
       }
       return mGadsDao.getSkillsLeaders();
    }

    public LiveData<List<Hours>> getLearningLeaders(){

        if(MainActivity.isConnected){
            mSkillservice.getLearningLeaders().enqueue(new Callback<List<Hours>>() {
                @Override
                public void onResponse(Call<List<Hours>> call, Response<List<Hours>> response) {
                    new DeleteAysncTask(mGadsDao, 1).execute();
                    new InsertAsyncTask(mGadsDao).execute(response.body());
                }

                @Override
                public void onFailure(Call<List<Hours>> call, Throwable t) {

                }
            });
        }

        return mGadsDao.getLearningLeaders();
    }

    private class InsertAsyncTask extends AsyncTask<List,Void,Void>{

        GadsDao mGadsDao;
        public InsertAsyncTask(GadsDao myDao) {
            mGadsDao = myDao;
        }

        @Override
        protected Void doInBackground(List... datas) {
            mGadsDao.insertLearningLeaders(datas[0]);
            return null;
        }
    }

    private class InsertSkillAsyncTask extends AsyncTask<List,Void,Void>{

        GadsDao mGadsDao;
        public InsertSkillAsyncTask(GadsDao myDao) {
            mGadsDao = myDao;
        }

        @Override
        protected Void doInBackground(List... datas) {
            mGadsDao.insertSkillsLeaders(datas[0]);
            return null;
        }
    }

    private class DeleteAysncTask extends AsyncTask<Void,Void,Void>{

        GadsDao mDao;
        int table;

        public DeleteAysncTask(GadsDao dao, int table) {
            mDao = dao;
            this.table = table;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(table == 1){
                mDao.deleteAll();
            }
            else if(table == 2){
                mDao.deleteAllSkills();
            }

            return null;
        }
    }



}
