package com.ys.domain.published_coupon;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    private static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    void 발행쿠폰_기간을_생성한다() {
        LocalDateTime startedAt = NOW;
        LocalDateTime endedAt = NOW.plusDays(5);

        Period actual = Period.of(startedAt, endedAt);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getStartedAt()).isNotNull(),
                () -> assertThat(actual.getEndedAt()).isNotNull()
        );
    }

    @Test
    void 시작일시가_종료일시_이후_이면_에러를_반환한다() {
        LocalDateTime startedAt = NOW;
        LocalDateTime endedAt = NOW.minusDays(1);

        assertThatThrownBy(() -> Period.of(startedAt, endedAt)).isInstanceOf(IllegalArgumentException.class);
    }
}