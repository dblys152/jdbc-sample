package com.ys.domain.coupon;

import com.fasterxml.uuid.Generators;
import com.ys.common.IdGenerator;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon {

    private CouponId id;
    private CouponType couponType;
    private DiscountInfo discountInfo;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private long version;

    private Coupon(CouponId id, CouponType couponType, DiscountInfo discountInfo, String description) {
        this.id = id;
        this.couponType = couponType;
        this.discountInfo = discountInfo;
        this.description = description;
    }

    public static Coupon of(
            CouponId id,
            CouponType couponType,
            DiscountInfo discountInfo,
            String description,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime deletedAt,
            long version
    ) {
        return new Coupon(
                id,
                couponType,
                discountInfo,
                description,
                createdAt,
                modifiedAt,
                deletedAt,
                version
        );
    }

    public static Coupon create(CouponType couponType, DiscountInfo discountInfo, String description) {
        CouponId id = CouponId.of(Generators.timeBasedEpochGenerator().generate().toString());
        return new Coupon(id, couponType, discountInfo, description);
    }
}
