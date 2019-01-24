package com.example.fitnesstracker.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.fitnesstracker.dataobject.Category;
import com.example.fitnesstracker.dataobject.Exercise;

@Database(entities = {Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract ExerciseDao exerciseDao();


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,
                                    "exercise_database").build();
                }
            }
        }
        return INSTANCE;
    }
}