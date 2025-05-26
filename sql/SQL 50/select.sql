-- https://leetcode.com/problems/recyclable-and-low-fat-products/
SELECT product_id FROM Products WHERE low_fats = 'Y' AND recyclable = 'Y';

-- https://leetcode.com/problems/find-customer-referee/
SELECT name FROM Customer WHERE referee_id IS DISTINCT FROM 2;

-- https://leetcode.com/problems/big-countries/
SELECT name, population, area FROM World WHERE area >= 3000000 OR population >= 25000000;

-- https://leetcode.com/problems/article-views-i/
SELECT DISTINCT author_id as id FROM Views WHERE viewer_id = author_id ORDER BY author_id ASC;

-- https://leetcode.com/problems/invalid-tweets/
SELECT tweet_id FROM Tweets WHERE LENGTH(content) > 15;