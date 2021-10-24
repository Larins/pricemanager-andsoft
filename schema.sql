DROP DATABASE IF EXISTS larapricemanager;
CREATE DATABASE larapricemanager;
USE larapricemanager;
DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	origin VARCHAR(100),
    destination VARCHAR(100),
	date_ini DATE,
    delay_days NUMERIC(65, 2),
    date_fin DATE,
    price NUMERIC(65, 2)
);
DROP TABLE IF EXISTS promos;
CREATE TABLE promos (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	origin VARCHAR(100),
    destination VARCHAR(100),
	date_ini DATE,
    duration_days NUMERIC(65, 2),
    date_fin DATE
);

INSERT INTO articles (name, origin, destination, date_ini, delay_days, price) VALUES 
('24h', 'bcn', 'bcn', '2018-05-05', 1, 100)
;

UPDATE articles
	SET date_fin = DATE_ADD(date_ini, INTERVAL delay_days DAY)
;

INSERT  INTO promos (name, origin, destination, date_ini, duration_days) VALUES
('24h', 'bcn', 'bcn', '2018-05-05', 1)
;

UPDATE promos
	SET date_fin = DATE_ADD(date_ini, INTERVAL duration_days DAY)
DROP DATABASE IF EXISTS larapricemanager;
CREATE DATABASE larapricemanager;
USE larapricemanager;
DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	origin VARCHAR(100),
    destination VARCHAR(100),
	date_ini DATE,
    delay_days NUMERIC(65, 2),
    date_fin DATE,
    price NUMERIC(65, 2)
);
DROP TABLE IF EXISTS promos;
CREATE TABLE promos (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	origin VARCHAR(100),
    destination VARCHAR(100),
	date_ini DATE,
    duration_days NUMERIC(65, 2),
    date_fin DATE
);

INSERT INTO articles (name, origin, destination, date_ini, delay_days, price) VALUES 
('24h', 'bcn', 'bcn', '2018-05-05', 1, 100)
;

UPDATE articles
	SET date_fin = DATE_ADD(date_ini, INTERVAL delay_days DAY)
;

INSERT  INTO promos (name, origin, destination, date_ini, duration_days) VALUES
('24h', 'bcn', 'bcn', '2018-05-05', 1)
;

UPDATE promos
	SET date_fin = DATE_ADD(date_ini, INTERVAL duration_days DAY)
;