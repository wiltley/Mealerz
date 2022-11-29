package com.example.mealerapplication.ui.cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class myProfileCook extends AppCompatActivity {

    Button logOutButton;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_cook);

        logOutButton = findViewById(R.id.logoutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(myProfileCook.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.myMenu:
                        Intent intent1 = new Intent(myProfileCook.this, MyMealsActivity.class);
                        startActivity(intent1);
                        return true;

                    case R.id.createFood:
                        Intent intent2 = new Intent(myProfileCook.this, CreateRecipe.class);
                        startActivity(intent2);
                        return true;

                    case R.id.requests:
                        Intent intent3 = new Intent(myProfileCook.this, MySales.class);
                        startActivity(intent3);
                        return true;
                    case R.id.myProfile:
                        return true;

                    //default:
                }

                return false;
            }
        });

    }
}