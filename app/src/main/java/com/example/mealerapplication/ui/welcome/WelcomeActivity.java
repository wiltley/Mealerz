package com.example.mealerapplication.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealerapplication.R;
import com.example.mealerapplication.databinding.ActivityWelcomeBinding;
import com.example.mealerapplication.ui.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
    private Button cookButton;
    private Button clientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        cookButton = findViewById(R.id.cookButton);
        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, Welcomephase2.class);
                startActivity(intent);
            }
        });

        clientButton = findViewById(R.id.clientButton);
        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, Welcomephase2.class);
                startActivity(intent);
            }
        });

    }
}