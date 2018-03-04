DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM voite;
DELETE FROM menu;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email,roles, password) VALUES
  ('User', 'user@yandex.ru','ROLE_USER', 'password'),
  ('Admin', 'admin@gmail.com','ROLE_ADMIN', 'admin');

INSERT INTO restaurants (id,name) VALUES
  (100017,'Restaurant1'),
  (100018,'Restaurant2');

INSERT INTO meals (meal,restauran_id,price) VALUES
  ('Zavtrak',100017,500),
  ('Obed',100018,700);

INSERT INTO voite (id,restauran_id, date_time,voite) VALUES
  (100020,100017, '2018-02-20 10:00:00',5),
  (100021,100018, '2018-02-20 10:00:00',3);

INSERT INTO menu (restauran_id,meal_id,voite_id, date_time) VALUES
  (100017,100002,100020, '2018-02-20 10:00:00'),
  (100018,100003,100021, '2018-02-20 10:00:00');
