package com.ys.domain.user_coupon;

import lombok.Value;

import java.time.LocalDateTime;

public class UserCouponPeriod {

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    private UserCouponPeriod(LocalDateTime startedAt, LocalDateTime endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static UserCouponPeriod of(LocalDateTime startedAt, LocalDateTime endedAt) {
        checkValidity(startedAt, endedAt);
        return UserCouponPeriod.of(startedAt, endedAt);
    }

    private static void checkValidity(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalStateException("시작일시가 종료일시보다 이후 일 수 없습니다.");
        }
    }
}
