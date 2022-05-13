package com.pmp.server.utils.enums;

public enum ERole {
  ROLE_ADMIN("ADMIN"),
  ROLE_TENANT("TENANT"),
  ROLE_LANDLORD("LANDLORD"),
  ;

  private String role;

  ERole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
