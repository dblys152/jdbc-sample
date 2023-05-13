package com.ys.domain.published_coupon;

import com.ys.domain.fixture.SupportedCouponFixture;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ys.domain.published_coupon.Status.*;
import static com.ys.domain.published_coupon.Status.EXPIRED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PublishedCouponTest extends SupportedCouponFixture {

    PublishedCoupon publishedCoupon;

    @BeforeEach
    void setUp() {
        publishedCoupon = PublishedCoupon.of(
                "ANY_ID", ANY_USER_ID, ANY_COUPON_ID, AVAILABLE, ANY_PERIOD, NOW, NOW, 0L
        );
    }

    @Test
    void 발행쿠폰을_생성하면_AVAILABLE_상태로_생성된다() {
        PublishedCoupon actual = PublishedCoupon.create(ANY_USER_ID, ANY_COUPON_ID, ANY_PERIOD);
        assertAll(
                () -> AssertionsForClassTypes.assertThat(actual).isNotNull(),
                () -> AssertionsForClassTypes.assertThat(actual.getId()).isNotNull(),
                () -> AssertionsForClassTypes.assertThat(actual.getUserId()).isNotNull(),
                () -> AssertionsForClassTypes.assertThat(actual.getCouponId()).isNotNull(),
                () -> AssertionsForClassTypes.assertThat(actual.getStatus()).isEqualTo(AVAILABLE),
                () -> AssertionsForClassTypes.assertThat(actual.getPeriod()).isNotNull()
        );
    }

    @Test
    void 발행쿠폰_사용() {
        publishedCoupon.use();
        assertThat(publishedCoupon.getStatus()).isEqualTo(USED);
    }

    @Test
    void 발행쿠폰은_AVAILABLE_상태여야만_사용_가능하다() {
        publishedCoupon.use();
        assertThatThrownBy(() -> publishedCoupon.terminate()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 발행쿠폰_해지() {
        publishedCoupon.terminate();
        assertThat(publishedCoupon.getStatus()).isEqualTo(TERMINATED);
    }

    @Test
    void 발행쿠폰은_AVAILABLE_상태여야만_해지_가능하다() {
        publishedCoupon.use();
        assertThatThrownBy(() -> publishedCoupon.terminate()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 발행쿠폰_만료() {
        publishedCoupon.expire();
        assertThat(publishedCoupon.getStatus()).isEqualTo(EXPIRED);
    }

    @Test
    void 발행쿠폰은_AVAILABLE_상태여야만_만료_가능하다() {
        publishedCoupon.use();
        assertThatThrownBy(() -> publishedCoupon.expire()).isInstanceOf(IllegalStateException.class);
    }
}