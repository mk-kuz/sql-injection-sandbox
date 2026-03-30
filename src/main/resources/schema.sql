-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE products
(
    id    INT PRIMARY KEY,
    name  VARCHAR(255),
    price DECIMAL(10, 2)
);

CREATE TABLE users
(
    id       INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255)
);

-- Admin is ID 1 (The prize for the attacker)
INSERT INTO users
VALUES (1, 'admin', 'SuperSecretPassword123', 'ADMIN');
INSERT INTO users
VALUES (2, 'bob', 'password', 'USER');
-- New targets for the lab
INSERT INTO users VALUES (3, 'alice', 'alice_secret_2024', 'USER');
INSERT INTO users VALUES (4, 'charlie', 'qwerty_789', 'USER');
INSERT INTO users VALUES (5, 'developer_mike', 'dev_access_only_!$', 'DEVELOPER');
INSERT INTO users VALUES (6, 'sarah_hr', 'human_resources_pwd', 'HR');
INSERT INTO users VALUES (7, 'guest_test', 'guest', 'GUEST');
INSERT INTO users VALUES (8, 'super_user', 'king_of_the_castle', 'ADMIN');
INSERT INTO users VALUES (9, 'it_dept', 'root_access_granted', 'ADMIN');
INSERT INTO users VALUES (10, 'marketing_team', 'sell_more_stuff', 'USER');


INSERT INTO products
VALUES (1, 'iPhone', 999.99);
INSERT INTO products
VALUES (2, 'MacBook', 1999.99);
INSERT INTO products
VALUES (3, 'AirPods', 199.99);
