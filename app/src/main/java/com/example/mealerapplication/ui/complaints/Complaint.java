package com.example.mealerapplication.ui.complaints;

public class Complaint {

    private String accuser;
    private String accused;
    private String message;
    private String documentId;
    private String accused_UID;


    public Complaint(){

        accuser = "";
        accused = "";
        message = "";
        documentId = "";

        accused_UID = "";
    }

    public Complaint(String accuser, String accused, String message, String documentId, String accused_UID){

        this.accuser = accuser;
        this.accused = accused;
        this.message = message;
        this.documentId = documentId;
        this.accused_UID = accused_UID;

    }


    public String getAccused_UID(){ return accused_UID;}

    public String getAccuser(){
        return accuser;
    }

    public String getAccused(){
        return accused;
    }

    public String getDocumentID(){return documentId;}


    public String getMessage(){
        return message;
    }

    public void banAccused(){



    }


}
