package com.ys.domain.coupon;

import com.ys.common.IdGenerator;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon {

    private String id;
    private CouponType couponType;
    private DiscountInfo discountInfo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private long version;

    private Coupon(String id, CouponType couponType, DiscountInfo discountInfo) {
        this.id = id;
        this.couponType = couponType;
        this.discountInfo = discountInfo;
    }

    public static Coupon of(
            String id,
            CouponType couponType,
            DiscountInfo discountInfo,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime deletedAt,
            long version
    ) {
        return new Coupon(
                id,
                couponType,
                discountInfo,
                createdAt,
                modifiedAt,
                deletedAt,
                version
        );
    }

    public static Coupon create(CouponType couponType, DiscountInfo discountInfo) {
        String id = IdGenerator.generateType1UUID().toString();
        return new Coupon(id, couponType, discountInfo);
    }
}
