package com.example.swagger.domain.model.identity;

import lombok.Value;

@Value
public class ContactInformation {
    private TelephoneNumber telephoneNumber;
    private EmailAddress emailAddress;
}
