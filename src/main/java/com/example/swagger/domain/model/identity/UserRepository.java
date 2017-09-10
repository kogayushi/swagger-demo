package com.example.swagger.domain.model.identity;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    
    User userFromUsername(String username);
    
    User userFromId(UUID id);
    
    List<User> findAll();
    
    User add(UserForRegistration userForRegistration);
    
    void update(User user);
    
    void remove(UUID id);
    
    void updateFullName(UUID id, String firstName, String lastName);
    
    void updateSex(UUID id, Sex sex);
    
    void updateTelephoneNumber(UUID id, TelephoneNumber telephonenumber);
    
    void updateEmailAddress(UUID id, EmailAddress emailAddress);
}
