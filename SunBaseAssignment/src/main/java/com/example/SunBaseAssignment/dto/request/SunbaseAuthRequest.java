package com.example.SunBaseAssignment.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SunbaseAuthRequest {
    private String login_id;
    private String password;
}
