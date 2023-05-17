package com.ys.domain.coupon;

import com.fasterxml.uuid.Generators;
import com.ys.common.SelfValidating;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon extends SelfValidating<Coupon> {

    @Valid @NotNull
    private CouponId id;
    @NotNull
    private CouponType couponType;
    @Valid @NotNull
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
        validateSelf();
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
