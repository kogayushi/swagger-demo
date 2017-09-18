package com.example.swagger.interfaces.rest.dto;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {
    @ApiModelProperty(position = 0, example = "yushi.koga", required = true)
    @NotEmpty
    private String username;
    @ApiModelProperty(position = 1, example = "passw0rd", required = true)
    @NotEmpty
    private String password;
    @ApiModelProperty(position = 2, example = "yushi", required = true)
    @NotEmpty
    private String firstName;
    @ApiModelProperty(position = 3, example = "koga", required = true)
    @NotEmpty
    private String lastName;
    @ApiModelProperty(position = 4, example = "MALE", required = true, allowableValues = "MALE,FEMALE,UNKWON")
    private String sex;
    @ApiModelProperty(position = 5, example = "090-1234-5678", required = true)
    private String telephoneNumber;
    @ApiModelProperty(position = 6, example = "yushi.koga@is-tech.co.jp", required = true)
    private String emailAddress;
}
