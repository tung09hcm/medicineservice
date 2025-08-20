package com.ryo.medicineservice.entity;

import jakarta.persistence.*;
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
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255)")
    String username;
    @Column(name = "firstname", columnDefinition = "VARCHAR(255)")
    String firstname;
    @Column(name = "lastname",  columnDefinition = "VARCHAR(255)")
    String lastname;
    String password;
    String avatarLink;
    LocalDate dob;
}
