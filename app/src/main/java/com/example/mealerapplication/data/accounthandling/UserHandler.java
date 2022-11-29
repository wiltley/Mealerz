package com.example.mealerapplication.data.accounthandling;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealerapplication.data.User;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// This class will be huge for now but we will want to split it up further
// into a authentication handler, registration handler... etc
public class UserHandler {

    public static void registerUser(HashMap<String, String> map, String password, Context context, FirebaseAuth auth) {

        auth.createUserWithEmailAndPassword(map.get("email"), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // create user in db
                    setUpUserInDB(auth, map);
                    Toast.makeText(context, "Registration complete!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Registration Failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public static void setUpUserInDB(FirebaseAuth auth, HashMap<String, String> map) {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("users").document(auth.getCurrentUser().getUid())
                .set(map)
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

    public static void loginUser(String email, String password, Context context) {

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
                            Toast.makeText(context.getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            checkIfBanned(context);

                        } else {

                            Toast.makeText(context, "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    public static void cookSetup() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


    }


    public static void checkIfBanned(Context context) {

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
                    // Can also read current user info in case we need it
                    User.setAddress(document.getString("address"));


                    if (document.getString("role").equals("cook") && document.getString("status").equals("Banned")) {
                        if (document.contains("permaBan") && document.getBoolean("permaBan") == true){
                            Toast.makeText(context, "Sorry, but your account is  permanently banned", Toast.LENGTH_LONG).show();
                        }
                        //Check if ban expired and remove banned status
                        else if (document.getLong("banExpiry") != null) {
                            Long banExpiry = document.getLong("banExpiry");
                            if (System.currentTimeMillis() >= banExpiry) {
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("status", null);
                                userMap.put("banExpiry", "");
                                docRef.update(userMap);
                                Intent i = new Intent(context, Welcomephase2.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                                return;
                            }

                            //If user is still banned show when ban expires
                            Date date = new Date(banExpiry);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
                            String dateString = dateFormat.format(date);
                            Toast.makeText(context, "Sorry, but your account is banned until " + dateString, Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(context, "Sorry, but your account is banned", Toast.LENGTH_LONG).show();
                        }

                        Intent i = new Intent(context, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    } else if (document.getString("role").equals("cook") && document.getString("status").equals("Suspended")) {
                        Toast.makeText(context, "Sorry, but your account is temporarily banned for 3 days", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(context, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);

                    } else {

                        Intent i = new Intent(context, Welcomephase2.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                } else {

                }
            }
        });
    }


    public static void updateUserRole(String role) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("users").document(user.getUid());

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("role", role);
        ref.update(userInfo);

        // If the usertype is cook, it's going to require additional setup..
        if (role.equals("cook")) {
            cookSetup();
        }


    }
}
