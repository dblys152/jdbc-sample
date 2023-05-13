package com.ys.adapter.fixture;

import com.ys.adapter.coupon.out.persistence.CouponEntity;
import com.ys.domain.coupon.CouponType;
import com.ys.domain.coupon.DiscountType;

import java.time.LocalDateTime;

public class SupportedCouponEntityFixture {

    protected static final LocalDateTime NOW = LocalDateTime.now();
    protected static final String ANY_COUPON_ID = "ANY_COUPON_ID";
    protected static final CouponType ANY_COUPON_TYPE = CouponType.PRODUCT_DISCOUNT;
    protected static final DiscountType ANY_DISCOUNT_TYPE = DiscountType.PERCENTAGE;
    protected static final Integer ANY_DISCOUNT_VALUE = 50;
    protected static final String ANY_DESCRIPTION = "ANY_DESCRIPTION";
    protected static CouponEntity ANY_COUPON_ENTITY = new CouponEntity(
            ANY_COUPON_ID, ANY_COUPON_TYPE,ANY_DISCOUNT_TYPE, ANY_DISCOUNT_VALUE, ANY_DESCRIPTION, NOW, NOW, null, 0L
    );
}
