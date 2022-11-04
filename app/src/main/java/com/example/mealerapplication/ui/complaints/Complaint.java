package com.example.mealerapplication.ui.complaints;

public class Complaint {

    private String accuser;
    private String accused;
    private String message;


    public Complaint(){

        accuser = "";
        accused = "";
        message = "";
    }

    public Complaint(String accuser, String accused, String message){

        this.accuser = accuser;
        this.accused = accused;
        this.message = message;

    }

    public String getAccuser(){
        return accuser;
    }

    public String getAccused(){
        return accused;
    }


    public String getMessage(){
        return message;
    }



}
