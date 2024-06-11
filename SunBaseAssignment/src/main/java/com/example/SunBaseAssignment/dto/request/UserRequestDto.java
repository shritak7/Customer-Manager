package com.example.SunBaseAssignment.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    String firstName;
    String lastName;
    String street;
    String address;
    String City;
    String state;
    String email;
    String phone;
}
