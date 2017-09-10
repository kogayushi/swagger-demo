package com.example.swagger.application.command;

import lombok.Value;

@Value
public class AuthenticateUserCommand {
    private String username;
    private String password;
}
