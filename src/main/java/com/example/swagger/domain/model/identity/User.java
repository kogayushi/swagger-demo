package com.example.swagger.domain.model.identity;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User {
    
    private UUID id;
    private String username;
    private String password;
    private Person person;
    
    public User(UUID id, String username, String password, Person person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.person = person;
    }
    
    public UserDescriptor userDescriptor() {
        Sex sex = this.person.getSex();
        ContactInformation contactInformation = this.person.getContactInformation();
        return new UserDescriptor(
                this.id,
                this.person.getFirstName(),
                this.person.getLastName(),
                sex != null ? sex : Sex.UNKNOWN,
                contactInformation != null ? contactInformation.getTelephoneNumber().getTelephoneNumber() : null,
                contactInformation != null ? contactInformation.getEmailAddress().getEmailAddress() : null);
    }
}