package com.example.mealerapplication.data.model;

public class CreditCard {
    private double number;
    private int cvv;
    private String expiryDate;
    private String cardHoldername;
    private Address billingAddress;

    public CreditCard(double number, int cvv, String expiryDate, String cardHoldername, Address billingAddress) {
        this.number = number;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHoldername = cardHoldername;
        this.billingAddress = billingAddress;
    }
}
