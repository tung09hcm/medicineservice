package com.ryo.medicineservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    String id;
    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;
    @Column(name = "firstname", columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String firstname;
    @Column(name = "lastname",  columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String lastname;
    String password;
    String avatarLink;
    LocalDate dob;
}
