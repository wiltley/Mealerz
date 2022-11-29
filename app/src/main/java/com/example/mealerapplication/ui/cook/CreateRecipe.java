package com.example.mealerapplication.ui.cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.accounthandling.CookHandler;
import com.example.mealerapplication.data.model.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;


public class CreateRecipe extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        TextInputLayout recipeName = findViewById(R.id.new_meal_name);
        TextInputLayout recipeCuisine = findViewById(R.id.new_meal_cuisine_type);
        TextInputLayout recipeDescription = findViewById(R.id.new_meal_description);
        TextInputLayout recipePrice = findViewById(R.id.new_meal_price);

        //String strTitle = String.valueOf(textInputLayout.getEditText().getText());
        Button submitRecipe = findViewById(R.id.create_new_recipe);
        Recipe recipe = new Recipe();


        submitRecipe.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View view){
                Context context = CreateRecipe.this;


               recipe.setRecipeName(String.valueOf(recipeName.getEditText().getText()));
               recipe.setPrice(String.valueOf(recipePrice.getEditText().getText()));
               recipe.setDescription(String.valueOf(recipeDescription.getEditText().getText()));
               recipe.setCuisineType(String.valueOf(recipeCuisine.getEditText().getText()));

               // We're definetely want to do some checks to see if the data
               // enter is valid before sending it to the database
               // Such as making sure none of the fields are empty

               CookHandler.addRecipe(recipe, context);
               Intent intent = new Intent(context, MyMealsActivity.class);
               startActivity(intent);

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
        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.myMenu:
                        Intent intent = new Intent(CreateRecipe.this, MyMealsActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.createFood:
                        return true;


                    case R.id.requests:
                        Intent intent3 = new Intent(CreateRecipe.this, MySales.class);
                        startActivity(intent3);
                        return true;

                    case R.id.myProfile:
                        Intent intent2 = new Intent(CreateRecipe.this, myProfileCook.class);
                        startActivity(intent2);
                        return true;

//                    default:
                }

                return false;
            }
        });
    }


}