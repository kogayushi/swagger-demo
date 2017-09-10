package com.example.swagger.application.command;

import java.util.Objects;

import lombok.Value;

@Value
public class RegisterUserCommand {
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String telephoneNumber;
    private String emailAddress;

    public RegisterUserCommand(
            String username, 
            String password,
            String firstName,
            String lastName,
            String sex,
            String telephoneNumber,
            String emailAddress) {
        Objects.requireNonNull(username, "username is required");
        Objects.requireNonNull(password, "password is required");
        Objects.requireNonNull(firstName, "firstName is required");
        Objects.requireNonNull(lastName, "lastName is required");

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex =  sex;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }
}
