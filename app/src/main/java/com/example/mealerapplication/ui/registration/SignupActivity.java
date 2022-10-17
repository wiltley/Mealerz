package com.example.mealerapplication.ui.registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mealerapplication.DashboardActivity;
import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Authentication;
import com.example.mealerapplication.databinding.ActivityLoginBinding;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.example.mealerapplication.ui.welcome.WelcomeActivity;
import com.example.mealerapplication.ui.welcome.Welcomephase2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private FirebaseAuth auth;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.registerUserEt);
        passwordEditText = findViewById(R.id.registerPassEt);
        registerButton = findViewById(R.id.registerBtn);

//        registerButton = findViewById(R.id.registerBtn);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignupActivity.this, WelcomeActivity.class);
//                startActivity(intent);
//            }
//        });

        loginButton = findViewById(R.id.loginButton2);
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

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration complete!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignupActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration Failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
