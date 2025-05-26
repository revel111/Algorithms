-- https://leetcode.com/problems/replace-employee-id-with-the-unique-identifier/
SELECT 
	eu.unique_id,
	e.name 
FROM 
	Employees e 
LEFT JOIN 
	EmployeeUNI eu 
ON 
	e.id = eu.id;

-- https://leetcode.com/problems/product-sales-analysis-i/
SELECT 
	p.product_name,
	s.year,
	s.price 
FROM 
	Sales s
LEFT JOIN
	Product p 
ON 
	p.product_id = s.product_id;

-- https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/
SELECT
    vis.customer_id,
    COUNT(vis.customer_id) as count_no_trans
FROM
    Visits vis
LEFT JOIN
    Transactions tr
ON
    tr.visit_id = vis.visit_id
WHERE
    tr IS NULL
GROUP BY
    vis.customer_id;
	
-- OR better
SELECT
    vis.customer_id,
    COUNT(vis.customer_id) as count_no_trans
FROM
    Visits vis
WHERE
    NOT EXISTS (SELECT transaction_id FROM Transactions WHERE vis.visit_id = visit_id)
GROUP BY
    vis.customer_id;

-- https://leetcode.com/problems/rising-temperature/
SELECT
    today.id
FROM
    Weather today
LEFT JOIN
    Weather yesterday
ON
    today.recordDate - yesterday.recordDate = 1 AND
    today.temperature > yesterday.temperature;
	
-- OR
SELECT
    today.id
FROM
    Weather today
CROSS JOIN
    Weather yesterday
WHERE
    today.recordDate - yesterday.recordDate = 1 AND
    today.temperature > yesterday.temperature;

-- https://leetcode.com/problems/average-time-of-process-per-machine/
SELECT
    ma1.machine_id,
    ROUND(AVG(ma2.timestamp - ma1.timestamp)::numeric, 3) AS processing_time
FROM
    Activity ma1
JOIN
    Activity ma2
ON
    ma1.machine_id = ma2.machine_id AND
    ma1.process_id  = ma2.process_id AND
    ma2.activity_type = 'end' AND
    ma1.activity_type = 'start'
GROUP BY
    ma1.machine_id;

-- https://leetcode.com/problems/employee-bonus/
SELECT
    emp.name,
    bon.bonus
FROM
    Employee emp
LEFT JOIN
    Bonus bon
ON
    bon.empId = emp.empId
WHERE
    bon.bonus < 1000 OR bon IS NULL;

-- https://leetcode.com/problems/students-and-examinations/
SELECT
    stud.student_id,
    stud.student_name,
    sub.subject_name,
    COUNT(exam) AS attended_exams
FROM
    Students stud
CROSS JOIN
    Subjects sub
LEFT JOIN
    Examinations exam
ON
    stud.student_id = exam.student_id AND
    sub.subject_name = exam.subject_name
GROUP BY
    stud.student_id,
    stud.student_name,
    sub.subject_name
ORDER BY
    stud.student_id,
    stud.student_name;

-- https://leetcode.com/problems/managers-with-at-least-5-direct-reports/
SELECT
    m.name
FROM (
    SELECT
		managerId
    FROM
		Employee
    GROUP BY
		managerId
    HAVING
		COUNT(*) >= 5
) AS e
JOIN
	Employee m
ON
	e.managerId = m.id;

-- OR
SELECT
    man.name
FROM
    Employee man
LEFT JOIN
    Employee emp
ON
    emp.managerId = man.id
GROUP BY
    man.name, man.id
HAVING
    COUNT(emp) >= 5;

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
