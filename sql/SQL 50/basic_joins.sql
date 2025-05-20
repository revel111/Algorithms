-- https://leetcode.com/problems/managers-with-at-least-5-direct-reports/
SELECT
    m.name
FROM (
    SELECT managerId
    FROM Employee
    GROUP BY managerId
    HAVING COUNT(*) >= 5
) AS e
JOIN Employee m ON e.managerId = m.id;

-- https://leetcode.com/problems/confirmation-rate/
SELECT
    s.user_id,
    ROUND(AVG(CASE WHEN c.action = 'confirmed' THEN 1 ELSE 0 END), 2) as confirmation_rate
FROM
    Signups s
LEFT JOIN
    Confirmations c
ON
    c.user_id = s.user_id
GROUP BY
    s.user_id;