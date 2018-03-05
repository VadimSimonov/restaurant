DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM vote;
DELETE FROM menu;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO user_roles (id, role) VALUES
  (100020, 'ROLE_USER'),
  (100021, 'ROLE_ADMIN');

INSERT INTO users (id,role_id,name, email, password) VALUES
  (100001,100020,'User', 'user@yandex.ru', 'password'),
  (100002,100021,'Admin', 'admin@gmail.com', 'admin');

INSERT INTO restaurants (id,name) VALUES
  (100017,'Restaurant1'),
  (100018,'Restaurant2');

INSERT INTO meals (meal,restauran_id,price) VALUES
  ('Zavtrak',100017,500),
  ('Obed',100018,700);

INSERT INTO vote (id,restauran_id,user_id, date_time,vote) VALUES
  (100020,100017,100001, '2018-02-20 10:00:00',5),
  (100021,100018,100001, '2018-02-20 10:00:00',3);

INSERT INTO menu (restauran_id, date) VALUES
  (100017, '2018-02-20'),
  (100018, '2018-02-20');
