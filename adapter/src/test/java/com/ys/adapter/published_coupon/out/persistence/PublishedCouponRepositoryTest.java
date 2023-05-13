package com.ys.adapter.published_coupon.out.persistence;

import com.ys.adapter.common.config.DataJdbcConfig;
import com.ys.adapter.fixture.SupportedCouponEntityFixture;
import com.ys.domain.published_coupon.Status;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Transactional
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@AutoConfigureEmbeddedDatabase(type = POSTGRES, provider = ZONKY)
@SpringJUnitConfig(value = {DataJdbcConfig.class, SpringDataJdbcConfig.class})
@Sql(scripts = "classpath:create.sql")
class PublishedCouponRepositoryTest extends SupportedCouponEntityFixture {

    private static final String ANY_USER_ID = "ANY_USER_ID";

    @Autowired
    private PublishedCouponRepository repository;

    @Test
    void save() {
        PublishedCouponEntity publishedCouponEntity = new PublishedCouponEntity(
                "ANY_ID", ANY_USER_ID, ANY_COUPON_ID, Status.AVAILABLE, NOW, NOW.plusDays(5), null, null, null
        );
        PublishedCouponEntity actual = repository.save(publishedCouponEntity);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull()
        );
    }
}