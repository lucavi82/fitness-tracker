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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText categoryEditText;
    Button saveCategoryBtn;
    TextView displayView;

    public static AppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "Exercise").allowMainThreadQueries().build();

        saveCategoryBtn = findViewById(R.id.save_category_btn);
        displayView = findViewById(R.id.category_text_view);
        categoryEditText = findViewById(R.id.category_edit_text);

        saveCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String categoryName = categoryEditText.getText().toString();
                Category category = new Category();
                category.setName(categoryName);

                myAppDatabase.exerciseDao().insertCategories(category);

                Toast.makeText(getApplicationContext(),"Category successfully added", Toast.LENGTH_LONG).show();
                showCategories();
            }
        });
        showCategories();
    }

    public void showCategories() {
        String categories = "";

        List<Category> categoryList = myAppDatabase.exerciseDao().loadAllCategories();
        for(Category category : categoryList) {
            categories += category.getName() + "\n";
        }

        displayView.setText(categories);
    }
}
