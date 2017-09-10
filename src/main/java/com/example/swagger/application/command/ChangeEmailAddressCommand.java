package com.example.swagger.application.command;

import java.util.Objects;

import lombok.Value;

@Value
public class ChangeEmailAddressCommand {
    private String id;
    private String emailAddress;
    
    public ChangeEmailAddressCommand(String id, String emailAddress) {
        Objects.requireNonNull(id, "id is required");
        
        this.id = id;
        this.emailAddress = emailAddress;
    }
}
