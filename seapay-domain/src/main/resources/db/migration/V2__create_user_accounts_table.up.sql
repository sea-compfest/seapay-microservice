CREATE TABLE IF NOT EXISTS USER_ACCOUNTS
(
    id          BIGSERIAL,
    user_id     VARCHAR(100),
    name        VARCHAR(100),
    email       VARCHAR(100),
    phonenumber VARCHAR(100),
    user_type   int
);