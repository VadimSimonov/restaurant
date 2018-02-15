DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email,roles, password) VALUES
  ('User', 'user@yandex.ru','ROLE_USER', 'password'),
  ('Admin', 'admin@gmail.com','ROLE_ADMIN', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
