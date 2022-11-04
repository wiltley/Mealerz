package com.example.mealerapplication.ui.complaints;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.example.mealerapplication.ui.welcome.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ComplaintsDecision extends AppCompatActivity {
    TextView message;
    Button dismiss;
    TextView accus;
    String accused;
    String msg;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_decision);

        message = findViewById(R.id.complaint_message);
        accus = findViewById(R.id.decisive_accused_name);
        dismiss = findViewById(R.id.dismiss_complaint);
        accused = getIntent().getStringExtra("accused");
        msg = getIntent().getStringExtra("message");



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CalendarView ban_time = findViewById(R.id.ban_calendar);
        Button ban = findViewById(R.id.ban_button);


        accus.setText(accused);
        message.setText(msg);


        // This will later be handled by a moderator class or something by calling a static function
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String,Object> userMap = new HashMap<>();
                userMap.put("status", "Banned");

                db = FirebaseFirestore.getInstance ();

                db
                        .collection("users")
                        .document(getIntent().getStringExtra("accusedUID"))
                        .update(userMap);
                deleteFileOnDB();


            }
        });


        ban.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){

               long time = ban_time.getDate();

               deleteFileOnDB();

           }


        });


    }


    // Will eventually be handled by a moderator class or something by calling a static function
    public void deleteFileOnDB(){

        db = FirebaseFirestore.getInstance();
        db.collection("complaints").document(getIntent().getStringExtra("documentID"))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, getIntent().getStringExtra("documentID"));

                        Intent i = new Intent(ComplaintsDecision.this, ComplaintsActivity.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

}