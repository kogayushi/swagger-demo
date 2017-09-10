package com.example.swagger.application.command;

import java.util.Objects;

import lombok.Value;

@Value
public class ChangeSexCommand {
    private String id;
    private String sex;
    
    public ChangeSexCommand(String id, String sex) {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(sex, "sex is required");
        
        this.id = id;
        this.sex = sex;
    }
}
