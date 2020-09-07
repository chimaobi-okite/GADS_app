package com.example.gadsleaderboardapp.services;

import com.example.gadsleaderboardapp.models.Hours;
import com.example.gadsleaderboardapp.models.Skill;

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
                          @Field("entry.1824927963") String email,
                          @Field("entry.1877115667") String firstName,
                          @Field("entry.2006916086") String lastName,
                          @Field("entry.284483984") String link);


}
