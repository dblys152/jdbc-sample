package com.ys.adapter.published_coupon.out.persistence;

import com.ys.domain.coupon.CouponId;
import com.ys.domain.published_coupon.PublishedCoupon;
import com.ys.domain.published_coupon.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("published_coupons")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Getter
@Slf4j
public class PublishedCouponEntity {

    @Id
    private String id;
    private String userId;
    private String couponId;
    private Status status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @Version
    private Long version;

    public static PublishedCouponEntity fromDomain(PublishedCoupon publishedCoupon) {
        Period period = publishedCoupon.getPeriod();
        return new PublishedCouponEntity(
                publishedCoupon.getId(),
                publishedCoupon.getUserId().getId(),
                publishedCoupon.getCouponId().getId(),
                publishedCoupon.getStatus(),
                period.getStartedAt(),
                period.getEndedAt(),
                publishedCoupon.getCreatedAt(),
                publishedCoupon.getModifiedAt(),
                publishedCoupon.getVersion()
        );
    }

    public PublishedCoupon toDomain() {
        return PublishedCoupon.of(
                this.id,
                UserId.of(this.userId),
                CouponId.of(this.couponId),
                this.status,
                Period.of(this.startedAt, this.endedAt),
                this.createdAt,
                this.modifiedAt,
                this.version
        );
    }
}
