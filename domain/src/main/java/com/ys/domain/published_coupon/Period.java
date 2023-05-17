package com.ys.domain.published_coupon;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class Period {

    @NotNull
    LocalDateTime startedAt;
    @NotNull
    LocalDateTime endedAt;

    private Period(LocalDateTime startedAt, LocalDateTime endedAt) {
        checkValidity(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    private void checkValidity(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작일시가 종료일시보다 이후 일 수 없습니다.");
        }
    }
}
