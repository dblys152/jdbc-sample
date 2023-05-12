package com.ys.domain.coupon;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountInfoTest {

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