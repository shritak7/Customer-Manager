package com.example.SunBaseAssignment.models;

import com.example.SunBaseAssignment.enums.City;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;

    String street;

    String address;

    String city;

    String state;

    @Column(nullable = false)
    String email;
    @Column(nullable = false,unique = true)
    String phone;
}
