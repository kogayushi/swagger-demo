package com.example.swagger.interfaces.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class ErrorResponseDto {
    @ApiModelProperty(position = 0, example = "404", required = true, notes = "Http Status code, always same with http header")
    private int status;
    @ApiModelProperty(position = 1, example = "Not Found", required = true, notes = "Reason frase for http status code")
    private String reason;
    @ApiModelProperty(position = 2, example = "Bad credential for {specified username}", required = false, notes = "show error message for developer, according to the cause.")
    private String message;
}
