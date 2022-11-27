package com.example.mealerapplication.ui.cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.example.mealerapplication.ui.client.MyPurchasesAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MySales extends AppCompatActivity implements MySalesAdapter.OnElementClickedListener {

    RecyclerView recyclerView;
    MySalesAdapter myAdapter;
    ArrayList<MealRequest> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    BottomNavigationView nav;
    LinearLayout decisionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sales);


        decisionBar = findViewById(R.id.decisionBar);

        recyclerView = findViewById(R.id.my_sales_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MySalesAdapter(this, list, this );
        recyclerView.setAdapter(myAdapter);

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference notesRef = rootRef.collection("requests")
                .document("sale")
                .collection("cooks")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("in progress");

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
                        r.setClientAddress(document.getString("Client Address"));

                        list.add(r);

                    }

                    myAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public void onElementClicked(int position) {
        // Going to make a fragment pop up probably
        decisionBar.setVisibility(View.VISIBLE);

    }
}