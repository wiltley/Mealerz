package com.example.mealerapplication.data.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Address {
    private String streetName;
    private int houseNumber;
    private String city;
    private String country;
    private String province;
    private String postalCode;

    public Address(int houseNumber, String streetName, String city, String province, String country, String postalCode){
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
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

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

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
        return houseNumber + " " + streetName + ", " + city + ", " + province + ", " + country + ", " + postalCode;
    }

    public Map<String, Object> getAddressMap(){
        //Gives data in map form for storing in Firebase
        Map<String, Object> data = new HashMap<>();
        data.put("houseNumber", houseNumber);
        data.put("streetName", streetName);
        data.put("city", city);
        data.put("province", province);
        data.put("country", country != null ? country : "");
        data.put("postalCode", postalCode != null ? postalCode : "");

        return data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber && Objects.equals(streetName, address.streetName)
                && Objects.equals(city, address.city) && Objects.equals(province, address.province) && Objects.equals(country, address.country) && Objects.equals(postalCode, address.postalCode);
    }
}
