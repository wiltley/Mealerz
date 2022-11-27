package com.example.mealerapplication.data.model;

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

}
