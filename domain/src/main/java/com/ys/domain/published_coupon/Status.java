package com.ys.domain.published_coupon;

public enum Status {

    AVAILABLE("AVAILABLE"),
    USED("USED"),
    TERMINATED("TERMINATED"),
    EXPIRED("EXPIRED");

    private final String value;

    Status(String state) {
        this.value = state;
    }

    public String toString() {
        return this.value.toUpperCase();
    }
}
