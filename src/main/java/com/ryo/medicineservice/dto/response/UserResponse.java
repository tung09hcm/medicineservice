package com.ryo.medicineservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryo.medicineservice.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    String id;
    String username;
    String firstname;
    String lastname;
    String avatarLink;
    String email;
    LocalDate dob;
    Set<Role> roles;
}
