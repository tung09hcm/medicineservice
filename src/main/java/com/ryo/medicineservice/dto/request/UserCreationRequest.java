package com.ryo.medicineservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;
    @Size(min = 4, message = "LASTNAME_INVALID")
    String lastname;
    @Size(min = 4, message = "FIRSTNAME_INVALID")
    String firstname;
    @Size(min = 4, message = "PASSWORD_INVALID")
    String password;
    LocalDate dob;
}
