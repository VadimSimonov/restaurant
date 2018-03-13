DELETE FROM user_roles;
DELETE FROM menu;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM vote;
DELETE FROM menu_day;
DELETE FROM vote_restaurant;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO menu (id,date) VALUES
  (100030,'2018-02-20'),
  (100031,'2018-02-21');

INSERT INTO user_roles (id, role) VALUES
  (100020, 'ROLE_USER'),
  (100021, 'ROLE_ADMIN');

INSERT INTO users (id,role_id,name, email, password) VALUES
  (100040,100020,'User', 'user@yandex.ru', 'password'),
  (100041,100021,'Admin', 'admin@gmail.com', 'admin');

INSERT INTO restaurants (id,name) VALUES
  (100060,'Restaurant1'),
  (100061,'Restaurant2');

INSERT INTO meals (meal,restauran_id,price) VALUES
  ('Zavtrak',100060,500),
  ('Obed',100061,700);

INSERT INTO vote (id,user_id, date_time,vote) VALUES
  (100050,100040, '2018-02-20 10:00:00',1),
  (100051,100041, '2018-02-20 10:00:00',-1);

INSERT INTO vote_restaurant (vote_id,restaurant_id) VALUES
  (100050,100060),
  (100051,100061);

INSERT INTO menu_day (restauran_id,menu_id) VALUES
  (100060,100030),
  (100061,100030),
  (100061,100031);
