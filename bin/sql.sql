CREATE DATABASE user_test;
USE user_test;
CREATE USER 'aa'@'%' IDENTITY BY '123456';
GRANT SELECT,UPDATE,DELETE,INSERT ON user_test.* TO 'aa'@'%';
CREATE TABLE user
(
    id CHAR(8) PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);