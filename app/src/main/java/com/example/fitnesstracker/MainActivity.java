package com.example.fitnesstracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstracker.dao.AppDatabase;
import com.example.fitnesstracker.dataobject.Category;
import com.example.fitnesstracker.dataobject.Exercise;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText categoryEditText;
    Button saveCategoryBtn;
    TextView categoryTextView;
    TextView exerciseTextView;

    public static AppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myAppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "Exercise").allowMainThreadQueries().build();

        myAppDatabase = AppDatabase.getDatabase(getApplicationContext());

        saveCategoryBtn = findViewById(R.id.save_category_btn);
        categoryTextView = findViewById(R.id.category_text_view);
        exerciseTextView = findViewById(R.id.exercise_text_view);
        categoryEditText = findViewById(R.id.category_edit_text);

        saveCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String categoryName = categoryEditText.getText().toString();
                Category category = new Category(categoryName);
                category.setName(categoryName);

                myAppDatabase.exerciseDao().insertCategories(Arrays.asList(category));

                Toast.makeText(getApplicationContext(),"Category successfully added", Toast.LENGTH_LONG).show();
                showCategoriesAndExercises();
            }
        });
        showCategoriesAndExercises();
    }

    public void showCategoriesAndExercises() {
        String categories = "";
        String exercises = "";

        List<Category> categoryList = myAppDatabase.exerciseDao().loadAllCategories();
        for(Category category : categoryList) {
            categories += category.getName() + "\n";
        }

        List<Exercise> exerciseList = myAppDatabase.exerciseDao().loadAllExercises();
        for(Exercise exercise : exerciseList) {
            exercises += exercise.getName() + "\n";
        }

        categoryTextView.setText(categories);
        exerciseTextView.setText(exercises);
    }
}
