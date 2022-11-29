package com.example.mealerapplication.ui.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapplication.R;
import com.example.mealerapplication.ui.client.MealsSearch;
import com.example.mealerapplication.ui.complaints.ComplaintsActivity;
import com.example.mealerapplication.ui.cook.MyMealsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Welcomephase2 extends AppCompatActivity {
    private Button proceedButton;

    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomephase2);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        TextView tV = findViewById(R.id.welcome2);
        proceedButton = findViewById(R.id.welcome_proceed);

        DocumentReference docRef = db.collection("users").document(auth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        role = document.getString("role");
                        tV.setText(tV.getText().toString() + role);

                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });


        Button continueButton = findViewById(R.id.welcome_proceed);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(role.equals("cook")){
                    Intent intent = new Intent(Welcomephase2.this, MyMealsActivity.class);
                    startActivity(intent);
                }else if(role.equals("client")){

                    Intent intent = new Intent(Welcomephase2.this, MealsSearch.class);
                    startActivity(intent);
                }else if(role.equals("admin")){
                    Intent intent = new Intent(Welcomephase2.this, ComplaintsActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}