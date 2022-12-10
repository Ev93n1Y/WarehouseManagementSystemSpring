CREATE TABLE producers
(
    id   UUID         PRIMARY KEY ,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE products
(
    id          UUID         PRIMARY KEY ,
    name        VARCHAR(100) NOT NULL,
    price       DECIMAL(10, 2),
    producer_id UUID REFERENCES producers (id) ON DELETE CASCADE
);

CREATE TABLE roles
(
    id   UUID         PRIMARY KEY ,
    role VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id        UUID         PRIMARY KEY ,
    email     VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(100),
    first_name VARCHAR(100),
    last_name  VARCHAR(100)
);

CREATE TABLE user_roles
(
    role_id UUID NOT NULL REFERENCES roles (id),
    user_id UUID NOT NULL REFERENCES users (id),
	PRIMARY KEY (role_id, user_id)
);