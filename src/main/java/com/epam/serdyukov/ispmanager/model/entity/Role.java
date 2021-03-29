package com.epam.serdyukov.ispmanager.model.entity;

/**
 * Role entity class.
 *
 * @author Aleksey Serdyukov.
 */
public enum Role {
  ADMIN, CLIENT;

  public static Role getRole(User user) {
    int roleId = user.getRoleId();
    return Role.values()[--roleId];
  }

  public String getName() {
    return name().toLowerCase();
  }
}