package com.ys.domain.published_coupon;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Period {

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    private Period(LocalDateTime startedAt, LocalDateTime endedAt) {
        checkValidity(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static Period of(LocalDateTime startedAt, LocalDateTime endedAt) {
        return new Period(startedAt, endedAt);
    }

    private void checkValidity(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작일시가 종료일시보다 이후 일 수 없습니다.");
        }
    }
}
