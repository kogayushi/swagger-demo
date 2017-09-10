package com.example.swagger.domain.model.identity;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    
    private final UserRepository userRepository;
    
    public UserDescriptor authenticatedUser(String username, String password) {
        User user = userRepository.userFromUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user.userDescriptor();
        }
        throw new FailedAuthenticationException("bad credential for " + username );
    }

}
