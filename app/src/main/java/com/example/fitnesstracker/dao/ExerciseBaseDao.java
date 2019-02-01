package com.example.fitnesstracker.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fitnesstracker.dataobject.Category;
import com.example.fitnesstracker.dataobject.Exercise;
import com.example.fitnesstracker.dataobject.ExerciseEntry;

import java.util.List;

@Dao
public interface ExerciseBaseDao {

    @Query("SELECT * FROM Category")
    List<Category> loadAllCategories();

    @Query("SELECT * FROM Exercise")
    List<Exercise> loadAllExercises();

    @Insert
    void insertExercises(Exercise ... exercises);

    @Insert
    void insertCategories(List<Category> categories);

    @Insert
    void insertExerciseEntries(ExerciseEntry ... exerciseEntries);
}
