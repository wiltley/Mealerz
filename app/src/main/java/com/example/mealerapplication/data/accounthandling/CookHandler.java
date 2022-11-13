package com.example.mealerapplication.data.accounthandling;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealerapplication.data.model.Recipe;
import com.example.mealerapplication.ui.complaints.ComplaintsActivity;
import com.example.mealerapplication.ui.login.LoginActivity;
import com.example.mealerapplication.ui.welcome.Welcomephase2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CookHandler {

    // Mostly going to be static methods pertaining to the cook I think

    public static void addRecipe(Recipe recipe){

        // This recipe should not have a documentID yet
        //

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> r = new HashMap<>();


        // To get the Author's email might be annoying
        // Might wanna store both the email and UserID honestly
        r.put("Cook Name", userEmail);
        r.put("Cook ID", userID);
        r.put("Name", recipe.getRecipeName());
        r.put("Description", recipe.getDescription());
        r.put("Price", recipe.getPrice());
        r.put("Offered?", false);

        //  Not sure if we want to add Ingredients this way

        // The ingredients should already be in HashMap format before reaching here
        db.collection("meals")
                .document("cooks")
                .collection(userID)
                .document("all")
                .collection("meals")
                .add(r)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                        // No clue if this setter will actually work lmao
                        // or the change will only occur on this stack. I could always just store the document name

                        recipe.setDocumentID(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });



    }

    public static void addRecipeToOffered(Recipe recipe){

        String id = recipe.getDocumentID();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> r = new HashMap<>();
        // Dummy value for now
        r.put(recipe.getDocumentID(), recipe.getRecipeName());

        // The document's ID would match up with the ID of the recipe document
        // Add it to the cook's offered
        db.collection("meals")
                .document("cooks")
                .collection(recipe.getCookID())
                .document("offered")
                .update(r)
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



        // Add to all offered meals
        // This is going to have to be changed to made better for searching
        // We may want to break up collections into keywords that you can search by
        // For a cook to remove that recipe tho they will have to store were that recipe
        //appears

        db.collection("meals")
                .document("offered")
                .collection("all")
                .document(recipe.getDocumentID())
                .set(r)
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

    public static void updateRecipe(String docID){

        // Going to go under the pretext that the recipe objects
        // fields are being altered on the editing form.
        // Honestly there's very little different between this
        // and addRecipe

        // The only difference will bet .set and the .add
        // There's probably a more intelligent way of handling this

    }




}
