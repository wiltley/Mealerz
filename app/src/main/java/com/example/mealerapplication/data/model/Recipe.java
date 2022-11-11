package com.example.mealerapplication.data.model;

import java.util.HashMap;

public class Recipe {

    private String recipeName;
    private String description;
    private HashMap<String, String> ingredients;
    private String instructions;



    public Recipe(String recipeName, String description, String instructions, HashMap<String, String> ingredients){

        this.recipeName = recipeName;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;

    }


    public String getRecipeName(){
        return recipeName;
    }
    public String getDescription(){
        return description;
    }
    public String getInstructions(){ return instructions; }
    public HashMap<String, String> getIngredients(){ return ingredients; }

}


