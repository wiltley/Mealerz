package com.example.mealerapplication.ui.complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mealerapplication.R;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ComplaintsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ComplaintsAdapter myAdapter;
    ArrayList<Complaint> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(), "Made it", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_complaints);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //DocumentReference docRef = db.collection("complaints").document("3peAFJuv37mH7PqIEjL5");

        //System.out.println("reached");

        recyclerView = findViewById(R.id.complaintsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        list.add(new Complaint("tha", "goat", "fr"));
        myAdapter = new ComplaintsAdapter(this, list);
        recyclerView.setAdapter(myAdapter);


        myAdapter.notifyDataSetChanged();

    }





}