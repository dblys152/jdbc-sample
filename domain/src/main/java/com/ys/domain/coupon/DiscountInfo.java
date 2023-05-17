package com.ys.domain.coupon;

import com.ys.common.SelfValidating;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class DiscountInfo extends SelfValidating<DiscountInfo> {

    @NotNull
    DiscountType discountType;
    @NotNull
    Integer discountValue;

    private DiscountInfo(DiscountType discountType, Integer discountValue) {
        this.discountType = discountType;
        this.discountValue = discountValue;
        validateSelf();
    }

    public Integer calculateDiscountableAmount(Integer price) {
        BigDecimal decimalDiscountValue = BigDecimal.valueOf(this.discountValue);
        BigDecimal decimalPrice = BigDecimal.valueOf(price);

        BigDecimal discountedAmount;
        BigDecimal hundred = BigDecimal.valueOf(100);

        if (discountType == DiscountType.PERCENTAGE) {
            BigDecimal discountPercent = decimalDiscountValue.divide(hundred, 2, BigDecimal.ROUND_HALF_UP);
            discountedAmount = decimalPrice.subtract(decimalPrice.multiply(discountPercent));
        } else {
            discountedAmount = decimalPrice.subtract(decimalDiscountValue);
        }

        return isGreaterThanZero(discountedAmount) ? discountedAmount.intValue() : 0;
    }

    private boolean isGreaterThanZero(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }
}

