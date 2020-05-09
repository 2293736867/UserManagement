DROP DATABASE user_test;
CREATE DATABASE user_test;
USE user_test;
CREATE USER aa@localhost IDENTIFIED BY '123456';
GRANT SELECT,UPDATE,DELETE,INSERT ON user_test.* TO aa@localhost;
CREATE TABLE user
(
    id CHAR(8) PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);