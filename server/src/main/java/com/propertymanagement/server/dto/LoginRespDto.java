package com.propertymanagement.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRespDto {
    private String accessToken;
}
