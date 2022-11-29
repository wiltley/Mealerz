package com.example.mealerapplication.ui.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Complaint;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ComplaintsDecision extends AppCompatActivity {
    TextInputEditText message;
    Button dismiss;
    TextView accus;
    String accused;
    String msg;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    CalendarView calendar;
    long banTime;
    BottomNavigationView nav;

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

        message.setText(msg);


        calendar = findViewById(R.id.ban_calendar);
        Button ban = findViewById(R.id.ban_button);
        banTime = System.currentTimeMillis();

        Button permaban = findViewById(R.id.permaBanButton);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        ban.setText(getString(R.string.ban_until, dateFormat.format(date)));


        accus.setText(accused);


        // This will later be handled by a moderator class or something by calling a static function
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String,Object> userMap = new HashMap<>();
                userMap.put("status", "");

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
               Map<String,Object> userMap = new HashMap<>();
               userMap.put("status", "Banned");
               userMap.put("banExpiry", banTime);
               long time = calendar.getDate();
               db = FirebaseFirestore.getInstance();
               db.collection("users").document(complaint.getAccused_UID()).update(userMap);
               complaint.removeFromDB();

               Intent i = new Intent(ComplaintsDecision.this, ComplaintsActivity.class);
               startActivity(i);
           }


        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Month integer is 0-indexed for some reason (Jan = 0, Dec = 11), so add 1 to be readable for users
                String dateString = year + "-" + (month+1) + "-" + day;
                ban.setText(getString(R.string.ban_until, dateString));
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                banTime = c.getTimeInMillis();
            }
        });


        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.complaint:
                        Intent intent = new Intent(ComplaintsDecision.this, ComplaintsActivity.class);
                        startActivity(intent);
                        return true;


                    case R.id.myProfile_admin:
                        Intent intent1 = new Intent(ComplaintsDecision.this, myProfileAdmin.class);
                        startActivity(intent1);
                        return true;

//                    default:
                }

                return false;
            }
        });

        permaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("status", "Banned");
                userMap.put("banExpiry", -1);
                userMap.put("permaBan", true);
                db = FirebaseFirestore.getInstance();
                db.collection("users").document(complaint.getAccused_UID()).update(userMap);
                complaint.removeFromDB();

                Intent i = new Intent(ComplaintsDecision.this, ComplaintsActivity.class);
                startActivity(i);
            }
        });
    }



}