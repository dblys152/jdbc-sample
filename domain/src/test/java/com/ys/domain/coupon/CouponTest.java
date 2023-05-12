package com.ys.domain.coupon;

import com.ys.domain.fixture.SupportedCouponFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CouponTest extends SupportedCouponFixture {

    @Test
    void 쿠폰_생성() {
        Coupon actual = Coupon.create(ANY_COUPON_TYPE, ANY_DISCOUNT_INFO, ANY_DESCRIPTION);
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getCouponType()).isNotNull(),
                () -> assertThat(actual.getDiscountInfo()).isNotNull()
        );
    }

}