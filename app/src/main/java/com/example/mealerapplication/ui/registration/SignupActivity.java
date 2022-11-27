package com.example.mealerapplication.ui.registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.accounthandling.UserHandler;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText addressEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;

    private Button registerButton;
    private FirebaseAuth auth;
    private TextView loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //getActionBar().setTitle("Mealerz Registration");

        auth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.registerUserEmail);
        passwordEditText = findViewById(R.id.registerUserPass);
        registerButton = findViewById(R.id.registerButton);
        addressEditText = findViewById(R.id.registerUserAddress);
        firstNameEditText = findViewById(R.id.registerUserFirstName);
        lastNameEditText = findViewById(R.id.registerUserLastName);


        loginButton = findViewById(R.id.bringToLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                registerUser();
            }

        });
    }


    public void registerUser() {

        String password = passwordEditText.getText().toString();

        HashMap<String, String> map = new HashMap<>();

        map.put("fName", firstNameEditText.getText().toString());
        map.put("email", emailEditText.getText().toString());
        map.put("lName", lastNameEditText.getText().toString());
        map.put("address", addressEditText.getText().toString());
        map.put("status", "");

        if (TextUtils.isEmpty(map.get("email"))) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;

        }

        if (TextUtils.isEmpty(map.get("address"))) {
            Toast.makeText(getApplicationContext(), "Please enter address!", Toast.LENGTH_LONG).show();
            return;

        }
        UserHandler.registerUser(map, password, SignupActivity.this, auth);
    }
}
