package com.example.supralogproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Getter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    @NotNull(message = "Last name is mandatory")
    private String lastName;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @Field("birth_date")
    @NotNull(message = "birthDate is mandatory")
    private LocalDate birthDate;

    @NotNull(message = "Address is mandatory")
    private Address address;

    public User() {
    }

    public User(String firstName, String lastName, String email, Gender gender, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public User(String firstName, String lastName, String email, Gender gender, LocalDate birthDate, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
    }
}
