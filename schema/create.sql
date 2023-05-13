CREATE TABLE IF NOT EXISTS coupons
(
    id                  varchar(36)  	PRIMARY KEY,
    coupon_type         varchar(40) 	not null,
    discount_type       varchar(40) 	not null,
    discount_value      int 			not null,
    description         varchar(256),
    created_at          TIMESTAMPTZ     not null DEFAULT NOW(),
    modified_at         TIMESTAMPTZ     not null DEFAULT NOW(),
    deleted_at          TIMESTAMPTZ,
    version             BIGINT          not null
);

CREATE TABLE IF NOT EXISTS published_coupons
(
    id                  varchar(36)  	PRIMARY KEY,
    user_id 			varchar(36)		not null,
    coupon_id 			varchar(36)		not null,
    status 				varchar(10) 	not null,
    started_at 			TIMESTAMPTZ     not null,
    ended_at			TIMESTAMPTZ     not null,
    created_at          TIMESTAMPTZ     not null DEFAULT NOW(),
    modified_at         TIMESTAMPTZ     not null DEFAULT NOW(),
    version             BIGINT          not null
);