package com.example.supralogproject.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    @NotNull(message = "Street is mandatory")
    private String street;
    @NotNull(message = "zipCode is mandatory")
    private String zipCode;
    @NotNull(message = "city is mandatory")
    private String city;
    @NotNull(message = "state is mandatory")
    private String state;

    public Address() {
    }
}
