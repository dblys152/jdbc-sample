package com.ys.domain.coupon;

public enum DiscountType {

    PERCENTAGE("PERCENTAGE"),
    AMOUNT("AMOUNT");

    private final String value;

    DiscountType(String state) {
        this.value = state;
    }

    public String toString() {
        return this.value.toUpperCase();
    }
}
