CREATE TABLE IF NOT EXISTS WALLETS
(
    id        BIGSERIAL,
    wallet_id VARCHAR(100),
    user_id   VARCHAR(100),
    balance   BIGINT
);