package com.example.mealerapplication.data.accounthandling;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealerapplication.data.model.MealRequest;
import com.example.mealerapplication.data.model.Recipe;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class CookHandler {

    // Mostly going to be static methods pertaining to the cook I think

    public static void addRecipe(Recipe recipe, Context context){

        // This recipe should not have a documentID yet
        //

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> r = new HashMap<>();


        // To get the Author's email might be annoying
        // Might wanna store both the email and UserID honestly

        r.put("cookName", userEmail);
        r.put("cookID", userID);
        r.put("recipeName", recipe.getRecipeName());
        r.put("cuisineType", recipe.getCuisineType());
        r.put("description", recipe.getDescription());
        r.put("price", recipe.getPrice());
        r.put("offered", "false");

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
                        Toast.makeText(context.getApplicationContext(), "Recipe added!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });



    }

    public static void addRecipeToOffered(Recipe recipe, Context context){

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
                .set(r, SetOptions.merge())
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
                .set(recipe.getRecipeMap())
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

        // Update the offered tag within the recipe

        Map<String, Object> f = new HashMap<>();
        f.put("offered", "true");
        db.collection("meals")
                        .document("cooks")
                        .collection(userID)
                        .document("all")
                        .collection("meals")
                        .document(recipe.getDocumentID())
                        .update(f)
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


    public static void removeFromOffered(Recipe recipe, Context context){

      // Removes the meal from the cook's own offered list
      Map<String, Object> deleteOffered = new HashMap<>();
      deleteOffered.put(recipe.getDocumentID(), FieldValue.delete());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("meals")
                       .document("cooks")
                       .collection(recipe.getCookID())
                       .document("offered")
                       .update(deleteOffered)
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

        // Remove the document from offered
        db.collection("meals")
                      .document("offered")
                      .collection("all")
                      .document(recipe.getDocumentID())
                      .delete()
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

        Map<String, Object> f = new HashMap<>();
        f.put("offered", "false");
        db.collection("meals")
                        .document("cooks")
                        .collection(recipe.getCookID())
                        .document("all")
                        .collection("meals")
                        .document(recipe.getDocumentID())
                        .update(f)
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

    // This method is basically only to clean up from the "in progress" sections
    // of purchase and sale
    public static void cleanupRequest(MealRequest mr){

        //Clean up client
         FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
         rootRef.collection("requests")
                 .document("purchase")
                 .collection("clients")
                 .document(mr.getClientID())
                 .collection("in progress")
                 .document(mr.getDocumentID())
                 .delete()
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

        // Clean up cook
        rootRef.collection("requests")
                .document("sale")
                .collection("cooks")
                .document(mr.getCookID())
                .collection("in progress")
                .document(mr.getDocumentID())
                .delete()
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

    public static void acceptRequest(MealRequest mr, String toAction){

        cleanupRequest(mr);

        // Deal with clients adding to
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> m = new HashMap<>();

        m.put("Requester Name", mr.getClientName());
        m.put("Requester ID", mr.getClientID());
        m.put("Cook ID", mr.getCookID());
        m.put("Meal Name", mr.getMealName());
        m.put("Status", toAction);



        // Add it to the client's "completed"
        db.collection("requests")
                .document("purchase")
                .collection("clients")
                .document(mr.getClientID())
                .collection("completed")
                .document(mr.getDocumentID())
                .set(m)
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

        // Add it to the cook's completed
        db.collection("requests")
                .document("sale")
                .collection("cooks")
                .document(mr.getCookID())
                .collection("completed")
                .document(mr.getDocumentID())
                .set(m)
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



}