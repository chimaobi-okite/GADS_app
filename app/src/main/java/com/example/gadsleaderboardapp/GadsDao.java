package com.example.gadsleaderboardapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GadsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLearningLeaders(List<Hours> hours);

    @Query("SELECT * FROM hours_table")
    LiveData<List<Hours>> getLearningLeaders();

    @Query("DELETE FROM hours_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSkillsLeaders(List<Skill> skills);

    @Query("SELECT * FROM skills_table")
    LiveData<List<Skill>> getSkillsLeaders();

    @Query("DELETE FROM skills_table")
    void deleteAllSkills();

}
