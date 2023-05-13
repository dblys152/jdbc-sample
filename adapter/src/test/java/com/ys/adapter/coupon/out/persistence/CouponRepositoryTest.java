package com.ys.adapter.coupon.out.persistence;

import com.ys.adapter.common.config.DataJdbcConfig;
import com.ys.adapter.coupon.out.persistence.CouponEntity;
import com.ys.adapter.coupon.out.persistence.CouponRepository;
import com.ys.adapter.fixture.SupportedCouponEntityFixture;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Transactional
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@AutoConfigureEmbeddedDatabase(type = POSTGRES, provider = ZONKY)
@SpringJUnitConfig(value = {DataJdbcConfig.class, SpringDataJdbcConfig.class})
@Sql(scripts = "classpath:create.sql")
class CouponRepositoryTest extends SupportedCouponEntityFixture {

    @Autowired
    private CouponRepository repository;

    @Test
    void save() {
        CouponEntity couponEntity = new CouponEntity(
                ANY_COUPON_ID, ANY_COUPON_TYPE,ANY_DISCOUNT_TYPE, ANY_DISCOUNT_VALUE, ANY_DESCRIPTION, NOW, NOW, null, null
        );
        CouponEntity actual = repository.save(couponEntity);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull()
        );
    }
}