-- https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/
SELECT
    teacher_id,
    COUNT(DISTINCT subject_id) AS cnt
FROM
    Teacher
GROUP BY
    teacher_id;
	
-- https://leetcode.com/problems/user-activity-for-the-past-30-days-i/
SELECT
    activity_date as day,
    COUNT(DISTINCT user_id) AS active_users
FROM
    Activity
WHERE
    activity_date <= '2019-07-27' AND
	'2019-07-27' - activity_date < 30
GROUP BY 
	activity_date;

-- https://leetcode.com/problems/product-sales-analysis-iii/
SELECT
    product_id,
    year as first_year,
    quantity,
    price
FROM
    Sales
WHERE
    (product_id, year) IN (SELECT 
								product_id,
								min(year)
							FROM 
								Sales
							GROUP BY 
								product_id
						);
	
-- https://leetcode.com/problems/classes-more-than-5-students/
SELECT
    class
FROM 
    Courses
GROUP BY
    class
HAVING
    COUNT(DISTINCT student) >= 5;
	
-- https://leetcode.com/problems/find-followers-count/
SELECT
    user_id,
    COUNT(follower_id) AS followers_count
FROM
    Followers
GROUP BY
    user_id
ORDER BY
    user_id;
	
-- https://leetcode.com/problems/biggest-single-number/
SELECT 
    MAX(num) AS num
FROM (
    SELECT
        num
    FROM
        MyNumbers
    GROUP BY
        num
    HAVING
        COUNT(num) = 1
);

-- https://leetcode.com/problems/customers-who-bought-all-products/
SELECT
    customer_id
FROM
    Customer
GROUP BY
    customer_id
HAVING
    COUNT(DISTINCT product_key) = (SELECT COUNT(*) FROM Product);