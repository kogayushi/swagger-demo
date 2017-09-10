package com.example.swagger.domain.model.identity;

import java.util.regex.Pattern;

import lombok.Value;

@Value
public class EmailAddress {

    private String emailAddress;

    public EmailAddress(String emailAddress) {
        if (Pattern.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", emailAddress) == false) {
            throw new IllegalArgumentException("it's not valid email address.");
        }
        this.emailAddress = emailAddress;
    }
}
