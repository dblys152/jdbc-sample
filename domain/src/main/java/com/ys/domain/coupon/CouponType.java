package com.ys.domain.coupon;

public enum CouponType {

    PRODUCT_DISCOUNT("PRODUCT_DISCOUNT");

    private final String value;

    CouponType(String state) {
        this.value = state;
    }

    public String toString() {
        return this.value.toUpperCase();
    }
}
