package com.example.gadsleaderboardapp.services;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String TAG = "RetrofitInstance";
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";

    //create logger
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);


     //create OkHttpClient
     private static OkHttpClient client = new OkHttpClient.Builder().
             addInterceptor(interceptor).build();



    private static Retrofit.Builder sBuilder = new Retrofit.Builder().
            baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client);

    private static Retrofit sRetrofit = sBuilder.build();

    public static <S> S buildService(Class<S> serviceType){
        Log.d(TAG, "buildService: instansiating class");
        return sRetrofit.create(serviceType);
    }





}
