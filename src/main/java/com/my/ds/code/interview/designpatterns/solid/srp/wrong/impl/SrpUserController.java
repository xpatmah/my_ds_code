package com.my.ds.code.interview.designpatterns.solid.srp.wrong.impl;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ds.code.interview.designpatterns.solid.srp.wrong.domain.SrpStore;
import com.my.ds.code.interview.designpatterns.solid.srp.wrong.domain.SrpUser;

//Handles incoming JSON requests that work on User resource/entity
public class SrpUserController {
	//Store used by controller
    private SrpStore store = new SrpStore();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        SrpUser srpUser = mapper.readValue(userJson, SrpUser.class);

        if(!isValidUser(srpUser)) {
            return "ERROR";
        }

        store.store(srpUser);
        
        return "SUCCESS";
    } 

    //Validates the user object
    private boolean isValidUser(SrpUser srpUser) {
        if(!isPresent(srpUser.getName())) {
            return false;
        }
        srpUser.setName(srpUser.getName().trim());

        if(!isValidAlphaNumeric(srpUser.getName())) {
            return false;
        }
        if(srpUser.getEmail() == null || srpUser.getEmail().trim().length() == 0) {
            return false;
        }
        srpUser.setEmail(srpUser.getEmail().trim());
        if(!isValidEmail(srpUser.getEmail())) {
            return false;
        }
        return true;
    }
    
    //Simply checks if value is null or empty..
    private boolean isPresent(String value) {
        return value != null && value.trim().length() > 0;
    }
    //check string for special characters
    private boolean isValidAlphaNumeric(String value) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]"); 
        Matcher matcher = pattern.matcher(value);
        return !matcher.find();
    }
    //check string for valid email address - this is not for prod. 
    //Just for demo. This fails for lots of valid emails.
    private boolean isValidEmail(String value) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); 
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}