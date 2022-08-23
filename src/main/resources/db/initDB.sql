DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS eucs;

CREATE SEQUENCE global_seq START WITH 10000;

CREATE TABLE users
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    login_email VARCHAR(64)        UNIQUE               NOT NULL ,
    password    VARCHAR(128)                            NOT NULL ,
    fullname    VARCHAR(128)                            NOT NULL ,
    birth_year  INTEGER check ( birth_year > 1900 )     NOT NULL ,
    address     VARCHAR(128)                            NOT NULL ,
    agreement   VARCHAR(64)                             NOT NULL ,
    deposit     INTEGER check ( deposit >=0 ) DEFAULT 0          ,
    enabled     BOOL    DEFAULT TRUE                    NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON users(login_email);

CREATE TABLE eucs
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id     INTEGER                                  ,
    model       VARCHAR(128)                    NOT NULL ,
    serial_n    VARCHAR(64)       UNIQUE        NOT NULL ,
    weight      INTEGER check ( weight > 0 )    NOT NULL ,
    distance    INTEGER check ( distance > 0 )  NOT NULL ,
    tyre        VARCHAR(64)                     NOT NULL ,
    day_price   INTEGER check ( day_price > 0 ) NOT NULL ,
    deposit     INTEGER check ( deposit > 0)    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);
CREATE UNIQUE INDEX eucs_unique_user_serial_idx ON eucs(user_id, serial_n);

ALTER TABLE eucs ADD COLUMN rent_start TIMESTAMP;
ALTER TABLE eucs ADD COLUMN rent_days integer;
ALTER TABLE eucs ADD COLUMN speed integer default 1;
