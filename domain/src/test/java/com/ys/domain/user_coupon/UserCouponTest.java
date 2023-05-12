package com.ys.domain.user_coupon;

import com.ys.domain.fixture.SupportedCouponFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ys.domain.user_coupon.UserCouponStatus.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserCouponTest extends SupportedCouponFixture {

    private static final UserId ANY_USER_ID = UserId.of("ANY_USER_ID");
    private static final UserCouponPeriod ANY_PERIOD = UserCouponPeriod.of(NOW, NOW.plusDays(5));

    UserCoupon availableUserCoupon;

    @BeforeEach
    void setUp() {
        availableUserCoupon = UserCoupon.of(
                UserCouponId.of("ANY_ID"), ANY_USER_ID, ANY_COUPON, AVAILABLE, ANY_PERIOD, NOW, NOW, 0L);
    }

    @Test
    void 유저_쿠폰_생성_시_AVAILABLE_상태로_생성된다() {
        UserCoupon actual = UserCoupon.create(ANY_USER_ID, ANY_COUPON, ANY_PERIOD);
        System.out.println(actual.getId().getId());
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getUserId()).isNotNull(),
                () -> assertThat(actual.getCoupon()).isNotNull(),
                () -> assertThat(actual.getStatus()).isEqualTo(AVAILABLE),
                () -> assertThat(actual.getPeriod()).isNotNull()
        );
    }

    @Test
    void 쿠폰_사용() {
        availableUserCoupon.use();
        assertThat(availableUserCoupon.getStatus()).isEqualTo(USED);
    }

    @Test
    void 쿠폰은_AVAILABLE_상태여야만_사용_가능하다() {
        availableUserCoupon.use();
        assertThatThrownBy(() -> availableUserCoupon.terminate()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 쿠폰_해지() {
        availableUserCoupon.terminate();
        assertThat(availableUserCoupon.getStatus()).isEqualTo(TERMINATED);
    }

    @Test
    void 쿠폰은_AVAILABLE_상태여야만_해지_가능하다() {
        availableUserCoupon.use();
        assertThatThrownBy(() -> availableUserCoupon.terminate()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 쿠폰_만료() {
        availableUserCoupon.expire();
        assertThat(availableUserCoupon.getStatus()).isEqualTo(EXPIRED);
    }

    @Test
    void 쿠폰은_AVAILABLE_상태여야만_만료_가능하다() {
        availableUserCoupon.use();
        assertThatThrownBy(() -> availableUserCoupon.expire()).isInstanceOf(IllegalStateException.class);
    }
}