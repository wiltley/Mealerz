package com.example.mealerapplication.ui.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mealerapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ComplaintCreation extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_creation);

        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.searchMenu_client:
                        Intent intent = new Intent(ComplaintCreation.this, MealsSearch.class);
                        startActivity(intent);
                        return true;

                    case R.id.requests_client:
                        Intent intent1 = new Intent(ComplaintCreation.this, ClientRecipeView.class);
                        startActivity(intent1);
                        return true;

                    case R.id.myProfile_client:
                        Intent intent2 = new Intent(ComplaintCreation.this, myProfileClient.class);
                        startActivity(intent2);
                        return true;

//                    default:
                }

                return false;
            }
        });
    }
}