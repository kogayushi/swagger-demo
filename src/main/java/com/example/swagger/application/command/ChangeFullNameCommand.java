package com.example.swagger.application.command;

import java.util.Objects;

import lombok.Value;

@Value
public class ChangeFullNameCommand {
    private String id;
    private String firstName;
    private String lastName;
    
    public ChangeFullNameCommand(String id, String firstName, String lastName) {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(firstName, "firstName is required");
        Objects.requireNonNull(lastName, "lastName is required");
        
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
