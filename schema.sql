
DROP DATABASE IF EXISTS larapricemanager;
CREATE DATABASE larapricemanager;
USE larapricemanager;
DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	origin VARCHAR(100),
    destination VARCHAR(100),
    delay_days NUMERIC(65, 2),
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

INSERT INTO articles (name, origin, destination, delay_days, price) VALUES 
('24h', '08040', '08040', 1, 500),
('RAPIDA', '08040', '08040', 10, 200),
('NORMAL', '08040', '08040', 10, 100),
('24h', '08040', '08005', 1, 120),
('RAPIDA', '08040', '08005', 10, 240),
('NORMAL', '08040', '08005', 10, 580)

;


INSERT  INTO promos (name, origin, destination, date_ini, duration_days, delay_days, date_fin, discount) VALUES
('CumpleEmpresa', '08040', '08040', '2021-05-05', 1, 1, null,  0.8),
('Clearance 2021-08-01 to 2021-08-31', '08040', '08040', '2021-08-01', null, null, '2021-08-31', 0.5),
('Halloween', '08040', '08040', '2021-10-31', 1, 10, null, 1.2),
('Xmas 2021-12-20 to 2022-01-10', '08040', '08040', '2021-12-20', null, null, '2022-01-10', 1.2),
('Lotería de Navidas', '08040', '08040', '2021-12-22', 1, 1, null,  0.95)
;

UPDATE promos
	SET date_fin = DATE_ADD(date_ini, INTERVAL duration_days DAY)
    WHERE date_fin IS NULL
;

UPDATE promos
	SET duration_days = DATEDIFF(date_fin, date_ini)
    WHERE duration_days IS NULL
;