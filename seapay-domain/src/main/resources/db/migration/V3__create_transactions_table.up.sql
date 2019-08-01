CREATE TABLE IF NOT EXISTS TRANSACTIONS
(
    id               BIGSERIAL,
    transaction_id   VARCHAR(100),
    reference_id     VARCHAR(100),
    credited_wallet  VARCHAR(100),
    debited_wallet   VARCHAR(100),
    description      VARCHAR(255),
    transaction_date timestamp,
    amount           BIGINT,
    transaction_type int
);