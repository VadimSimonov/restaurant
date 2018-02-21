DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM voite;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email,roles, password) VALUES
  ('User', 'user@yandex.ru','ROLE_USER', 'password'),
  ('Admin', 'admin@gmail.com','ROLE_ADMIN', 'admin');

INSERT INTO restaurants (id,name) VALUES
  (100007,'Restorant1'),
  (100008,'Restorant2');

INSERT INTO meals (meal,restauran_id, date_time,price) VALUES
  ('Zavtrak',100007, '2018-05-30 13:00:00',500),
  ('Obed',100008, '2018-05-29 13:00:00',700);

INSERT INTO voite (restauran_id, date_time,voite) VALUES
  (100007, '2018-02-20 10:00:00',5),
  (100008, '2018-02-20 10:00:00',3);
