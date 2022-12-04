package com.example.mealerapplication.ui.cook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.data.model.Review;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.example.mealerapplication.ui.client.ClientRecipeView;

import com.example.mealerapplication.ui.client.ComplaintCreation;
import com.example.mealerapplication.ui.client.MealsSearch;
import com.example.mealerapplication.ui.client.myProfileClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CookProfile extends AppCompatActivity {

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
    RecyclerView recyclerView;
    MyMealsAdapter myAdapter;
    ArrayList<Review> list;
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
        db = FirebaseFirestore.getInstance();
        testimonial.setHasFixedSize(true);
        testimonial.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Review>();
        CookProfileAdapter myAdapter = new CookProfileAdapter((Context) this, (ArrayList) list, (ClickableAdapter.OnElementClickedListener) this);
        testimonial.setAdapter(myAdapter);

        EventChangeListner();

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
                Intent intent = new Intent(CookProfile.this, ComplaintCreation.class);
                startActivity(intent);
            }
        });


        nav = findViewById(R.id.btm_nav);
        nav.getMenu().findItem(R.id.myProfile_client).setChecked(true);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.searchMenu_client:
                        Intent intent0 = new Intent(CookProfile.this, MealsSearch.class);
                        startActivity(intent0);
                        return true;

                    case R.id.requests_client:
                        Intent intent = new Intent(CookProfile.this, ClientRecipeView.class);
                        startActivity(intent);
                        return true;

                    case R.id.myProfile_client:
                        Intent intent1 = new Intent(CookProfile.this, myProfileClient.class);
                        startActivity(intent1);
                        return true;

//                    default:
                }

                return false;
            }
        });
    }

    private void EventChangeListner() {
        db.collection("Testimonial")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(Review.class));
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}