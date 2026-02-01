package com.my.ds.code.interview.designpatterns.solid.liskov.correct.impl;



import com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain.LiskovUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//A separate class for handling validation of User
public class LiskovUserValidator {

	public boolean validateUser(LiskovUser liskovUser) {
		return isValidUser(liskovUser);
	}
	

    //Validates the user object
    private boolean isValidUser(LiskovUser liskovUser) {
        if(!isPresent(liskovUser.getName())) {
            return false;
        }
        liskovUser.setName(liskovUser.getName().trim());

        if(!isValidAlphaNumeric(liskovUser.getName())) {
            return false;
        }
        if(liskovUser.getEmail() == null || liskovUser.getEmail().trim().isEmpty()) {
            return false;
        }
        liskovUser.setEmail(liskovUser.getEmail().trim());
        if(!isValidEmail(liskovUser.getEmail())) {
            return false;
        }
        return true;
    }
    
    //Simply checks if value is null or empty..
    private boolean isPresent(String value) {
        return value != null && !value.trim().isEmpty();
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
