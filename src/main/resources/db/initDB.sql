DROP TABLE IF EXISTS user_roles CASCADE ;
DROP TABLE IF EXISTS menu CASCADE ;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu_day;
DROP TABLE IF EXISTS vote_restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 10000;

CREATE TABLE menu
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date              TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  UNIQUE (date)
);

CREATE TABLE user_roles
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  role    VARCHAR                      NOT NULL
);

CREATE TABLE restaurants
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR                      NOT NULL,
  UNIQUE (name)
  /* FOREIGN KEY (meals_id ) REFERENCES meals (id) ON DELETE CASCADE*/
);

CREATE TABLE meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restauran_id INTEGER                       ,
  meal           VARCHAR                       NOT NULL,
  price             INTEGER                       NOT NULL,
   FOREIGN KEY (restauran_id ) REFERENCES restaurants (id) ON DELETE CASCADE
);
/*CREATE UNIQUE INDEX meals_unique_restaurant_idx ON meals (restauran_id); */


CREATE TABLE menu_day
(
  menu_id         INTEGER                      NOT NULL,
  restauran_id    INTEGER                      NOT NULL
);

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  role_id          INTEGER                 NOT NULL,
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  FOREIGN KEY (role_id ) REFERENCES user_roles (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE vote (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER                 NOT NULL,
  restauran_id INTEGER                NOT NULL,
  date_time         TIMESTAMP DEFAULT now() NOT NULL,
  vote             INTEGER                 NOT NULL,
  FOREIGN KEY (user_id ) REFERENCES users (id)  ON DELETE CASCADE,
  FOREIGN KEY (restauran_id ) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE vote_restaurant
(
  vote_id         INTEGER                      NOT NULL,
  restaurant_id    INTEGER                      NOT NULL
);



