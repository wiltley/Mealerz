package com.example.mealerapplication.ui.cook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mealerapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompletedSales extends AppCompatActivity {
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_sales);

//        nav = findViewById(R.id.btm_nav);
//        nav.getMenu().findItem(R.id.myMenu).setChecked(true);
//
//        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()){
//                    case R.id.myMenu:
//                        Intent intent0 = new Intent(CompletedSales.this, MyMealsActivity.class);
//                        startActivity(intent0);
//                        return true;
//
//                    case R.id.createFood:
//                        Intent intent = new Intent(CompletedSales.this, CreateRecipe.class);
//                        startActivity(intent);
//                        return true;
//
//                    case R.id.requests:
//                        Intent intent1 = new Intent(CompletedSales.this, MySales.class);
//                        startActivity(intent1);
//                        return true;
//                    case R.id.myProfile:
//                        Intent intent2 = new Intent(CompletedSales.this, myProfileCook.class);
//                        startActivity(intent2);
//                        return true;
//
//                    //default:
//                }
//
//                return false;
//            }
//        });
    }
}