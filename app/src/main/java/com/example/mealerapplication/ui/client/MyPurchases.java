package com.example.mealerapplication.ui.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.ui.cook.MyMealsAdapter;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyPurchases extends AppCompatActivity implements MyPurchasesAdapter.OnElementClickedListener {

    RecyclerView recyclerView;
    MyMealsAdapter myAdapter;
    ArrayList<MealRequest> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    //BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchases);
        // TODO: 2022-11-23  Change this to match the proper r.id_list
        recyclerView = findViewById(R.id.my_purchases_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyMealsAdapter(this, list, this );
        recyclerView.setAdapter(myAdapter);

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference notesRef = rootRef.collection("requests")
                .document("purchase")
                .collection("clients")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("in progress");

        // Not too sure if we want this stored in here or not yet
        notesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MealRequest r = new MealRequest();


                        r.setDocumentID(document.getId());
                        r.setClientName(document.getString("Requester Name"));
                        r.setClientID(document.getString("Requester ID"));
                        r.setCookID(document.getString("Cook ID"));
                        r.setCookName(document.getString("Cook Name"));
                        r.setMealName(document.getString("Meal Name"));
                        r.setStatus(document.getString("Status"));

                        list.add(r);

                    }

                    myAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public void onElementClicked(int position) {

    }
}