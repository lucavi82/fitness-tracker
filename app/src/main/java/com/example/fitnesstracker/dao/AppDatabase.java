package com.example.fitnesstracker.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.fitnesstracker.dataobject.Category;
import com.example.fitnesstracker.dataobject.Exercise;
import com.example.fitnesstracker.dataobject.ExerciseAndCategory;
import com.example.fitnesstracker.dataobject.ExerciseEntry;

import java.util.concurrent.Executors;


@Database(entities = {Category.class, Exercise.class, ExerciseEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract ExerciseDao exerciseDao();

    synchronized static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    static AppDatabase buildDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {

                    RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
                        public void onCreate(SupportSQLiteDatabase db) {
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    ExerciseDao exerciseDao = getDatabase(context).exerciseDao();
                                    exerciseDao.insertExerciseAndCategoriesAll(ExerciseAndCategory.populateDatabase());
                                }
                            });
                        }
                    };

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,
                                    "exercise_database")
                                    .addCallback(dbCallback).build();
                }
            }
        }
        return INSTANCE;
    }
}
