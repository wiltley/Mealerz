package com.example.mealerapplication.data;
import com.example.mealerapplication.data.model.Address;
import com.example.mealerapplication.data.model.CreditCard;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


// CLIENT SIDE GENERAL USER

public class User {

    private String username;
    private String email;
    private String fName;
    private String lName;
    Address address;
    CreditCard creditCard;
    private FirebaseUser currentUser;
    private DatabaseReference ref;
    private Role role;
    private String status;

    public enum Role{
        CLIENT(0),
        COOK(1),
        ADMIN(2);

        public final int role;

        Role(int role){
            this.role = role;
        }
        public int getRole(){
            return role;
        }
        public String getRoleString(){
            switch (role){
                case 0:
                    return "client";
                case 1:
                    return "cook";
                default:
                    return "admin";
            }
        }

        public Role getRoleFromString(String role){
            switch (role){
                case "client":
                    return CLIENT;
                case "cook":
                    return COOK;
                case "admin":
                    return ADMIN;
            }
            //Shouldn't get to this area since input should only be "client", "cook" or "admin"
            return null;
        }
    }

    public User(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User(){

    }

    public User(String email, String fName, String lName, FirebaseUser currentUser, Role role, String status) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.currentUser = currentUser;
        this.role = role;
        this.status = status;
    }

    public static void createUser(){

    }


    public void setCurrentUser(FirebaseUser user){
        currentUser = user;
        ref = FirebaseDatabase.getInstance().getReference();
        email = user.getEmail();

    }

    public String getUsername(){
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isClient(){
        return role == Role.CLIENT;
    }

    public boolean isCook(){
        return role == Role.COOK;
    }

    public boolean isAdmin(){
        return role == Role.ADMIN;
    }

    public Role getRole(){ return role; }

    public Map<String, Object> getUserMap(){
        //used to get mapped data for user to update Firebase

        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("email", email);
        data.put("fName", fName != null ? fName : "");
        data.put("lName", lName != null ? lName : "");
        data.put("address", address != null ? address.getAddressMap() : "");
        data.put("role", role.getRole());
        data.put("status", status);
        //Do we put credit cards in the DB? seems like a security risk but not sure
        // how serious we should take it for now

        return data;
    }



    public void handleMessageFromServer(){

    }

    /*public void permissable(Permission p){

    }*/


}
