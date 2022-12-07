package com.example.mealerapplication.data.model;

import java.util.HashMap;
import java.util.Map;

public class MealRequest {

    String clientID;
    String cookID;
    String mealID;
    String documentID;

    String mealName;
    String clientName;



    String status;
    String cookName;

    String clientAddress;

    public MealRequest(String clientID, String cookID, String mealID, String mealName, String clientName, String status, String cookName, String clientAddress) {
        this.clientID = clientID;
        this.cookID = cookID;
        this.mealID = mealID;
        this.mealName = mealName;
        this.clientName = clientName;
        this.status = status;
        this.cookName = cookName;
        this.clientAddress = clientAddress;
    }

    public MealRequest() {
    }

    public String getMeal(){return this.mealID;}
    public String getClientID(){return this.clientID;}
    public String getCookID(){return this.cookID;}
    public String getMealName(){return this.mealName;}
    public String getClientName(){return this.clientName;}
    public String getDocumentID(){return this.documentID;}
    public String getCookName(){return cookName;}
    public String getStatus(){return status;}
    public String getClientAddress(){return clientAddress;}


    public void setMealName(String mealName){this.mealName = mealName;}
    public void setMeal(Recipe meal){this.mealID = mealID;}
    public void setClientID(String clientID){this.clientID = clientID;}
    public void setCookID(String cookID){this.cookID = cookID;}
    public void setClientName(String cookID){this.clientName = clientName;}
    public void setDocumentID(String documentID){this.documentID = documentID;}
    public void setCookName(String cookName){this.cookName = cookName;}
    public void setStatus(String status){this.status = status;}
    public void setClientAddress(String clientAddress){this.clientAddress = clientAddress;}

    public Map<String, Object> getMealRequestMap(){
        //Gives data in map form for storing in Firebase
        Map<String, Object> data = new HashMap<>();
        data.put("mealName", mealName);
        data.put("clientName", clientName);
        data.put("cookName", cookName);
        data.put("clientID", clientID);
        data.put("cookID", cookID);
        data.put("mealID", mealID);
        data.put("clientAddress", clientAddress);
        data.put("status", status);

        return data;
    }
}
