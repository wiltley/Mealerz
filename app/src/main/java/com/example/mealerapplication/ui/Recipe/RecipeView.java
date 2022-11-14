package com.example.mealerapplication.ui.Recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.ui.cook.MyMealsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RecipeView extends AppCompatActivity {
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        // The recipe to display
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        // Pulls the ingredients and instructions...
        //recipe.getFullRecipe();

        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.myMenu:
                        Toast.makeText(RecipeView.this, "meals", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RecipeView.this, MyMealsActivity.class);
                        startActivity(intent);

                    case R.id.createFood:
                        Toast.makeText(RecipeView.this, "meals", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(RecipeView.this, CreateRecipe.class);
                        startActivity(intent2);

                    case R.id.requests:
                        Toast.makeText(RecipeView.this, "requests", Toast.LENGTH_LONG).show();
//                        Intent intent2 = new Intent(MyMealsActivity.this, .class);
//                        startActivity(intent2);
                        break;
                    case R.id.myProfile:
                        Toast.makeText(RecipeView.this, "profile", Toast.LENGTH_LONG).show();
//                        Intent intent2 = new Intent(MyMealsActivity.this, .class);
//                        startActivity(intent2);
                        break;

                    default:
                }

                return true;
            }
        });
    }
}