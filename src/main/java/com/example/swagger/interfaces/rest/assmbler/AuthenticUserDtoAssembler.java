package com.example.swagger.interfaces.rest.assmbler;

import org.springframework.stereotype.Component;

import com.example.swagger.domain.model.identity.UserDescriptor;
import com.example.swagger.interfaces.rest.dto.AuthenticatedUserDto;

@Component
public class AuthenticUserDtoAssembler {

    public AuthenticatedUserDto toDto(UserDescriptor userDescriptor) {
        return new AuthenticatedUserDto(
                userDescriptor.getId(),
                userDescriptor.getFirstName(),
                userDescriptor.getLastname(),
                userDescriptor.getSex().name(),
                userDescriptor.getTelephoneNumber(),
                userDescriptor.getEmailAddress()
                );
        
    }
}
