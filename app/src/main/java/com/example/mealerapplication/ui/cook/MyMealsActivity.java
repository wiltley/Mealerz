package com.example.mealerapplication.ui.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyMealsActivity extends AppCompatActivity implements ClickableAdapter.OnElementClickedListener {


    RecyclerView recyclerView;
    // MyMealsAdapter and MyComplaints Adapter can probably be generalized
    // Esp given how much overlapping code they'll have
    MyMealsAdapter myAdapter;
    ArrayList<Recipe> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meals);

        recyclerView = findViewById(R.id.my_meals_list);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyMealsAdapter(this, list, this );
        recyclerView.setAdapter(myAdapter);
    }


    private void populate(){

        // Firebase calls to pull recipes etc
        // We probably don't want to pull and deconstruct here to avoid
        // needless memory usage so let's just pull the titles userId's for now

    }

    @Override
    public void onElementClicked(int position) {

    }
}