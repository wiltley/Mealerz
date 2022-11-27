package com.example.mealerapplication.data.accounthandling;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealerapplication.data.User;
import com.example.mealerapplication.data.model.Recipe;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ClientHandler {




    public static void purchase(Recipe recipe){

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> mr = new HashMap<>();

        mr.put("Requester Name", userEmail);
        mr.put("Requester ID", userID);
        mr.put("Cook ID", recipe.getCookID());
        mr.put("Cook Name", recipe.getCookName());
        mr.put("Meal Name", recipe.getRecipeName());
        mr.put("Price", recipe.getPrice());
        mr.put("Client Address", User.getAddress());
        mr.put("Status", "Pending");




        // Add it to the client's "in progress"
        db.collection("requests")
                .document("purchase")
                .collection("clients")
                .document(userID)
                .collection("in progress")
                .add(mr)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        String documentID = documentReference.getId();

                        // Yo... I don't know how terrible practice this is... but lowe it
                        // I wanted them to have matching document id's to make the process easier
                        // but I do NOT want to deal with any callbacks

                        // Add it to the cook's "in progress"
                        db.collection("requests")
                                .document("sale")
                                .collection("cooks")
                                .document(recipe.getCookID())
                                .collection("in progress").document(documentID)
                                .set(mr)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error writing document", e);
                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
}
