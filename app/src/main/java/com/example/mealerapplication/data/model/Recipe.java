package com.example.mealerapplication.data.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.Serializable;

public class Recipe implements Serializable {

    private String recipeName;
    private String description;

    // We might wants instructions to be it's own hashmap similarly to
    // ingredients instead


    private String author;
    private String documentID;
    private String authorID;
    private String cuisineType;

    // Probably going to have to parse it??? Idk
    private float price;


    // Should only be used when creating a new recipe
    public Recipe(){

    }

    // Should be used when pulling a recipe from the Firestore
    // Constructor could honestly also just take a hashmap or 2 and deconstruct them instead of having to pass everything
    public Recipe(String recipeName, String description,   String author,  String documentID, String authorID){

        this.recipeName = recipeName;
        this.description = description;
        this.author = author;
        this.authorID = authorID;

        // If it's a new recipe this is supposed to be null
        this.documentID = documentID;

    }

    public void setDocumentID(String documentID){this.documentID = documentID;}
    public void setRecipeName(String recipeName){this.recipeName = recipeName;}
    public void setAuthor(String author){this.author = author;}
    public void setAuthorID(String authorID){this.authorID = authorID;}
    public void setDescription(String description){this.description = description;}
    public void setCuisineType(String cuisineType){this.cuisineType = cuisineType;}
    public void setPrice(float price){this.price = price;}

    public String getRecipeName(){
        return recipeName;
    }
    public String getDescription(){
        return description;
    }
    public String getAuthor(){return author;}
    public String getDocumentID(){return documentID;}
    public String getAuthorID(){return authorID;}
    public String getCuisineType(){return cuisineType;}
    public float getPrice(){return price;}


    // By the time we call this, the recipe should already have the
    // Name, Briefing, and Author/Author ID and descriptions pulled
    // so in this we only need to pull the sub-collections

    // Depending on whether we will use this for the full on search query for

    //Might not even be needed at all
    // Except for maybe the ratings and the comments on the recipe or sum
    public void getFullRecipe() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();



        DocumentReference docRef = db.collection("meals")
                .document(authorID)
                .collection("recipes")
                .document(documentID)
                .collection("ingredients")
                .document("ingredients list");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    // For reference, DocumentSnapshot#getData returns a Map<String, Object>, that's why this works.
                    // or should....
                    //ingredients = (document.getData());
                }
            }
        });

        DocumentReference docRef2 = db.collection("meals")
                .document(author)
                .collection("recipes")
                .document(documentID)
                .collection("instructions")
                .document("instructions list");
    }

}


