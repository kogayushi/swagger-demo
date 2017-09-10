package com.example.swagger.domain.model.identity;

import java.util.regex.Pattern;

import lombok.Value;

@Value
public class TelephoneNumber {
    private String telephoneNumber; 

    public TelephoneNumber(String telephoneNumber) {
        if(Pattern.matches("^0\\d{1,4}-\\d{1,4}-\\d{4}$", telephoneNumber) == false) {
            throw new IllegalArgumentException("It's not valid telephone number.");
        }
        this.telephoneNumber = telephoneNumber;
    }
}
