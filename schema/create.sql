CREATE TABLE IF NOT EXISTS coupons
(
    id                  VARCHAR(36)  	PRIMARY KEY,
    coupon_type         VARCHAR(40) 	NOT NULL,
    discount_type       VARCHAR(40) 	NOT NULL,
    discount_value      INT 			NOT NULL,
    description         VARCHAR(256),
    created_at          TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    modified_at         TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    deleted_at          TIMESTAMPTZ,
    version             BIGINT          NOT NULL
);

CREATE TABLE IF NOT EXISTS published_coupons
(
    id                  VARCHAR(36)  	PRIMARY KEY,
    user_id 			VARCHAR(36)		NOT NULL,
    coupon_id 			VARCHAR(36)		NOT NULL,
    status 				VARCHAR(10) 	NOT NULL,
    started_at 			TIMESTAMPTZ     NOT NULL,
    ended_at			TIMESTAMPTZ     NOT NULL,
    created_at          TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    modified_at         TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    version             BIGINT          NOT NULL
);

insert into coupons(id, coupon_type, discount_type, discount_value, description, created_at, modified_at, deleted_at, version)
values ('1', 'PRODUCT_DISCOUNT', 'PERCENTAGE', 50, 'TEST', NOW(), NOW(), null, 0);
insert into published_coupons(id, user_id, coupon_id, status, started_at, ended_at, created_at, modified_at, version)
values ('123', 'TEST_USER_ID', '1', 'AVAILABLE', '2023-05-14 09:00:00', '2023-05-31 23:59:59', now(), now(), 0);
