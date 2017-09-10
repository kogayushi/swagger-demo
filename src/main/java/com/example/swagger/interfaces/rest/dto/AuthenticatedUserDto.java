package com.example.swagger.interfaces.rest.dto;

import java.util.UUID;

import lombok.Value;

@Value
public class AuthenticatedUserDto {
    private UUID id;
    private String firstName;
    private String lastname;
    private String sex;
    private String telephoneNumber;
    private String emailAddress;
}
