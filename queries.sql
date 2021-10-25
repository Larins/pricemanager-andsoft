SELECT article, original_price, discount, increment, original_price*discount*increment AS final_price, delay_days, article_date_ini, article_date_fin 
FROM
(SELECT 
	A.name AS article, 
    A.price AS original_price, 
    CASE 
    	WHEN MIN(P.discount) > 1 THEN 1
        ELSE MIN(P.discount)
    END AS discount,
    CASE 
    	WHEN MAX(P.discount) < 1 THEN 1
        ELSE MAX(P.discount)
    END AS increment,
    A.delay_days AS delay_days, 
    A.date_ini AS article_date_ini, 
    A.date_fin AS article_date_fin
FROM articles A
LEFT JOIN promos P ON (
                	(P.date_ini BETWEEN A.date_ini AND A.date_fin OR P.date_fin BETWEEN A.date_ini AND A.date_fin)
					OR (A.date_ini BETWEEN P.date_ini AND P.date_fin OR A.date_fin BETWEEN P.date_ini AND P.date_fin)
					AND (A.delay_days = P.delay_days OR P.delay_days IS NULL)
        	)
GROUP BY article, original_price, delay_days, article_date_ini, article_date_fin) AS Z
;


SELECT article, original_price, discount, increment, original_price*discount*increment AS final_price, delay_days, article_date_ini, article_date_fin 
FROM
(SELECT 
	A.name AS article, 
    A.price AS original_price, 
    CASE 
    	WHEN MIN(P.discount) > 1 THEN 1
        ELSE MIN(P.discount)
    END AS discount,
    CASE 
    	WHEN MAX(P.discount) < 1 THEN 1
        ELSE MAX(P.discount)
    END AS increment,
    A.delay_days AS delay_days, 
    A.date_ini AS article_date_ini, 
    A.date_fin AS article_date_fin
FROM articles A
LEFT JOIN promos P ON (
                	(P.date_ini BETWEEN A.date_ini AND A.date_fin OR P.date_fin BETWEEN A.date_ini AND A.date_fin)
					OR (A.date_ini BETWEEN P.date_ini AND P.date_fin OR A.date_fin BETWEEN P.date_ini AND P.date_fin)
					AND (A.delay_days = P.delay_days OR P.delay_days IS NULL)
        	)
WHERE A.name = '24h'
AND A.date_ini <= '2021-12-26'  AND A.date_fin >= '2021-12-26'
GROUP BY article, original_price, delay_days, article_date_ini, article_date_fin) AS Z
;


INSERT INTO articles (name, origin, destination, delay_days, price)
SELECT * FROM (
    SELECT 
        'NORMAL' AS name,
        'bcn' AS origin,
        'bcn' AS destination,
        10 AS delay_days,
        100 AS price) 
    AS temp
WHERE NOT EXISTS (
    SELECT name, origin, destination, date_ini, delay_days, price 
    FROM articles 
    WHERE
        name = 'NORMAL'
        AND origin = 'bcn'
        AND destination = 'bcn'
        AND delay_days =  10
        AND price = 100
) LIMIT 1;