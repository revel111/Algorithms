--https://leetcode.com/problems/not-boring-movies/
SELECT *
FROM
    Cinema
WHERE
    description != 'boring' AND id % 2 = 1
ORDER BY
    rating DESC;

-- https://leetcode.com/problems/average-selling-price/
SELECT
    p.product_id,
    COALESCE(ROUND(SUM(price * units) * 1.0 / SUM(units), 2), 0) as average_price
FROM Prices p
LEFT JOIN UnitsSold u 
ON 
    p.product_id = u.product_id AND
    u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;

-- https://leetcode.com/problems/percentage-of-users-attended-a-contest/
SELECT
contest_id,
ROUND((COUNT(user_id) * 100 / (SELECT COUNT(*)::FLOAT FROM Users))::numeric, 2) as percentage
FROM Register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id;

-- https://leetcode.com/problems/queries-quality-and-percentage/
SELECT 
    query_name,
    ROUND(AVG(rating::numeric / position), 2) AS quality,
    ROUND(AVG(CASE WHEN rating < 3 THEN 1.0 ELSE 0.0 END) * 100, 2) AS poor_query_percentage
FROM Queries
WHERE query_name IS NOT NULL
GROUP BY query_name;

-- https://leetcode.com/problems/monthly-transactions-i/
SELECT 
    TO_CHAR(trans_date, 'YYYY-MM') as month,
    country,
    COUNT(id) as trans_count,
    SUM(CASE WHEN state = 'approved' THEN 1 ELSE 0 END) as approved_count,
    SUM(amount) as trans_total_amount,
    SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END) as approved_total_amount
FROM
    Transactions
GROUP BY month, country;


-- https://leetcode.com/problems/immediate-food-delivery-ii/description/
SELECT
    ROUND(AVG(CASE WHEN order_date = customer_pref_delivery_date THEN 1.0 ELSE 0.0 END) * 100, 2) AS immediate_percentage
FROM
    Delivery
WHERE
    (customer_id, order_date) IN (
        SELECT
            customer_id, 
            MIN(order_date)
        FROM 
            Delivery
        GROUP BY 
            customer_id
    );
