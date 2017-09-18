package com.example.swagger.interfaces.rest.dto;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class ChangeEmailAddressDto {

    @ApiModelProperty(example = "yushi.koga@is-tech.co.jp", required = true)
    @NotEmpty
    String emailAddress;
}
