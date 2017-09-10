package com.example.swagger.application.command;

import java.util.Objects;

import lombok.Value;

@Value
public class ChangeTelephoneNumberCommand {
    private String id;
    private String telephoneNumber;
    
    public ChangeTelephoneNumberCommand(String id, String telephoneNumber) {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(telephoneNumber, "telephoneNumber is required");
        
        this.id = id;
        this.telephoneNumber = telephoneNumber;
    }
}
