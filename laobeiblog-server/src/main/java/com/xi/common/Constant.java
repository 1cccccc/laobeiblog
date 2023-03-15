package com.xi.common;

public enum Constant {
    REDIS_USER_PREFIX("user:");
    private String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
