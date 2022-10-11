SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS application_user;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE category
(id BIGINT NOT NULL AUTO_INCREMENT
, name VARCHAR(100) NOT NULL
, PRIMARY KEY (id)
);
INSERT INTO category (name)
VALUES (''), ('Dekkari'), ('Novelli'), ('Dokumentti');
CREATE TABLE book (
id BIGINT NOT NULL AUTO_INCREMENT
, title VARCHAR(100) NOT NULL
, author VARCHAR(100) NOT NULL
, price FLOAT
, isbn VARCHAR(50) NOT NULL
, book_year INT
, categoryid BIGINT
, PRIMARY KEY (id)
, FOREIGN KEY (categoryid) REFERENCES category(id));
INSERT INTO book (title, author, book_year, isbn, price, categoryid)
VALUES
('Kasvoton kuolema', 'Henning Mankell', 1989, '', 12.00, 1),
('Riian verikoirat', 'Henning Mankell', 1990, '', 13.00, 1),
('From Dusk till Dawn', 'Robert Rodriques', 1996, '66546044-55', 19.99, 2),
('a Farewell to Arms', 'Ernest Hemingway', 1929, '1232323-21', 29.99, 3);
CREATE TABLE application_user
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
, firstname VARCHAR(100) NOT NULL
, lastname VARCHAR(100) NOT NULL
, role VARCHAR(100) NOT NULL
, username VARCHAR(250) NOT NULL
, password_hash VARCHAR(250) NOT NULL);
INSERT INTO application_user (firstname, lastname, username, password_hash,
role)
VALUES ('Jussi', 'Kosonen', 'user',
'$2a$10$amK1FwmFcC5BctacyKiSW.3ZG5JTD3Jky0Usvjuli3m3EPVToasV6', 'USER'),
('Jussi', 'Kosonen', 'admin',
'$2a$10$FndAP653l6sY1f/UuWv/LeESJ1thxEEXx7Or/P1iR3aKZZiIBAtGC', 'ADMIN');
SELECT * FROM category;
SELECT * FROM book;
SELECT * FROM application_user;