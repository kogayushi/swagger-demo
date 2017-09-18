package com.example.swagger.interfaces.rest.dto;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class ChangeTelephoneNumberDto {
    @ApiModelProperty(example = "090-1234-5678", required = true, notes = "specify Tell Number as format of '^0\\\\d{1,4}-\\\\d{1,4}-\\\\d{4}$'")
    @NotEmpty
    String telephoneNumber;

}
