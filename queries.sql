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
