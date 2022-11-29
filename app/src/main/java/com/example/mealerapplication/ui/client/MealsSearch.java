package com.example.mealerapplication.ui.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.data.rendering.Searcher;

import com.example.mealerapplication.ui.cook.MyMealsAdapter;
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
import java.util.List;

public class MealsSearch extends AppCompatActivity implements MealsSearchAdapter.OnElementClickedListener {

    RecyclerView recyclerView;
    MyMealsAdapter myAdapter;
    ArrayList<Recipe> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    BottomNavigationView nav;
    Spinner spinner;
    ListView listview;
    SearchView searchView;
    Button searchButton;
    Button resetSearchButton;
    Searcher searchFunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_meals_search);

        resetSearchButton = findViewById(R.id.search_reset_button);
        searchView = findViewById(R.id.searchView);
        spinner = findViewById(R.id.search_spinner);
        List<String> search = new ArrayList<>();
        search.add("Meal Name");
        search.add("Cuisine Type");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, search);


        spinner.setAdapter(adapter);

        recyclerView = findViewById(R.id.search_meals_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        // Pass it to the search class
        searchFunc = new Searcher(list);
        //

        myAdapter = new MyMealsAdapter(this, list, this );
        recyclerView.setAdapter(myAdapter);

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference notesRef = rootRef.collection("meals")
                .document("offered")
                .collection("all");

        notesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Recipe r = new Recipe();


                        r.setDocumentID(document.getId());
                        r.setRecipeName(document.getString("recipeName"));
                        r.setCookName(document.getString("cookName"));
                        r.setCookID(document.getString("cookID"));
                        r.setDescription(document.getString("description"));
                        r.setPrice(document.getString("price"));
                        r.setOffered(String.valueOf(document.getString("offered")));
                        r.setCuisineType(document.getString("cuisineType"));

                        list.add(r);

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
                    case R.id.searchMenu_client:
                        return true;

                    case R.id.requests_client:
                        Intent intent = new Intent(MealsSearch.this, MyPurchases.class);
                        startActivity(intent);
                        return true;

                    case R.id.myProfile_client:
                        Intent intent1 = new Intent(MealsSearch.this, myProfileClient.class);
                        startActivity(intent1);
                        return true;

//                    default:
                }

                return false;
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                myAdapter.list = (ArrayList<Recipe>) searchFunc.search(s, adapter.getItem(spinner.getSelectedItemPosition()) );
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });

        resetSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myAdapter.list = (ArrayList<Recipe>) searchFunc.getOriginal();
                myAdapter.notifyDataSetChanged();

            }
        });



    }

    @Override
    public void onElementClicked(int position) {

        Intent intent = new Intent(this, ClientRecipeView.class);
        // If serializable works as expected we won't have to do any of this stuff
        String name = list.get(position).getRecipeName();
        String author = list.get(position).getCookName();

        String documentId = list.get(position).getDocumentID();

        // Only thing we might wanna putExtra is the document ID
        intent.putExtra("documentID", documentId );
        intent.putExtra("recipe", list.get(position));
        startActivity(intent);

    }
}