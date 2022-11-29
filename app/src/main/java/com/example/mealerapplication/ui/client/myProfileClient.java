package com.example.mealerapplication.ui.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.cook.myProfileCook;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class myProfileClient extends AppCompatActivity {

    Button logOutButton;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_client);

        logOutButton = findViewById(R.id.logoutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(myProfileClient.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        nav = findViewById(R.id.btm_nav);


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.searchMenu_client:
                        Intent intent = new Intent(myProfileClient.this, MealsSearch.class);
                        startActivity(intent);
                        return true;

                    case R.id.requests_client:
                        Intent intent1 = new Intent(myProfileClient.this, MyPurchases.class);
                        startActivity(intent1);
                        return true;

                    case R.id.myProfile_client:
                        return true;
                }

                return false;
            }
        });
    }
}