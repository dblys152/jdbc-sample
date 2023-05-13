package com.ys.adapter.published_coupon.out.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedCouponRepository extends CrudRepository<PublishedCouponEntity, String> {

}
