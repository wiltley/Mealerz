package com.example.mealerapplication;

public class FoodInfo {
    private String foodName;
    private String foodDescription;
    private int foodImage;


    public FoodInfo(String foodName, String foodDescription, int foodImage) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public int getFoodImage() {
        return foodImage;
    }
}
