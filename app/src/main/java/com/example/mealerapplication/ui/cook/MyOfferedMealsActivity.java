package com.example.mealerapplication.ui.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.mealerapplication.R;
import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.data.rendering.ClickableAdapter;
import com.example.mealerapplication.ui.Recipe.CreateRecipe;
import com.example.mealerapplication.ui.Recipe.RecipeView;
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

public class MyOfferedMealsActivity extends AppCompatActivity implements ClickableAdapter.OnElementClickedListener {


    RecyclerView recyclerView;
    MyMealsAdapter myAdapter;
    ArrayList<Recipe> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offered_meals);

        recyclerView = findViewById(R.id.my_offered_meals_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyMealsAdapter(this, list, this );
        recyclerView.setAdapter(myAdapter);

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference notesRef = rootRef.collection("meals")
                .document("cooks")
                .collection(FirebaseAuth.getInstance().getUid())
                .document("all")
                .collection("meals");

        // Not too sure if we want this stored in here or not yet
        notesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Recipe r = new Recipe();


                        // We shouldn't be in need to the other stuff just yet
                        r.setDocumentID(document.getId());
                        r.setRecipeName(document.getString("Name"));
                        r.setCookName(document.getString("Cook Name"));
                        r.setCookID(document.getString("Cook ID"));
                        r.setDescription(document.getString("Description"));
                        r.setPrice(document.getString("Price"));
                        r.setOffered(String.valueOf(document.getBoolean("Offered")));

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
                    case R.id.myMenu:
                        Intent intent1 = new Intent(MyOfferedMealsActivity.this, MyMealsActivity.class);
                        startActivity(intent1);

                    case R.id.createFood:
                        Intent intent2 = new Intent(MyOfferedMealsActivity.this, CreateRecipe.class);
                        startActivity(intent2);

                    case R.id.myOffer:
                        break;

                    case R.id.requests:
//                        Intent intent2 = new Intent(MyMealsActivity.this, .class);
//                        startActivity(intent2);
                        break;
                    case R.id.myProfile:
//                        Intent intent2 = new Intent(MyMealsActivity.this, .class);
//                        startActivity(intent2);
                        break;

                    default:
                }

                return true;
            }
        });
    }

    @Override
    public void onElementClicked(int position) {

        Intent intent = new Intent(this, RecipeView.class);
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