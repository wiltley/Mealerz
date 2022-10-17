package com.example.mealerapplication.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.login.LoginActivity;

public class Welcomephase2 extends AppCompatActivity {
    private Button signoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomephase2);

        signoutButton = findViewById(R.id.signoutButton);
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcomephase2.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}