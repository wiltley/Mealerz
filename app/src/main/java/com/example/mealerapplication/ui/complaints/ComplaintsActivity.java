package com.example.mealerapplication.ui.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mealerapplication.R;

import java.util.ArrayList;

import com.example.mealerapplication.data.model.Complaint;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.example.mealerapplication.ui.cook.TestAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complaints);


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
    }


    @Override
    public void onElementClicked(int position) {

        // Gets us the complaint that was clicked
        Intent intent = new Intent(this, ComplaintsDecision.class);
        String accused = list.get(position).getAccused();
        String message = list.get(position).getMessage();
        String documentId = list.get(position).getDocumentID();
        String accusedUID = list.get(position).getAccused_UID();

        intent.putExtra("message", message );
        intent.putExtra("accused", accused );
        intent.putExtra("documentID", documentId );
        intent.putExtra("accusedUID", accusedUID );
        startActivity(intent);
    }
}