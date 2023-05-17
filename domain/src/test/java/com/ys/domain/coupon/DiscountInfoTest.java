package com.ys.domain.coupon;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class DiscountInfoTest {

    private static final DiscountType ANY_DISCOUNT_TYPE = DiscountType.PERCENTAGE;
    private static final Integer ANY_DISCOUNT_VALUE = 50;

    @Test
    void 할인_정보_생성() {
        DiscountInfo actual = DiscountInfo.of(ANY_DISCOUNT_TYPE, ANY_DISCOUNT_VALUE);
        assertThat(actual).isNotNull();
    }

    @Test
    void 할인_정보_생성_실패() {
        assertAll(
                () -> assertThatThrownBy(() -> DiscountInfo.of(null, ANY_DISCOUNT_VALUE)).isInstanceOf(ConstraintViolationException.class),
                () -> assertThatThrownBy(() -> DiscountInfo.of(ANY_DISCOUNT_TYPE, null)).isInstanceOf(ConstraintViolationException.class)
        );
    }

    @Test
    void 퍼센테이지_할인_계산() {
        DiscountInfo discountInfo = DiscountInfo.of(DiscountType.PERCENTAGE, 50);
        int originalPrice = 350000;

        int actual = discountInfo.calculateDiscountableAmount(originalPrice);

        assertThat(actual).isEqualTo(175000);
    }

    @Test
    void 금액_할인_계산() {
        DiscountInfo discountInfo = DiscountInfo.of(DiscountType.AMOUNT, 50000);
        int originalPrice = 350000;

        int actual = discountInfo.calculateDiscountableAmount(originalPrice);

        assertThat(actual).isEqualTo(300000);
    }

    @Test
    void 계산된_할인_금액이_0원_이하이면_0원을_반환한다() {
        DiscountInfo discountInfo = DiscountInfo.of(DiscountType.PERCENTAGE, 100);
        int originalPrice = 350000;

        int actual = discountInfo.calculateDiscountableAmount(originalPrice);

        assertThat(actual).isEqualTo(0);
    }
}