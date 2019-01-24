package com.example.fitnesstracker.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fitnesstracker.dataobject.Category;
import com.example.fitnesstracker.dataobject.Exercise;
import com.example.fitnesstracker.dataobject.ExerciseEntry;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM categories")
    List<Category> loadAllCategoriesByIds();

    @Insert
    void insertExercises(Exercise ... exercises);

    @Insert
    void insertCategories(Category ... categories);

    @Insert
    void insertExerciseEntries(ExerciseEntry ... exerciseEntries);
}
