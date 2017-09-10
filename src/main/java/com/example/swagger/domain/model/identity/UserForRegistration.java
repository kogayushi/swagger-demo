package com.example.swagger.domain.model.identity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserForRegistration {
    
    private String username;
    private String password;
    private Person person;
    
    public UserForRegistration(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
    }
}
