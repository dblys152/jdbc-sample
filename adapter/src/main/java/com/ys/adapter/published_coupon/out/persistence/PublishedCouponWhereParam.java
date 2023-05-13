package com.ys.adapter.published_coupon.out.persistence;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PublishedCouponWhereParam {

    String userId;
    String status;

    @Builder
    public PublishedCouponWhereParam(
            String userId,
            String status
    ) {
        this.userId = userId;
        this.status = status;
    }
}
