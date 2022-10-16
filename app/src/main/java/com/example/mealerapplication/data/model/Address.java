package com.example.mealerapplication.data.model;

import java.util.Objects;

public class Address {
    private String streetName;
    private int houseNumber;
    private String city;
    private String country;
    private String postalCode;

    public Address(int houseNumber, String streetName, String city, String country, String postalCode){
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String toString(){
        return houseNumber + " " + streetName + ", " + city + ", " + country + " " + postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber && Objects.equals(streetName, address.streetName) && Objects.equals(city, address.city) && Objects.equals(country, address.country) && Objects.equals(postalCode, address.postalCode);
    }
}
