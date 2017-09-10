package com.example.swagger.interfaces.rest.dto;

import lombok.Value;

@Value
public class ErrorResponseDto {
    private int status;
    private String reason;
    private String message;
}
