package com.example.mealerapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapplication.R;
//import com.example.mealerapplication.data.User;
import com.example.mealerapplication.data.accounthandling.UserHandler;
import com.example.mealerapplication.databinding.ActivityLoginBinding;
import com.example.mealerapplication.ui.registration.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private TextView registerButton;
    private boolean isBanned;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getActionBar().setTitle("Mealerz Authentication");

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.loginButton;
        mAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.bringToSignUp);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        //OnClickListener that handles show/hide password buttons being clicked

        //Set onclick listener for show/hide password buttons on login activity
        //Button showLoginPassword = findViewById(R.id.showHideBtnLogin);
        //showLoginPassword.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {
           //     passwordEditText = findViewById(R.id.password);
            //    if(showLoginPassword.getText().toString().equals(getString(R.string.show_pass))){
             //       passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
              //      showLoginPassword.setText(R.string.hide_pass);
               // } else{
                //    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                 //   showLoginPassword.setText(R.string.show_pass);
               // }
            //}
        //});


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);




    }

    private void loginUserAccount() {

        String email, password;
        email = usernameEditText.getText().toString();
        password= passwordEditText.getText().toString();
        UserHandler.loginUser(email, password, getApplicationContext());

    }
}