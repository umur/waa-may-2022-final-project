package com.propertymanagement.server.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN, LANDLORD, TENANT;

    @JsonCreator
    public static Role create(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}

