package com.pmp.server.utils.enums;

public enum ECurrency {
    USD("USD"),
        ;

    private String value;

    ECurrency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
