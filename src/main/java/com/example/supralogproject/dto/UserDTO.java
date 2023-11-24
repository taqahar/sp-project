package com.example.supralogproject.dto;

import com.example.supralogproject.model.Gender;
import com.example.supralogproject.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    @NotNull(message = "First name is mandatory")
    private String firstName;

    @NotNull(message = "Last name is mandatory")
    private String lastName;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @NotNull(message = "birthDate is mandatory")
    private LocalDate birthDate;

    private AddressDTO address;

    public UserDTO() {
    }

    public User toUser() {
        return new User();
    }
}
