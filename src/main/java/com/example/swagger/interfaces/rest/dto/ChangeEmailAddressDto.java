package com.example.swagger.interfaces.rest.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Value;

@Value
public class ChangeEmailAddressDto {

    @NotEmpty
    String emailAddress;
}
