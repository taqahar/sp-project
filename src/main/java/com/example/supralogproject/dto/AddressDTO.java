package com.example.supralogproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDTO {
    @NotNull(message = "Street is mandatory")
    private String street;
    @NotNull(message = "zipCode is mandatory")
    private String zipCode;
    @NotNull(message = "city is mandatory")
    private String city;
    @NotNull(message = "state is mandatory")
    private String state;
}
