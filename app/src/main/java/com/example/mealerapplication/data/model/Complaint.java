package com.example.mealerapplication.data.model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Complaint implements Serializable {

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


    public void removeFromDB(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("complaints").document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Successfully removed " + documentId);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    public Map<String, Object> getComplaintMap(){
        //Gives data in map form for storing in Firebase
        Map<String, Object> data = new HashMap<>();
        data.put("accuser", accuser);
        data.put("accused", accused);
        data.put("accusedUID", accused_UID);
        data.put("message", message);

        return data;
    }

    public String toString(){
        return "Complaint by " + accuser + " against " + accused + ": " + message;
    }


}
