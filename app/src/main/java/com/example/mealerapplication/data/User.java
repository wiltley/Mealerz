package com.example.mealerapplication.data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


// CLIENT SIDE GENERAL USER

public abstract class User {

    private String username;
    private String ID;

    public User(){

    }

    public String getID(){

        return ID;
    }

    public String getUsername(){

        return username;

    }

    public void handleMessageFromServer(){

    }

    /*public void permissable(Permission p){

    }*/


}
