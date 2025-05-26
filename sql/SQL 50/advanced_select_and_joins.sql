-- https://leetcode.com/problems/the-number-of-employees-which-report-to-each-employee/
SELECT
    mgr.employee_id,
    mgr.name,
    COUNT(emp.employee_id) AS reports_count,
    ROUND(AVG(emp.age)) AS average_age
FROM
    Employees emp
JOIN 
    Employees mgr
ON
    emp.reports_to = mgr.employee_id
GROUP BY
    mgr.name, mgr.employee_id
ORDER BY
    mgr.employee_id;

-- https://leetcode.com/problems/primary-department-for-each-employee/
SELECT
    employee_id,
    department_id
FROM
    Employee emp
WHERE
    primary_flag = 'Y' OR
    employee_id IN (SELECT
                        employee_id
                    FROM
                        Employee
                    GROUP BY
                        employee_id
                    HAVING count(employee_id) = 1);

-- https://leetcode.com/problems/triangle-judgement/
SELECT
    x,
    y,
    z,
    CASE WHEN (x + y) > z AND (x + z) > y AND (z + y) > x THEN 'Yes' ELSE 'No' END as Triangle
FROM
    Triangle
	
-- https://leetcode.com/problems/consecutive-numbers/
SELECT DISTINCT
    l1.num AS ConsecutiveNums
FROM
    Logs l1,
    Logs l2,
    Logs l3
WHERE
    l1.id = l2.id + 1 AND 
    l2.id = l3.id + 1 AND
    l1.num = l2.num AND
    l2.num = l3.num;

-- https://leetcode.com/problems/product-price-at-a-given-date/
SELECT
    p1.product_id,
    p1.new_price AS price
FROM 
    Products p1
WHERE p1.change_date = (
    SELECT 
        MAX(p2.change_date)
    FROM
        Products p2
    WHERE 
        p2.product_id = p1.product_id AND
        p2.change_date <= '2019-08-16'
)
UNION 
SELECT
    product_id,
    10 as price
FROM 
    Products
WHERE
    product_id NOT IN (SELECT DISTINCT product_id FROM Products WHERE change_date <='2019-08-16');
	
-- https://leetcode.com/problems/last-person-to-fit-in-the-bus/
SELECT
    person_name
FROM
    (SELECT
        person_name,
        turn,
        SUM(weight) OVER (ORDER BY turn) as cumulative
        FROM
            Queue
    ) temp 
WHERE 
    cumulative <= 1000
ORDER BY 
    turn DESC LIMIT 1;

-- https://leetcode.com/problems/count-salary-categories/
SELECT
    'Low Salary' as category,
    COUNT(account_id) as accounts_count
FROM
    Accounts
WHERE
    income < 20000
UNION
    SELECT
    'Average Salary' as category,
    COUNT(account_id) as accounts_count 
FROM
    Accounts
WHERE
    income BETWEEN 20000 AND 50000
UNION
    SELECT
    'High Salary' as category,
    COUNT(account_id) as accounts_count 
FROM
    Accounts
WHERE
    income > 50000;