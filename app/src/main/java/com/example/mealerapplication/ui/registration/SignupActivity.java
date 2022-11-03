package com.example.mealerapplication.ui.registration;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.User;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.example.mealerapplication.ui.welcome.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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

        emailEditText = findViewById(R.id.registerUserEmail);
        passwordEditText = findViewById(R.id.registerUserPass);
        registerButton = findViewById(R.id.registerBtn);


        loginButton = findViewById(R.id.loginButton2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button showRegisterPassword = findViewById(R.id.showHideBtnReg);
        showRegisterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showRegisterPassword.getText().toString().equals(getString(R.string.show_pass))){
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showRegisterPassword.setText(R.string.hide_pass);
                } else{
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showRegisterPassword.setText(R.string.show_pass);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                registerUser();
            }
        });
    }

    public void registerUserInDB(){
        //Careful when this is called because .set has the ability to overwrite documents
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String email = emailEditText.getText().toString();
        User userObj = new User(email, email, User.Role.CLIENT);
        Map<String, Object> userInfo = userObj.getUserMap();
//        userInfo.put("email", "");
//        userInfo.put("first name", "");
//        userInfo.put("last name", "");
//        userInfo.put("role", "");

        db.collection("users").document(auth.getCurrentUser().getUid())
                .set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
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
                    // create user in db
                    registerUserInDB();
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
