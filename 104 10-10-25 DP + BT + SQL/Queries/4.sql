/*
Write a SQL query using CTEs and ranking functions to identify the most active 
reviewer in each decade. 

Display:
--------
    decade
    reviewer_name
    review_count

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+---------------+--------------+                                                                                                             
| decade | reviewer_name | review_count |                                                                                                             
+--------+---------------+--------------+                                                                                                             
|   1970 | Bob Smith     |            1 |                                                                                                             
|   1980 | Jane Doe      |            2 |                                                                                                             
|   1990 | Alice Johnson |            1 |                                                                                                             
|   1990 | Olivia Taylor |            1 |                                                                                                             
|   1990 | Bob Smith     |            1 |                                                                                                             
|   2000 | David Brown   |            1 |                                                                                                             
|   2000 | John Doe      |            1 |                                                                                                             
|   2000 | Chris Evans   |            1 |                                                                                                             
|   2010 | Olivia Taylor |            1 |                                                                                                             
|   2010 | Chris Evans   |            1 |                                                                                                             
|   2020 | Emma Stone    |            1 |                                                                                                             
|   2020 | Alice Johnson |            1 |                                                                                                             
+--------+---------------+--------------+


*/

use mdb;

with reviewers as (
    select FLOOR(m.release_year/10)*10 as decade, a.name as reviewer_name, COUNT(*) as review_count from Reviews r join Artists a on r.Artist_id = a.id join Movies m on r.Movie_id = m.id
    group by decade, a.name
),
decade_reviews as (
    select decade, reviewer_name, review_count, DENSE_RANK() OVER (PARTITION BY decade ORDER BY review_count desc) as rnk from reviewers
)
select decade, reviewer_name, review_count from decade_reviews where rnk = 1 order by decade;
