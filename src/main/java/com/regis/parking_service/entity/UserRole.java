package com.regis.parking_service.entity;

public enum UserRole {
  ADMIN(1, "ADMIN"),
  USER(2, "USER");

  private Integer id;
  private String role;

  UserRole(Integer id, String role) {
    this.id = id;
    this.role = role;
  }

  public Integer getId() {
    return this.id;
  }

  public String getRole() {
    return this.role;
  }

  public static Integer getIdByRole(String role) {
    for (UserRole userRole : values()) {
      if (userRole.role.equals(role)) {
        return userRole.getId();
      }
    }
    return null;
  }
}
