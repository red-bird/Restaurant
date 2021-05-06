package com.redbird.restaurant.models;

public enum Permission {
    PERMISSION_ADMIN("permission:admin"),
    PERMISSION_USER("permission:user");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
