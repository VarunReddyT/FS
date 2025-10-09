/*
Write a SQL query using two separate CTEs to calculate:
1. The average rating of all movies released before 1980
2. The average rating of all movies released from 1980 onward

Then display the following columns:
    - pre1980_avg
    - post1980_avg
    - rating_difference ( difference between pre-1980 and post-1980 averages, 
      rounded to 2 decimals )

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+-------------+--------------+-------------------+                                                                                                    
| pre1980_avg | post1980_avg | rating_difference |                                                                                                    
+-------------+--------------+-------------------+                                                                                                    
|      9.0000 |       8.3333 |              0.67 |                                                                                                    
+-------------+--------------+-------------------+  

*/

use mdb;

with pre1980 as (
    select avg(r.rating) as pre1980_avg from Reviews r join Movies m on r.Movie_id = m.id where m.release_year < 1980
),
post1980 as (
    select avg(r.rating) as post1980_avg from Reviews r join Movies m on r.Movie_id = m.id where m.release_year >= 1980
)
select pre.pre1980_avg, post.post1980_avg, ROUND(pre.pre1980_avg - post.post1980_avg,2) as rating_difference from pre1980 pre, post1980 post;
