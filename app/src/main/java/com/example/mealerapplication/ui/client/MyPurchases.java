package com.example.mealerapplication.ui.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.ui.cook.MyMealsAdapter;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyPurchases extends AppCompatActivity implements MyPurchasesAdapter.OnElementClickedListener {

    RecyclerView recyclerView;
    MyPurchasesAdapter myAdapter;
    ArrayList<MealRequest> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    BottomNavigationView nav;
    Button swapView;
    ArrayList<MealRequest> toDisplay;
    TextView topLabel;
    int currentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchases);
        recyclerView = findViewById(R.id.my_purchases_list);

        currentState = 0;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        topLabel = findViewById(R.id.my_purchases_label);

        swapView = findViewById(R.id.swap_purchases);
        list = new ArrayList<>();
        toDisplay = new ArrayList();

        myAdapter = new MyPurchasesAdapter(this, list, this );
        recyclerView.setAdapter(myAdapter);




        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference notesRef = rootRef.collection("requests")
                .document("purchase")
                .collection("clients")
                .document(FirebaseAuth.getInstance().getUid())
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

                        list.add(r);
                        toDisplay.add(r);

                    }

                    myAdapter.notifyDataSetChanged();
                }

            }
        });

        rootRef = FirebaseFirestore.getInstance();
        notesRef = rootRef.collection("requests")
                .document("purchase")
                .collection("clients")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("completed");


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

                        toDisplay.add(r);

                    }

                }

            }
        });

        swapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentState == 0){
                    myAdapter.list = toDisplay;
                    myAdapter.notifyDataSetChanged();
                    topLabel.setText("Order History");
                    swapView.setText("View In Progress");
                    currentState = 1;
                }else{
                    currentState = 0;
                    myAdapter.list = list;
                    topLabel.setText("Orders in Progress");
                    myAdapter.notifyDataSetChanged();
                    swapView.setText("View history");
                }

            }
        });

        nav = findViewById(R.id.btm_nav);


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.searchMenu_client:
                        Intent intent = new Intent(MyPurchases.this, MealsSearch.class);
                        startActivity(intent);
                        return true;

                    case R.id.requests_client:
                        return true;

                    case R.id.myProfile_client:
                        Intent intent2 = new Intent(MyPurchases.this, myProfileClient.class);
                        startActivity(intent2);
                        return true;

//                    default:
                }

                return false;
            }
        });
    }

    @Override
    public void onElementClicked(int position) {

    }
}