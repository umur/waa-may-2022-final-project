package com.project.accessdenied.model;

import com.project.accessdenied.dto.UserDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserDto userDto;
}
