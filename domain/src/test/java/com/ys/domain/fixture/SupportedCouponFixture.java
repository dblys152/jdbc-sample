package com.ys.domain.fixture;

import com.ys.domain.coupon.*;
import com.ys.domain.published_coupon.Period;
import com.ys.refs.user.domain.UserId;

import java.time.LocalDateTime;

public class SupportedCouponFixture {

    protected static final LocalDateTime NOW = LocalDateTime.now();
    protected static final CouponId ANY_COUPON_ID = CouponId.of("ANY_COUPON_ID");

    protected static final CouponType ANY_COUPON_TYPE = CouponType.PRODUCT_DISCOUNT;
    protected static final DiscountInfo ANY_DISCOUNT_INFO = DiscountInfo.of(DiscountType.PERCENTAGE, 50);
    protected static final String ANY_DESCRIPTION = "ANY_DESCRIPTION";
    protected static final Coupon ANY_COUPON = Coupon.of(
            ANY_COUPON_ID, ANY_COUPON_TYPE, ANY_DISCOUNT_INFO, ANY_DESCRIPTION, NOW, NOW, null, 0L
    );

    protected static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    protected static final Period ANY_PERIOD = Period.of(NOW, NOW.plusDays(5));
}
