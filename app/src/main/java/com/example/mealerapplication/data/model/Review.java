package com.example.mealerapplication.data.model;

public class Review {
    String reviews;
    String firstName;
    String rating;


    public Review(String firstName, String rating, String reviews) {
        this.firstName = firstName;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Review() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }


}
