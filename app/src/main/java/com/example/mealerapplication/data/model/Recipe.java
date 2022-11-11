package com.example.mealerapplication.data.model;

import java.util.HashMap;

public class Recipe {

    private String recipeName;
    private String description;
    private HashMap<String, String> ingredients;
    private String instructions;
    private String author;
    private String briefing;
    private String documentID;
    private String authorID;



    public Recipe(String recipeName, String description, String instructions, HashMap<String, String> ingredients, String author, String briefing, String documentID, String authorID){

        this.recipeName = recipeName;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.author = author;
        this.briefing = briefing;
        this.authorID = authorID;

        // If it's a new recipe this is supposed to be null
        this.documentID = documentID;

    }

    public void setDocumentID(String id){
         documentID = id;

    }

    public String getRecipeName(){
        return recipeName;
    }
    public String getDescription(){
        return description;
    }
    public String getInstructions(){ return instructions; }
    public HashMap<String, String> getIngredients(){ return ingredients; }
    public String getAuthor(){return author;}
    public String getBriefing(){return briefing;}
    public String getDocumentID(){return documentID;}
    public String getAuthorID(){return authorID;}

}


