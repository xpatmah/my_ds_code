package com.my.ds.code.interview.designpatterns.solid.srp.correct.impl;

import com.my.ds.code.interview.designpatterns.solid.srp.correct.domain.SrpUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//A separate class for handling validation of User
public class SrpUserValidator {

	public boolean validateUser(SrpUser srpUser) {
		return isValidUser(srpUser);
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
    //check string for valid email address
    private boolean isValidEmail(String value) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); 
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}
