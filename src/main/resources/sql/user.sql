DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cart;

CREATE TABLE users(
    id SERIAL UNIQUE,
    name text,
    email text,
    password text,
    PRIMARY KEY (id)
);

CREATE TABLE cart(
    user_id int,
    products_in_cart text,
    CONSTRAINT users_fk
        FOREIGN KEY (user_id) REFERENCES users(id)
                 ON DELETE CASCADE
);

