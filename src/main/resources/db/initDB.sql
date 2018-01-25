DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS voite;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 10000;

CREATE TABLE restaurants
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR                      NOT NULL
);

CREATE TABLE meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  meal           VARCHAR                       NOT NULL,
  restauran_id     INTEGER                       NOT NULL,
  date_time         TIMESTAMP DEFAULT now()       NOT NULL,
  price             INTEGER                       NOT NULL,
  FOREIGN KEY (restauran_id ) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_restaurant_idx ON meals (restauran_id);

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE voite (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id           INTEGER                 NOT NULL,
  restauran_id     INTEGER                 NOT NULL,
  date_time         TIMESTAMP DEFAULT now() NOT NULL,
  value             INTEGER                 NOT NULL,
  FOREIGN KEY (restauran_id ) REFERENCES restaurants (id)  ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_user_restauran_idx ON voite (restauran_id, user_id);