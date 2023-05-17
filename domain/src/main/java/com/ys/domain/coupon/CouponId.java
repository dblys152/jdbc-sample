package com.ys.domain.coupon;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class CouponId {

    @NotNull
    String id;
}
