CREATE TABLE orderslist
(
    id serial primary key,
    order_status varchar(200) NOT NULL,
    order_date varchar(200) NOT NULL,
    total_cost DOUBLE PRECISION NOT NULL CHECK (total_cost > 0)
);
INSERT INTO orderslist(order_status, order_date, total_cost)
VALUES
    ('Completed', '2023-10-06', 35000.0),
    ('Completed', '2023-10-06', 75000.0),
    ('Completed', '2023-10-12', 120000.0),
    ('In Process', '2023-11-10', 55000.0),
    ('In Process', '2023-11-10', 95000.0),
    ('In Process', '2023-11-10', 35000.0),
    ('In Process', '2023-11-10', 135000.0),
    ('In Process', '2024-12-11', 25000.0),
    ('In Process', '2024-12-11', 30000.0),
    ('In Process', '2024-12-11', 105000.0);


CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    name     VARCHAR(20)  NOT NULL,
    enabled  boolean      NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users
VALUES ('admin', '$2a$10$dYJ9JcdxtCIc6jnJYNTDFOs1tdPt1te25Gf5JKIEc7uRBvJiSk6JO', 'Администратор', true),
       ('user', '$2a$10$zxVS3muLezmSlzipO76OVuUsEPwxBzgYrMMBXu.b383sFiaO.rB5m', 'Пользователь', true);

CREATE TABLE authorities
(
    username  varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (username)
            REFERENCES users (username)
);

INSERT INTO authorities
VALUES ('admin', 'ROLE_ADMIN'),
       ('user', 'ROLE_USER');