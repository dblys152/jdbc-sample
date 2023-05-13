package com.ys.adapter.published_coupon.out.persistence;

import com.ys.adapter.coupon.out.persistence.CouponEntity;
import com.ys.domain.coupon.CouponType;
import com.ys.domain.coupon.DiscountType;
import com.ys.domain.published_coupon.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PublishedCouponRowMapper implements RowMapper<PublishedCouponEntity> {

    @Override
    public PublishedCouponEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        PublishedCouponEntity publishedCouponEntity = getPublishedCouponEntity(rs);

        if (isThere(rs, "coupon_type")) {
            CouponEntity couponEntity = getCouponEntity(rs);
            publishedCouponEntity.setMembershipEntity(couponEntity);
        }

        return publishedCouponEntity;
    }

    private boolean isThere(ResultSet rs, String column) {
        try {
            rs.findColumn(column);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private LocalDateTime getLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZoneId.of("Asia/Seoul"));
    }

    private PublishedCouponEntity getPublishedCouponEntity(ResultSet rs) throws SQLException {
        return new PublishedCouponEntity(
                rs.getString("id"),
                rs.getString("user_id"),
                rs.getString("coupon_id"),
                Status.valueOf(rs.getString("status")),
                getLocalDateTime(rs.getTimestamp("started_at")),
                getLocalDateTime(rs.getTimestamp("ended_at")),
                getLocalDateTime(rs.getTimestamp("created_at")),
                getLocalDateTime(rs.getTimestamp("modified_at")),
                rs.getLong("version")
        );
    }

    private CouponEntity getCouponEntity(ResultSet rs) throws SQLException {
        return new CouponEntity(
                rs.getString("coupon_id"),
                CouponType.valueOf(rs.getString("coupon_type")),
                DiscountType.valueOf(rs.getString("discount_type")),
                rs.getInt("discount_value"),
                rs.getString("description"),
                getLocalDateTime(rs.getTimestamp("coupon_created_at")),
                getLocalDateTime(rs.getTimestamp("coupon_modified_at")),
                getLocalDateTime(rs.getTimestamp("coupon_deleted_at")),
                rs.getLong("coupon_version")
        );
    }
}