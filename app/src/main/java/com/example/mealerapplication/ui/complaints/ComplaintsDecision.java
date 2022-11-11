package com.example.mealerapplication.ui.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Complaint;
import com.google.firebase.auth.FirebaseAuth;
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

        //getActionBar().setTitle("Administrative Action");

        Complaint complaint = (Complaint) getIntent().getSerializableExtra("complaint");

        message = findViewById(R.id.complaint_message);
        accus = findViewById(R.id.decisive_accused_name);
        dismiss = findViewById(R.id.dismiss_complaint);
        accused = complaint.getAccused();
        msg = complaint.getMessage();



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


                        // Did you know that this is valid syntax?



                        .collection("users")
                        .document(complaint.getAccused_UID())
                        .update(userMap);

                complaint.removeFromDB();

                Intent i = new Intent(ComplaintsDecision.this, ComplaintsActivity.class);
                startActivity(i);

            }
        });


        ban.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){

               long time = ban_time.getDate();


               complaint.removeFromDB();

               Intent i = new Intent(ComplaintsDecision.this, ComplaintsActivity.class);
               startActivity(i);
           }


        });


    }



}