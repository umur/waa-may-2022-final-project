package com.propertymanagement.server.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PropertyType {
    HOUSE, APARTMENT;

    @JsonCreator
    public static PropertyType create(String value) {
        return PropertyType.valueOf(value.toUpperCase());
    }
}
