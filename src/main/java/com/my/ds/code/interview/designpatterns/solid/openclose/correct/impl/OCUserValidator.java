package com.my.ds.code.interview.designpatterns.solid.openclose.correct.impl;



import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//A separate class for handling validation of User
public class OCUserValidator {

	public boolean validateUser(OCUser OCUser) {
		return isValidUser(OCUser);
	}
	

    //Validates the user object
    private boolean isValidUser(OCUser OCUser) {
        if(!isPresent(OCUser.getName())) {
            return false;
        }
        OCUser.setName(OCUser.getName().trim());

        if(!isValidAlphaNumeric(OCUser.getName())) {
            return false;
        }
        if(OCUser.getEmail() == null || OCUser.getEmail().trim().length() == 0) {
            return false;
        }
        OCUser.setEmail(OCUser.getEmail().trim());
        if(!isValidEmail(OCUser.getEmail())) {
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
    //check string for valid email address
    private boolean isValidEmail(String value) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); 
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}
