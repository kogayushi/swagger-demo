package com.example.swagger.interfaces.rest.dto;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class ChangeFullNameDto {
    @ApiModelProperty(example = "yushi", required = true)
    @NotEmpty
    String firstName;
    @ApiModelProperty(example = "koga", required = true)
    @NotEmpty
    String lastName;

}
