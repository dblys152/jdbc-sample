package com.ys.domain.user_coupon;

import com.ys.common.IdGenerator;
import com.ys.domain.coupon.Coupon;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCoupon {

    private UserCouponId id;
    private UserId userId;
    private Coupon coupon;
    private UserCouponStatus status;
    private UserCouponPeriod period;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long version;

    private UserCoupon(
            UserCouponId id,
            UserId userId,
            Coupon coupon,
            UserCouponStatus status,
            UserCouponPeriod period
    ) {
        this.id = id;
        this.userId = userId;
        this.coupon = coupon;
        this.status = status;
        this.period = period;
    }

    public static UserCoupon of(
            UserCouponId id,
            UserId userId,
            Coupon coupon,
            UserCouponStatus status,
            UserCouponPeriod period,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Long version
    ) {
        return new UserCoupon(
                id,
                userId,
                coupon,
                status,
                period,
                createdAt,
                modifiedAt,
                version
        );
    }

    public static UserCoupon create(UserId userId, Coupon coupon, UserCouponPeriod period) {
        UserCouponId id = UserCouponId.of(IdGenerator.generateType1UUID().toString());
        return new UserCoupon(id, userId, coupon, UserCouponStatus.AVAILABLE, period);
    }

    public void use() {
        if (!this.isAvailable()) {
            throw new IllegalStateException("AVAILABLE 상태에서만 사용 가능합니다.");
        }
        this.status = UserCouponStatus.USED;
    }

    public void terminate() {
        if (!this.isAvailable()) {
            throw new IllegalStateException("AVAILABLE 상태에서만 해지 가능합니다.");
        }
        this.status = UserCouponStatus.TERMINATED;
    }

    public void expire() {
        if (!this.isAvailable()) {
            throw new IllegalStateException("AVAILABLE 상태에서만 만료 가능합니다.");
        }
        this.status = UserCouponStatus.EXPIRED;
    }

    public boolean isAvailable() {
        return this.status == UserCouponStatus.AVAILABLE;
    }
}
