
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
    delay_days NUMERIC(65, 2),
    date_fin DATE,
    discount NUMERIC(65, 2)
);

INSERT INTO articles (name, origin, destination, date_ini, delay_days, price) VALUES 
('24h', 'bcn', 'bcn', '2021-05-05', 1, 100),
('RAPIDA', 'bcn', 'bcn', '2021-08-10', 10, 100),
('NORMAL', 'bcn', 'bcn', '2021-10-24', 10, 100),
('24h', 'bcn', 'bcn', '2021-12-25', 1, 100)

;

UPDATE articles
	SET date_fin = DATE_ADD(date_ini, INTERVAL delay_days DAY)
;

INSERT  INTO promos (name, origin, destination, date_ini, duration_days, delay_days, date_fin, discount) VALUES
('CumpleEmpresa', 'bcn', 'bcn', '2021-05-05', 1, 1, null,  0.8),
('Clearance 2021-08-01 to 2021-08-31', 'bcn', 'bcn', '2021-08-01', null, null, '2021-08-31', 0.5),
('Halloween', 'bcn', 'bcn', '2021-10-31', 1, 10, null, 1.2),
('Xmas 2021-12-20 to 2022-01-10', 'bcn', 'bcn', '2021-12-20', null, null, '2022-01-10', 1.2)
;

UPDATE promos
	SET date_fin = DATE_ADD(date_ini, INTERVAL duration_days DAY)
    WHERE date_fin IS NULL
;

UPDATE promos
	SET duration_days = DATEDIFF(date_fin, date_ini)
    WHERE duration_days IS NULL
;