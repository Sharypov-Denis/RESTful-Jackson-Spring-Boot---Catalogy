DROP TABLE IF EXISTS products;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE products
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255)        NOT NULL
);
INSERT INTO products (name, description)
VALUES ('Молоко', 'описание продукта'),
       ('Творог', 'описание продукта'),
       ('Сливки', 'описание продукта'),
       ('Пиво', 'описание продукта'),
       ('Сок', 'описание продукта'),
       ('Хлопья', 'описание продукта'),
       ('Масло сливочное', 'описание продукта'),
       ('Сыр', 'описание продукта'),
       ('Сметана', 'описание продукта'),
       ('Йогурт', 'описание продукта');