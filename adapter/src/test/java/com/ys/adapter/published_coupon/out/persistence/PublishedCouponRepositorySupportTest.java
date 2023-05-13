package com.ys.adapter.published_coupon.out.persistence;

import com.ys.adapter.common.config.DataJdbcConfig;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

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
class PublishedCouponRepositorySupportTest {

    private static final String INSERTED_PUBLISHED_COUPON_ID = "123";
    private static final String INSERTED_USER_ID = "TEST_USER_ID";

    @Autowired
    private DataSource dataSource;

    private PublishedCouponRepositorySupport repositorySupport;

    @BeforeEach
    void setUp() {
        repositorySupport = new PublishedCouponRepositorySupport(dataSource);
    }

    @Test
    void select_join() {
        Optional<PublishedCouponEntity> actual = repositorySupport.findById(INSERTED_PUBLISHED_COUPON_ID);
        Optional<PublishedCouponEntity> actual2 = repositorySupport.findById("NO_ID");

        assertAll(
                () -> assertThat(actual).isPresent(),
                () -> assertThat(actual2).isEmpty()
        );
    }

    @Test
    void select_join_with_param() {
        PublishedCouponWhereParam param = PublishedCouponWhereParam.builder()
                .userId(INSERTED_USER_ID)
                .build();
        List<PublishedCouponEntity> actual = repositorySupport.findAllByParam(param);

        assertAll(
                () -> assertThat(actual).isNotEmpty(),
                () -> assertThat(actual.get(0).getCouponEntity().getId()).isEqualTo("1")
        );
    }
}