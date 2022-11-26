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

public class ClientRecipeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recipe_view);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        TextView mealName = findViewById(R.id.client_view_recipe_name);
        TextView mealDescription = findViewById(R.id.client_view_recipe_description);
        TextView mealPrice = findViewById(R.id.client_view_recipe_price);
        TextView mealType = findViewById(R.id.client_view_recipe_type);
        TextView cookName = findViewById(R.id.client_view_cook_name);

        Button purchase =  findViewById(R.id.client_view_purchase_recipe);
        Button viewProfile =  findViewById(R.id.client_view_cook_profile);


        mealName.setHint(recipe.getRecipeName());
        mealDescription.setHint(recipe.getDescription());
        mealPrice.setHint(recipe.getPrice());
        cookName.setHint(recipe.getCookName());
        mealType.setHint(recipe.getCuisineType());



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