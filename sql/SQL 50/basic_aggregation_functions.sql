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

-- https://leetcode.com/problems/project-employees-i/
SELECT
    proj.project_id,
    ROUND(AVG(emp.experience_years), 2) AS average_years
FROM
    Project proj
LEFT JOIN
    Employee emp
ON
    proj.employee_id = emp.employee_id
GROUP BY
    proj.project_id;

-- https://leetcode.com/problems/percentage-of-users-attended-a-contest/
SELECT
    reg.contest_id,
    ROUND(COUNT(us.user_id) * 100.0 / (SELECT COUNT(*) FROM Users), 2) AS percentage
FROM
    Register reg
LEFT JOIN
    Users us
ON
    us.user_id = reg.user_id
GROUP BY
    reg.contest_id
ORDER BY
    percentage DESC, reg.contest_id;

-- https://leetcode.com/problems/queries-quality-and-percentage/
SELECT
    query_name,
    ROUND(AVG(rating::numeric / position), 2) AS quality,
    ROUND(AVG(CASE WHEN rating < 3 THEN 1.0 ELSE 0.0 END) * 100.0, 2) AS poor_query_percentage
FROM
    Queries
GROUP BY
    query_name;

-- https://leetcode.com/problems/monthly-transactions-i/
SELECT
    TO_CHAR(trans_date, 'YYYY-MM') AS month,
    country,
    COUNT(id) as trans_count,
    COUNT(CASE WHEN state = 'approved' THEN 1 END) as approved_count,
    SUM(amount) AS trans_total_amount,
    SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END) AS approved_total_amount
FROM
    Transactions
GROUP BY
    month, country;

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

-- https://leetcode.com/problems/game-play-analysis-iv/
SELECT
    ROUND(COUNT(tommorow) * 1.0 / (SELECT COUNT(DISTINCT player_id) FROM Activity), 2) AS fraction
FROM
    Activity today
LEFT JOIN
    Activity tommorow
ON
    today.player_id = tommorow.player_id AND
    today.event_date + 1 = tommorow.event_date
WHERE
    (today.event_date, today.player_id) IN (
        SELECT
            MIN(event_date),
            player_id
        FROM
            Activity
        GROUP BY
            player_id
    );