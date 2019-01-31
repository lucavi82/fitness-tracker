package com.example.fitnesstracker.dataobject;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseAndCategory {

    @Embedded
    private Exercise exercise;

    @Relation(parentColumn = "id", entityColumn = "id")
    private List<Category> categories;

    public ExerciseAndCategory (String exerciseName, List<Category> categories) {
        this.exercise = new Exercise(exerciseName);
        this.categories = categories;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static List<ExerciseAndCategory> populateDatabase() {
        List<ExerciseAndCategory> exerciseAndCategories = new ArrayList<>();
        Category biceps = new Category("Biceps");
        Category triceps = new Category("Triceps");
        Category shoulders = new Category("Shoulders");
        Category chests = new Category("Chests");
        Category back = new Category("Back");
        Category legs = new Category("Legs");

        exerciseAndCategories.add(new ExerciseAndCategory("Pull-ups", Arrays.asList(back, shoulders)));
        exerciseAndCategories.add(new ExerciseAndCategory("Bicep curl", Arrays.asList(biceps)));
        exerciseAndCategories.add(new ExerciseAndCategory("Push-ups", Arrays.asList(chests, triceps)));

        return exerciseAndCategories;
    }
}
