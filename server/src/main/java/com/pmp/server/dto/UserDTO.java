package com.pmp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private boolean active;
  private String gender;
}
