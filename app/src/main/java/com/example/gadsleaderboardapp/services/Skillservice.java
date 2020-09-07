package com.example.gadsleaderboardapp.services;

import androidx.lifecycle.LiveData;

import com.example.gadsleaderboardapp.Hours;
import com.example.gadsleaderboardapp.Skill;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Skillservice {

    @GET("/api/skilliq")
    Call<List<Skill>> getSkills();

    @GET("/api/hours")
    Call<List<Hours>> getLearningLeaders();

    @FormUrlEncoded
    @POST
    Call<Void> submitData(@Url String formUrl,
                          @Field("entry.1724812527") String email,
                          @Field("entry.420022196") String firstName,
                          @Field("entry.895527790") String lastName,
                          @Field("entry.1124918817") String link);


}
