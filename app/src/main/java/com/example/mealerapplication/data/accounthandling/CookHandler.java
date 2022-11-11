package com.example.mealerapplication.data.accounthandling;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealerapplication.data.model.Recipe;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CookHandler {

    // Mostly going to be static methods pertaining to the cook I think

    public static void addRecipe(Recipe recipe){

        // This recipe should not have a documentID yet
        //

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> r = new HashMap<>();


        // To get the Author's email might be annoying
        // Might wanna store both the email and UserID honestly
        r.put("Author", recipe.getAuthor());
        r.put("Author ID", recipe.getAuthorID());
        r.put("Name", recipe.getRecipeName());
        r.put("Briefing", recipe.getBriefing());
        r.put("Description", recipe.getDescription());
        r.put("Instructions", recipe.getInstructions());

        // The ingredients should already be in HashMap format before reaching here
        db.collection("meals")
                .document(userID)
                .collection("recipes")
                .add(r)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                        // No clue if this setter will actually work lmao
                        recipe.setDocumentID(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        // Now for adding the ingredients subcollection
        db.collection("meals")
                .document(userID)
                .collection("recipes")
                .document(recipe.getDocumentID())
                .collection("ingredients")
                .document("ingredients list")
                .set(recipe.getIngredients())
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

    public static void addRecipeToOffered(Recipe recipe){

        String id = recipe.getDocumentID();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> r = new HashMap<>();
        r.put(recipe.getRecipeName(), recipe.getBriefing());

        // The document's ID would match up with the ID of the recipe document
        db.collection("meals").document(userID).collection("offered recipes").document(recipe.getDocumentID())
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
        // Would probably be easier to pull the Recipe object
        // change a couple fields, and then just set the whole thing again
        // That is probably less efficient but oh well...


    }

}
