package com.huy.identify_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

//@Getter
//@Setter

// auto generate getter and setter and constructor and toString and equals and hashcode methods
@Data

// auto generate no-args constructor
@NoArgsConstructor

// auto generate all-args constructor
@AllArgsConstructor

// auto generate builder pattern
@Builder

// auto generate field level access modifier
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserCreationRequest {
    @NotBlank(message = "Username is required")
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    String password;

    @NotBlank(message = "First name is required")
    String firstName;

    @NotBlank(message = "Last name is required")
    String lastName;

    @NotBlank(message = "Date of birth is required")
    LocalDate dob;
}
