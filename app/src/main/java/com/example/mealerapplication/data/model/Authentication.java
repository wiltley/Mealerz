package com.example.mealerapplication.data.model;
import com.example.mealerapplication.ui.login.LoginActivity;
import static android.content.ContentValues.TAG;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealerapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public abstract class Authentication {
    private static FirebaseAuth mAuth;
    static FirebaseUser user;

    public Authentication() {
        mAuth = FirebaseAuth.getInstance();

    }
/*
    public static void signIn(String email, String password){

    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete (@NonNull Task < AuthResult > task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
            }
        }
    });
}

 */

}
