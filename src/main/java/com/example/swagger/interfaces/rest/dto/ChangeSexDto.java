package com.example.swagger.interfaces.rest.dto;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class ChangeSexDto {
    @ApiModelProperty(example = "MALE", required = true, allowableValues = "MALE,FEMALE,UNKNOWN")
    @NotEmpty
    String sex;

}
