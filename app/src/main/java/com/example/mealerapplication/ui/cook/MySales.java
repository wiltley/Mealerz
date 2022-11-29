package com.example.mealerapplication.ui.cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mealerapplication.R;
import com.example.mealerapplication.data.accounthandling.CookHandler;
import com.example.mealerapplication.data.model.MealRequest;
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

public class MySales extends AppCompatActivity implements MySalesAdapter.OnElementClickedListener {

    RecyclerView recyclerView;
    MySalesAdapter myAdapter;
    ArrayList<MealRequest> list;
    FirebaseAuth auth;
    FirebaseFirestore db ;
    BottomNavigationView nav;
    LinearLayout decisionBar;
    MealRequest toAction;
    int toActionPosition;
    Button acceptRequest;
    Button denyRequest;
    Drawable navBack;
    int altNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sales);



        nav = findViewById(R.id.btm_nav);
        navBack = nav.getBackground();

        String uri = "@drawable/nav_layout_sqr";
        altNav  = getResources().getIdentifier(uri, null, getPackageName());

        acceptRequest = findViewById(R.id.requestApprove);
        denyRequest = findViewById(R.id.requestDeny);

        decisionBar = findViewById(R.id.decisionBar);

        recyclerView = findViewById(R.id.my_sales_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MySalesAdapter(this, list, this);
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

        nav.getMenu().findItem(R.id.requests).setChecked(true);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.myMenu:
                        Intent intent = new Intent(MySales.this, MyMealsActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.createFood:
                        Intent intent1 = new Intent(MySales.this, CreateRecipe.class);
                        startActivity(intent1);
                        return true;

                    case R.id.requests:
                        return true;

                    case R.id.myProfile:
                        Intent intent2 = new Intent(MySales.this, myProfileCook.class);
                        startActivity(intent2);
                        return true;

//                    default:
                }

                return false;
            }
        });

        acceptRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Deny

                CookHandler.acceptRequest(toAction, "Denied");
                decisionBar.setVisibility(View.GONE);
                list.remove(toActionPosition);
                nav.setBackground(navBack);
                myAdapter.notifyDataSetChanged();

            }
        });

        denyRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CookHandler.acceptRequest(toAction, "Accepted");
                decisionBar.setVisibility(View.GONE);
                list.remove(toActionPosition);
                nav.setBackground(navBack);
                myAdapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    public void onElementClicked(int position) {
        // Going to make a fragment pop up probably
        decisionBar.setVisibility(View.VISIBLE);
        // TODO FIX
        toAction = list.get(position);
        nav.setBackground(getDrawable(altNav));
        for(View tempItemView : myAdapter.itemViewList) {
            /** navigate through all the itemViews and change color
             of selected view to colorSelected and rest of the views to colorDefault **/
            if(myAdapter.itemViewList.get(position) == tempItemView) {
                tempItemView.setBackgroundResource(R.color.grey);

            }
            else{
                tempItemView.setBackgroundResource(R.color.white);
            }
        }


    }
}