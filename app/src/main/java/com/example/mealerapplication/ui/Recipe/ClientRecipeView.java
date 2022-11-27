package com.example.mealerapplication.ui.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.accounthandling.ClientHandler;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.ui.client.MealsSearch;
import com.google.android.material.textfield.TextInputLayout;

public class ClientRecipeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recipe_view);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        TextInputLayout mealName = findViewById(R.id.client_view_recipe_name);
        TextInputLayout mealDescription = findViewById(R.id.client_view_recipe_description);
        TextInputLayout mealPrice = findViewById(R.id.client_view_recipe_price);
        TextInputLayout mealType = findViewById(R.id.client_view_recipe_type);
        TextInputLayout cookName = findViewById(R.id.client_view_cook_name);

        Button purchase =  findViewById(R.id.client_view_purchase_recipe);
        Button viewProfile =  findViewById(R.id.client_view_cook_profile);


        mealName.setHint("Meal Name: " +recipe.getRecipeName());
        mealDescription.setHint("Meal Desc.: " + recipe.getDescription());
        mealPrice.setHint("$" + recipe.getPrice());
        cookName.setHint(recipe.getCookName());
        mealType.setHint("Cuisine Type: " + recipe.getCuisineType());



        // Need to add on click for Cook View profile

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // We'll make this method return something eventually to display whether it was succesfully purchase or wtv
                ClientHandler.purchase(recipe);

                Intent intent = new Intent(ClientRecipeView.this, MealsSearch.class);
                startActivity(intent);

            }
        });
    }
}