package com.ys.adapter.coupon.out.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends CrudRepository<CouponEntity, String> {

}
