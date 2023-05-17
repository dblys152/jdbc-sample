package com.ys.domain.coupon;

import com.ys.domain.fixture.SupportedCouponFixture;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void 쿠폰_생성_실패() {
        assertAll(
                () -> assertThatThrownBy(() -> Coupon.create(null, ANY_DISCOUNT_INFO, ANY_DESCRIPTION)).isInstanceOf(ConstraintViolationException.class),
                () -> assertThatThrownBy(() -> Coupon.create(ANY_COUPON_TYPE, null, ANY_DESCRIPTION)).isInstanceOf(ConstraintViolationException.class)
        );
    }
}