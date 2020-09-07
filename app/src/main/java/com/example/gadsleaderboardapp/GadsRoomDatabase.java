package com.example.gadsleaderboardapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Hours.class, Skill.class}, version = 3, exportSchema = false)
public abstract class GadsRoomDatabase extends RoomDatabase {
    public abstract GadsDao mHoursDao();

    private static GadsRoomDatabase INSTANCE;

    public static GadsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GadsRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GadsRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
