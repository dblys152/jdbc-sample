package com.ys.domain.user_coupon;

public enum UserCouponStatus {

    AVAILABLE("AVAILABLE"),
    USED("USED"),
    TERMINATED("TERMINATED"),
    EXPIRED("EXPIRED");

    private final String value;

    UserCouponStatus(String state) {
        this.value = state;
    }

    public String toString() {
        return this.value.toUpperCase();
    }
}
