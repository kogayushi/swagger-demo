package com.example.swagger.domain.model.identity;

import java.util.UUID;

import lombok.Value;

@Value
public class UserDescriptor {
    
    private UUID id;
    private String firstName;
    private String lastname;
    private Sex sex;
    private String telephoneNumber;
    private String emailAddress;
}
