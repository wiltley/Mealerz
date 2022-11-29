package com.example.mealerapplication.ui.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mealerapplication.R;

import java.util.ArrayList;

import com.example.mealerapplication.data.model.Complaint;
import com.example.mealerapplication.ui.cook.TestAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ComplaintsActivity extends AppCompatActivity implements TestAdapter.OnElementClickedListener  {

    RecyclerView recyclerView;
    TestAdapter myAdapter;
    ArrayList<Complaint> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    BottomNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complaints);
        //getActionBar().setTitle("Review Complaints");


        //DocumentReference docRef = db.collection("complaints").document("3peAFJuv37mH7PqIEjL5");

        //System.out.println("reached");

        recyclerView = findViewById(R.id.complaintsList);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new TestAdapter(this, list, this);
        recyclerView.setAdapter(myAdapter);

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference notesRef = rootRef.collection("complaints");
        notesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String accused = document.getString("accused");
                        String accuser = document.getString("accuser");
                        String message = document.getString("complaint");
                        String accusedUID = document.getString("accusedUID");
                        String documentId = document.getId();

                        list.add(new Complaint(accuser, accused, message, documentId, accusedUID));

                    }

                    myAdapter.notifyDataSetChanged();
                }

            }
        });

        nav = findViewById(R.id.btm_nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.complaint:
                        return true;


                    case R.id.myProfile_admin:
                        Intent intent = new Intent(ComplaintsActivity.this, myProfileAdmin.class);
                        startActivity(intent);
                        return true;

//                    default:
                }

                return false;
            }
        });
    }


    @Override
    public void onElementClicked(int position) {

        Intent intent = new Intent(this, ComplaintsDecision.class);
        intent.putExtra("complaint", list.get(position));
        startActivity(intent);
    }
}