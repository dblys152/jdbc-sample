package com.ys.adapter.published_coupon.out.persistence;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class PublishedCouponRepositorySupport {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PublishedCouponRepositorySupport(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<PublishedCouponEntity> findById(String id) {
        String sql = "select " +
                "       pc.id " +
                "     , pc.user_id " +
                "     , pc.coupon_id " +
                "     , pc.status " +
                "     , pc.started_at " +
                "     , pc.ended_at " +
                "     , pc.created_at " +
                "     , pc.modified_at " +
                "     , pc.version " +
                "     , c.coupon_type " +
                "     , c.discount_type " +
                "     , c.discount_value " +
                "     , c.description " +
                "     , c.created_at       coupon_created_at " +
                "     , c.modified_at      coupon_modified_at " +
                "     , c.deleted_at       coupon_deleted_at " +
                "     , c.version          coupon_version " +
                "from   published_coupons pc " +
                "join   coupons c " +
                "on     pc.coupon_id = c.id " +
                "where  1 = 1 " +
                "and    pc.id = :id ";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return jdbcTemplate.query(sql, parameterSource, new PublishedCouponRowMapper()).stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<PublishedCouponEntity> findAllByParam(PublishedCouponWhereParam param) {
        String sql = "select " +
                "       pc.id " +
                "     , pc.user_id " +
                "     , pc.coupon_id " +
                "     , pc.status " +
                "     , pc.started_at " +
                "     , pc.ended_at " +
                "     , pc.created_at " +
                "     , pc.modified_at " +
                "     , pc.version " +
                "     , c.coupon_type " +
                "     , c.discount_type " +
                "     , c.discount_value " +
                "     , c.description " +
                "     , c.created_at       coupon_created_at " +
                "     , c.modified_at      coupon_modified_at " +
                "     , c.deleted_at       coupon_deleted_at " +
                "     , c.version          coupon_version " +
                "from   published_coupons pc " +
                "join   coupons c " +
                "on     pc.coupon_id = c.id " +
                "where  1 = 1 " +
                "and    (coalesce(:userId, '') = '' or pc.user_id = :userId) " +
                "and    (coalesce(:status, '') = '' or pc.status = :status) ";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(param);
        try {
            return jdbcTemplate.query(sql, parameterSource, new PublishedCouponRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
