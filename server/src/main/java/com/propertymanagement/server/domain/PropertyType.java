package com.propertymanagement.server.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PropertyType {
    HOUSE, APARTMENT;

    @JsonCreator
    public static Role create(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}
