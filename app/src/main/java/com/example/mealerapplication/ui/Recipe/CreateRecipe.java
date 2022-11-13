package com.example.mealerapplication.ui.Recipe;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.accounthandling.CookHandler;
import com.example.mealerapplication.data.model.Recipe;
import com.google.android.material.textfield.TextInputEditText;

public class CreateRecipe extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        TextInputEditText recipeName = findViewById(R.id.new_meal_name);
        TextInputEditText recipeCuisine = findViewById(R.id.new_meal_name);
        TextInputEditText recipeDescription = findViewById(R.id.new_meal_description);
        TextInputEditText recipePrice = findViewById(R.id.new_meal_price);

        Button submitRecipe = findViewById(R.id.create_new_recipe);
        Recipe recipe = new Recipe();


        submitRecipe.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View view){



               recipe.setRecipeName(String.valueOf(recipeName.getText()));
               recipe.setDescription(String.valueOf(recipeDescription.getText()));
               recipe.setDescription(String.valueOf(recipeDescription.getText()));
               recipe.setCuisineType(String.valueOf(recipeCuisine.getText()));

               // We're definetely want to do some checks to see if the data
               // enter is valid before sending it to the database
               // Such as making sure none of the fields are empty

               CookHandler.addRecipe(recipe);

           }
           }
        );

        //imgGallery = findViewById(R.id.imageFood);
        //Button btnGallery = findViewById(R.id.imageUpload);

        //btnGallery.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {

          //      Intent iGallery = new Intent(Intent.ACTION_PICK);
           //     iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //    startActivityForResult(iGallery,GALLERY_REQ_CODE);

            //}
        //});

    //}

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
     //   super.onActivityResult(requestCode, resultCode, data);

      //  if(resultCode == RESULT_OK) {
       //     if (requestCode == GALLERY_REQ_CODE) {
        //        imgGallery.setImageURI(data.getData());
        //}
    //}
    }
}