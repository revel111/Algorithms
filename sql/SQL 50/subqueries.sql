-- https://leetcode.com/problems/employees-whose-manager-left-the-company/
SELECT
    employee_id
FROM
    Employees
WHERE
    salary < 30000 AND 
    manager_id NOT IN (SELECT employee_id FROM Employees)
ORDER BY
    employee_id;

-- https://leetcode.com/problems/exchange-seats/
SELECT
    CASE WHEN id % 2 = 0 AND id - 1 IN (SELECT id FROM Seat) THEN id - 1
        WHEN id % 2 = 1 AND id + 1 IN (SELECT id FROM Seat) THEN id + 1
        ELSE id
        END AS id,
        student
FROM
    Seat
ORDER BY
    id;

-- https://leetcode.com/problems/movie-rating/
(SELECT
    u.name AS results
FROM
    Users u 
LEFT JOIN
    MovieRating mr
ON
    u.user_id = mr.user_id
GROUP BY
    u.name
ORDER BY
    COUNT(*) DESC, u.name
LIMIT 1)
UNION ALL
(SELECT
    m.title AS results
FROM
    Movies m
LEFT JOIN
    MovieRating mr
ON
    m.movie_id = mr.movie_id
WHERE
    EXTRACT(month FROM mr.created_at) = 2 AND EXTRACT(year FROM mr.created_at) = 2020
GROUP BY
    m.title
ORDER BY
    AVG(mr.rating) DESC, m.title
LIMIT 1);

-- https://leetcode.com/problems/restaurant-growth/
SELECT
    visited_on + 6 as visited_on,
    (SELECT SUM(amount) FROM Customer WHERE visited_on BETWEEN c.visited_on AND c.visited_on + 6) as amount,
    (SELECT ROUND(SUM(amount) / 7.0, 2) FROM Customer WHERE visited_on BETWEEN c.visited_on AND c.visited_on + 6) as average_amount
FROM
    Customer c
WHERE
    EXISTS (SELECT visited_on FROM Customer WHERE visited_on = c.visited_on + 6)
GROUP BY
    visited_on 
ORDER BY 
    visited_on;

-- https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/
SELECT id, 
   COUNT(id) AS num
FROM
(
   SELECT
        requester_id AS id 
   FROM 
        RequestAccepted
   UNION ALL
   SELECT 
        accepter_id AS id
   FROM 
   RequestAccepted
) all_ids
GROUP BY 
    id
ORDER BY
    COUNT(id) DESC
LIMIT 1;

-- https://leetcode.com/problems/investments-in-2016/
SELECT
    ROUND(SUM(tiv_2016)::numeric, 2) AS tiv_2016 
FROM
    Insurance
WHERE
    tiv_2015 IN
    (SELECT
        tiv_2015
    FROM
        Insurance
    GROUP BY
        tiv_2015
    HAVING
        COUNT(*) > 1
    )
AND
    (lat, lon) IN
    (SELECT
        lat, lon
    FROM
        Insurance
    GROUP BY
        lat, lon
    HAVING
        COUNT(*) = 1
    );

-- https://leetcode.com/problems/department-top-three-salaries/
SELECT DISTINCT
    dept.name as Department,
    emp1.name as Employee,
    emp1.salary as Salary
FROM
    Department dept
JOIN
    Employee emp1
ON
    emp1.departmentId = dept.id
WHERE
    3 > (SELECT
            COUNT(DISTINCT emp2.salary)
        FROM
            Employee emp2
        WHERE
            emp2.salary > emp1.salary AND 
            emp1.departmentId = emp2.departmentId
        );