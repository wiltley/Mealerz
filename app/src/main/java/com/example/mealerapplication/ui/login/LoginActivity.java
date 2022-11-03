package com.example.mealerapplication.ui.login;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealerapplication.R;
import com.example.mealerapplication.databinding.ActivityLoginBinding;
import com.example.mealerapplication.ui.registration.SignupActivity;
import com.example.mealerapplication.ui.welcome.Welcomephase2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private Button registerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.loginButton;
        mAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.registerButton);
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
                Toast.makeText(getApplicationContext(), "Worked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, Welcomephase2.class);
                startActivity(intent);
            }
        });

        //OnClickListener that handles show/hide password buttons being clicked

        //Set onclick listener for show/hide password buttons on login activity
        Button showLoginPassword = findViewById(R.id.showHideBtnLogin);
        showLoginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEditText = findViewById(R.id.password);
                if(showLoginPassword.getText().toString().equals(getString(R.string.show_pass))){
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showLoginPassword.setText(R.string.hide_pass);
                } else{
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showLoginPassword.setText(R.string.show_pass);
                }
            }
        });






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

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, Welcomephase2.class);
                            //User object is only in the scope of this function here
//                            User user = new User();
//                            user.setCurrentUser(mAuth.getCurrentUser());
                            loginButton.setText("success");
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            loginButton.setText("failed");
                            Intent intent1 = new Intent(LoginActivity.this, SignupActivity.class);
                            startActivity(intent1);
                        }
                    }
                });


    }


}