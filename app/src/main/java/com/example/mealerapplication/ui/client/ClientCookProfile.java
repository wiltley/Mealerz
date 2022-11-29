package com.example.mealerapplication.ui.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ClientCookProfile extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref = db.collection("reviews")
                              .document("cooks")
                              .collection("COOKID")
                              .document("18lui00utQdn1ZckDJ4NT5dGODd2");
    BottomNavigationView nav;
    DatabaseReference mDatabase;
    TextView cookName;
    TextView cookEmail;
    TextView averageRating;
    TextView biography;
    RecyclerView testimonial;
    Button complaint;
//    ArrayList<Recipe> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_profile);

        //mDatabase = FirebaseDatabase.getInstance().getReference("COOKID").child("")
        cookName = (TextView) findViewById(R.id.cookName);
        cookEmail = (TextView) findViewById(R.id.cookEmail);
        averageRating = (TextView) findViewById(R.id.averageReview);
        biography = (TextView) findViewById(R.id.biography);
        testimonial = (RecyclerView) findViewById(R.id.testimonial);

        // CODE FOR POPULATING RECYCLER VIEW WITH ARRAY FROM DB
//        testimonial.setHasFixedSize(true);
//        testimonial.setLayoutManager(new LinearLayoutManager(this));
//
//        list = new ArrayList<>();
//        MyMealsAdapter myAdapter = new MyMealsAdapter(this, list, this);
//        testimonial.setAdapter(myAdapter);


        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        String CookName = document.getString("cookName");
                        String CookEmail = document.getString("cookEmail");
                        String Biography = document.getString("biography");
                        Number AverageRating = document.getDouble("averageReview");
                        cookName.setText(CookName);
                        cookEmail.setText(CookEmail);
                        biography.setText(Biography);
                        averageRating.setText(AverageRating.toString());
                        
//                        String Testimonial = document.getString("testimonial");
//                        testimonial.setText(Testimonial);

//                        ArrayList[] testimonial = new ArrayList[];
//                        for
                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });

        complaint = findViewById(R.id.complaintButton);
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientCookProfile.this, ComplaintCreation.class);
                startActivity(intent);
            }
        });


        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.searchMenu_client:
                        Intent intent0 = new Intent(ClientCookProfile.this, MealsSearch.class);
                        startActivity(intent0);
                        return true;

                    case R.id.requests_client:
                        Intent intent = new Intent(ClientCookProfile.this, ClientRecipeView.class);
                        startActivity(intent);
                        return true;

                    case R.id.myProfile_client:
                        Intent intent1 = new Intent(ClientCookProfile.this, myProfileClient.class);
                        startActivity(intent1);
                        return true;

//                    default:
                }

                return false;
            }
        });
    }
}