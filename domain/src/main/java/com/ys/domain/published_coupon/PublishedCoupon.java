package com.ys.domain.published_coupon;

import com.fasterxml.uuid.Generators;
import com.ys.common.SelfValidating;
import com.ys.domain.coupon.CouponId;
import com.ys.refs.user.domain.UserId;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PublishedCoupon extends SelfValidating<PublishedCoupon> {

    @NotNull
    private String id;
    @Valid @NotNull
    private UserId userId;
    @Valid @NotNull
    private CouponId couponId;
    @NotNull
    private Status status;
    @Valid @NotNull
    private Period period;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long version;

    private PublishedCoupon(
            String id,
            UserId userId,
            CouponId couponId,
            Status status,
            Period period
    ) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.status = status;
        this.period = period;
        validateSelf();
    }

    public static PublishedCoupon of(
            String id,
            UserId userId,
            CouponId couponId,
            Status status,
            Period period,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Long version
    ) {
        return new PublishedCoupon(
                id,
                userId,
                couponId,
                status,
                period,
                createdAt,
                modifiedAt,
                version
        );
    }

    public static PublishedCoupon create(UserId userId, CouponId couponId, Period period) {
        String id = Generators.timeBasedEpochGenerator().generate().toString();
        return new PublishedCoupon(id, userId, couponId, Status.AVAILABLE, period);
    }

    public void use() {
        if (!this.isAvailable()) {
            throw new IllegalStateException("AVAILABLE 상태에서만 사용 가능합니다.");
        }
        this.status = Status.USED;
    }

    public void terminate() {
        if (!this.isAvailable()) {
            throw new IllegalStateException("AVAILABLE 상태에서만 해지 가능합니다.");
        }
        this.status = Status.TERMINATED;
    }

    public void expire() {
        if (!this.isAvailable()) {
            throw new IllegalStateException("AVAILABLE 상태에서만 만료 가능합니다.");
        }
        this.status = Status.EXPIRED;
    }

    public boolean isAvailable() {
        return this.status == Status.AVAILABLE;
    }
}
