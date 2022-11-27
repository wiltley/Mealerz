package com.example.mealerapplication.data.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Recipe implements Serializable {

    private String recipeName;
    private String description;

    // We might wants instructions to be it's own hashmap similarly to
    // ingredients instead


    private String cookName;
    private String documentID;
    private String cookID;
    private String cuisineType;
    private String offered;

    // Probably going to have to parse it??? Idk
    private String price;


    // Should only be used when creating a new recipe
    public Recipe(){

    }

    // Should be used when pulling a recipe from the Firestore
    // Constructor could honestly also just take a hashmap or 2 and deconstruct them instead of having to pass everything
    public Recipe(String recipeName, String description,   String author,  String documentID, String cookID){
this.recipeName = recipeName;
        this.description = description;
        this.cookName = author;
        this.cookID = cookID;

        // If it's a new recipe this is supposed to be null
        this.documentID = documentID;

    }

    public Recipe(String recipeName, String description, String cookName, String documentID, String cookID, String cuisineType, String offered, String price) {
        this.recipeName = recipeName;
        this.description = description;
        this.cookName = cookName;
        this.documentID = documentID;
        this.cookID = cookID;
        this.cuisineType = cuisineType;
        this.offered = offered;
        this.price = price;
    }

    public void setDocumentID(String documentID){this.documentID = documentID;}
    public void setRecipeName(String recipeName){this.recipeName = recipeName;}
    public void setCookName(String cookName){this.cookName = cookName;}
    public void setCookID(String cookID){this.cookID = cookID;}
    public void setDescription(String description){this.description = description;}
    public void setCuisineType(String cuisineType){this.cuisineType = cuisineType;}
    public void setPrice(String price){this.price = price;}
    public void setOffered(String offered){this.offered = offered;}

    public String getRecipeName(){
        return recipeName;
    }
    public String getDescription(){
        return description;
    }
    public String getCookName(){return cookName;}
    public String getDocumentID(){return documentID;}
    public String getCookID(){return cookID;}
    public String getCuisineType(){return cuisineType;}
    public String getPrice(){return price;}
    public String getOffered(){return offered;}


    // By the time we call this, the recipe should already have the
    // Name, Briefing, and Author/Author ID and descriptions pulled
    // so in this we only need to pull the sub-collections

    // Depending on whether we will use this for the full on search query for

    //Might not even be needed at all
    // Except for maybe the ratings and the comments on the recipe or sum

    public static Recipe retrieveRecipe(String documentID){

       FirebaseFirestore db = FirebaseFirestore.getInstance();
       Recipe r = new Recipe();

       db.collection("meals")
               .document("offered")
               .collection("all")
               .document(documentID)
               .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                       DocumentSnapshot docRef = task.getResult();

                       r.setCookName(docRef.getString("cookName"));
                       r.setCookID(docRef.getString("cookID"));
                       r.setRecipeName(docRef.getString("name"));
                       r.setDescription(docRef.getString("description"));
                       r.setPrice(docRef.getString("price"));
                       r.setCuisineType(docRef.getString("cuisineType"));

                   }
               });



        return new Recipe();


    }

    public Map<String, Object> getRecipeMap(){
        //Gives data in map form for storing in Firebase
        Map<String, Object> data = new HashMap<>();
        data.put("recipeName", recipeName);
        data.put("description", description);
        data.put("cookName", cookName);
        data.put("cuisineType", cuisineType);
        data.put("cookID", cookID);
        data.put("offered", offered);
        data.put("price", price);

        return data;
    }

    public String toString(){
        return recipeName + " by " + cookName + " ($" + price + "): " + description + " \n Cuisine Type: " + cuisineType;
    }


}


