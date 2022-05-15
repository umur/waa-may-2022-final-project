

package com.pmp.server.dto;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewPasswordDTO {
  private String password;
  private String token;
}
