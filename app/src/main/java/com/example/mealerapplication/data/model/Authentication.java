package com.example.mealerapplication.data.model;
import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public abstract class Authentication {
    private static FirebaseAuth mAuth;
    static FirebaseUser user;

    public Authentication(){
        mAuth = FirebaseAuth.getInstance();

    }

    public static void signIn(String email, String password){
            mAuth.signInWithEmailAndPassword(email, password);
            user = mAuth.getCurrentUser();
            System.out.println("Cleared?");
    }
}
