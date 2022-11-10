package com.example.mealerapplication.data.accounthandling;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealerapplication.ui.complaints.ComplaintsActivity;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.example.mealerapplication.ui.registration.SignupActivity;
import com.example.mealerapplication.ui.welcome.WelcomeActivity;
import com.example.mealerapplication.ui.welcome.Welcomephase2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

// This class will be huge for now but we will want to split it up further
// into a authentication handler, registration handler... etc
public class UserHandler {

    public static void registerUser(String email, String password, Context context, FirebaseAuth auth){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // create user in db
                    setUpUserInDB(auth);
                    Toast.makeText(context, "Registration complete!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Registration Failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public static void setUpUserInDB(FirebaseAuth auth){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", "");
        userInfo.put("first name", "");
        userInfo.put("last name", "");
        userInfo.put("role", "");
        userInfo.put("status", "");

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

    public static  void loginUser(String email, String password, Context context){

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(context, "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            checkIfBanned(context);

                        }else {

                            Toast.makeText(context, "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    public static void cookSetup(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        HashMap<String, Integer> test = new HashMap<String, Integer>();
        test.put("Yo", 2);


        System.out.println("reachd");
        db.collection("meals").document(user.getUid()).collection("recipes").document()
                .set(test)
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


    public static void checkIfBanned(Context context){

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("users").document(auth.getCurrentUser().getUid());

//         if role is cook and status is banned, show them message of them not being able to access account
//         else if role is cook and status is suspended, show them message with time remaining before their suspension uplifts
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.getString("role").equals("cook") && document.getString("status").equals("Banned")) {
                        Toast.makeText(context, "Sorry, but your account is permanently banned", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context, LoginActivity.class);
                        context.startActivity(i);
                    }
                    else if(document.getString("role").equals("cook") && document.getString("status").equals("Suspended")) {
                        Toast.makeText(context, "Sorry, but your account is temporarily banned for 3 days", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(context, LoginActivity.class);
                        context.startActivity(i);

                    }else if(!document.getString("role").equals("admin")){
                        Intent i = new Intent(context, Welcomephase2.class);
                        context.startActivity(i);
                    }else{

                        Intent i = new Intent(context, ComplaintsActivity.class);
                        context.startActivity(i);
                    }
                } else {

                }
            }
        });
    }


    public static void updateUserRole(String role){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("users").document(user.getUid());

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("role", role);
        ref.update(userInfo);

        // If the usertype is cook, it's going to require additional setup..
        if(role.equals("cook")){
            cookSetup();
        }


    }
}
