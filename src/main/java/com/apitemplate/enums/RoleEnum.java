package com.apitemplate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleEnum {
    ADMIN,
    USER;

    @JsonCreator
    public static RoleEnum fromString(String value) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("O valor da role deve ser um dos seguintes: ADMIN, USER.");
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
