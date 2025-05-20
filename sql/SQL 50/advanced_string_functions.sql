-- https://leetcode.com/problems/fix-names-in-a-table/
SELECT
    user_id,
    UPPER(SUBSTRING(name, 1, 1)) || LOWER(SUBSTRING(name, 2, LENGTH(name))) as name
FROM
    Users
ORDER BY user_id;

-- https://leetcode.com/problems/patients-with-a-condition/
SELECT 
    *
FROM
    Patients
WHERE
    conditions LIKE '% DIAB1%' OR conditions LIKE 'DIAB1%';
	
-- https://leetcode.com/problems/delete-duplicate-emails/
DELETE FROM
    Person p1
USING
    Person p2
WHERE
    p1.email = p2.email AND p1.id > p2.id;
	
-- https://leetcode.com/problems/second-highest-salary/
SELECT ( -- XYETA
  SELECT    
    DISTINCT salary
  FROM
    Employee
  ORDER BY
    salary DESC
  LIMIT 1 OFFSET 1
) AS SecondHighestSalary;

SELECT -- TOP
    MAX(salary) AS SecondHighestSalary
FROM 
    Employee
WHERE
    salary < (SELECT MAX(salary) FROM Employee);

-- https://leetcode.com/problems/group-sold-products-by-the-date/
SELECT
    sell_date,
    COUNT(DISTINCT product) AS num_sold,
    STRING_AGG(product, ',' ORDER BY product) AS products
FROM (
    SELECT DISTINCT
        sell_date,
        product
    FROM Activities
)
GROUP BY
    sell_date
ORDER BY
    sell_date;

-- https://leetcode.com/problems/list-the-products-ordered-in-a-period/
SELECT
    product_name,
    SUM(o.unit) as unit
FROM
    Products p
LEFT JOIN Orders o ON
    p.product_id = o.product_id
WHERE
    extract(YEAR FROM o.order_date) = 2020 AND extract(MONTH FROM o.order_date) = 2
GROUP BY
    product_name
HAVING
    SUM(o.unit) >= 100;
	
-- https://leetcode.com/problems/find-users-with-valid-e-mails/
SELECT *
FROM Users
WHERE mail ~ '^[a-zA-Z]+[a-zA-Z0-9_.-]*@leetcode\.com$'