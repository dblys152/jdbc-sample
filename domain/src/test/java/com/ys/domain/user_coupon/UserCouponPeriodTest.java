package com.ys.domain.user_coupon;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class UserCouponPeriodTest {

    private static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    void 유저_쿠폰_기간을_생성한다() {
        LocalDateTime startedAt = NOW;
        LocalDateTime endedAt = NOW.plusDays(5);

        UserCouponPeriod actual = UserCouponPeriod.of(startedAt, endedAt);

        assertAll(
                () -> assertThat(actual).isInstanceOf(UserCouponPeriod.class),
                () -> assertThat(actual.getStartedAt()).isNotNull(),
                () -> assertThat(actual.getEndedAt()).isNotNull()
        );
    }

    @Test
    void 시작일시가_종료일시_이후_이면_에러를_반환한다() {
        LocalDateTime startedAt = NOW;
        LocalDateTime endedAt = NOW.minusDays(1);

        assertThatThrownBy(() -> UserCouponPeriod.of(startedAt, endedAt)).isInstanceOf(IllegalArgumentException.class);
    }
}