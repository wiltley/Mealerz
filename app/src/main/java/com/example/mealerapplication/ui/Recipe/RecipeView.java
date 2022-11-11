package com.example.mealerapplication.ui.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;

public class RecipeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        // The recipe to display
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
    }
}