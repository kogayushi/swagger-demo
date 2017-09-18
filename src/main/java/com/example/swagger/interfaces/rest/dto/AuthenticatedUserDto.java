package com.example.swagger.interfaces.rest.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class AuthenticatedUserDto {
    @ApiModelProperty(position = 0, example = "A9C3ED4F-E492-461E-9D60-41EA8983173C", required = true)
    private UUID id;
    @ApiModelProperty(position = 1, example = "yushi", required = true)
    private String firstName;
    @ApiModelProperty(position = 2, example = "koga", required = true)
    private String lastname;
    @ApiModelProperty(position = 3, example = "MALE", required = true)
    private String sex;
    @ApiModelProperty(position = 4, example = "090-1234-5678", required = false)
    private String telephoneNumber;
    @ApiModelProperty(position = 5, example = "yushi.koga@is-tech.co.jp", required = false)
    private String emailAddress;
}
