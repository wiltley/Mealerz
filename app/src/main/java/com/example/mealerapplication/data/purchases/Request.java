package com.example.mealerapplication.data.purchases;

import com.example.mealerapplication.data.model.Address;
import com.example.mealerapplication.data.model.Recipe;

public class Request {


    // A request will be stored under the cook's user file
    // and it will include the userID of the client who requested
    //  that userID can be used to retrieve the address stored in the user's file

    private Address address;

    // We'll only really need the name of it
    private Recipe r;
    private String mealName;



    public Request(){




    }

    public void approveRequest(){


    }

    public void declineRequest(){


        removeRequestFromDB();
    }



    public static Request retrieveRequest(){


        return new Request();
    }


    private void removeRequestFromDB(){

    }


}
