package com.example.fitnesstracker.dao;

import android.arch.persistence.room.Dao;

import com.example.fitnesstracker.dataobject.ExerciseAndCategory;

import java.util.List;

@Dao
public abstract class ExerciseDao implements ExerciseBaseDao {

    void insertExerciseAndCategoriesAll(List<ExerciseAndCategory> exerciseAndCategories) {
        for (ExerciseAndCategory exerciseAndCategory : exerciseAndCategories) {
            insertCategories(exerciseAndCategory.getCategories());
            insertExercises(exerciseAndCategory.getExercise());
        }
    }

}
