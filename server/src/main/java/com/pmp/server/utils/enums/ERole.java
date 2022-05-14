package com.pmp.server.utils.enums;

public enum ERole {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_TENANT("ROLE_TENANT"),
  ROLE_LANDLORD("ROLE_LANDLORD"),
  ;

  private String role;

  ERole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
