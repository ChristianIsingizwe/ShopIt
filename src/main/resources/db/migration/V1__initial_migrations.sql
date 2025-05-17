CREATE TABLE users (
                       id       BIGSERIAL      PRIMARY KEY,
                       name     VARCHAR(255)   NOT NULL,
                       email    VARCHAR(255)   NOT NULL UNIQUE,
                       password VARCHAR(255)   NOT NULL
);

CREATE TABLE addresses (
                           id      BIGSERIAL     PRIMARY KEY,
                           street  VARCHAR(255)  NOT NULL,
                           city    VARCHAR(255)  NOT NULL,
                           state   VARCHAR(255)  NOT NULL,
                           zip     VARCHAR(255)  NOT NULL,
                           user_id BIGINT        NOT NULL
);

CREATE TABLE categories (
                            id   SMALLSERIAL    PRIMARY KEY,
                            name VARCHAR(255)   NOT NULL
);

CREATE TABLE products (
                          id          BIGSERIAL      PRIMARY KEY,
                          name        VARCHAR(255)   NOT NULL,
                          price       DECIMAL(10,2)  NOT NULL,
                          description TEXT           NOT NULL,
                          category_id SMALLINT
);

CREATE TABLE profiles (
                          id             BIGINT     PRIMARY KEY,
                          bio            TEXT,
                          phone_number   VARCHAR(15),
                          date_of_birth  DATE,
                          loyalty_points INT         DEFAULT 0 CHECK (loyalty_points >= 0)
);

CREATE TABLE wishlist (
                          product_id BIGINT NOT NULL,
                          user_id    BIGINT NOT NULL,
                          PRIMARY KEY (product_id, user_id)
);

-- Foreign key constraints
ALTER TABLE addresses
    ADD CONSTRAINT fk_addresses_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE products
    ADD CONSTRAINT fk_products_category
        FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION;

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_product
        FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
  ADD CONSTRAINT fk_wishlist_user
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE profiles
    ADD CONSTRAINT fk_profiles_user
        FOREIGN KEY (id) REFERENCES users (id) ON DELETE NO ACTION;

-- Indexes (PostgreSQL automatically indexes PKs; additional indexes optional)
CREATE INDEX idx_addresses_user_id ON addresses (user_id);
CREATE INDEX idx_products_category_id ON products (category_id);
CREATE INDEX idx_wishlist_user_id  ON wishlist (user_id);
