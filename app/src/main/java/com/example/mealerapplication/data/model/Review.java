package com.example.mealerapplication.data.model;

public class Review {
    String reviews;
    String firstName;

    public Review(String firstName, double rating, String reviews) {
        this.firstName = firstName;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    double rating;


    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }


}
