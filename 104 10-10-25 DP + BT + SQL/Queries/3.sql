/*
Write a SQL query using multi-level CTEs to determine which decades produced the 
most top-rated movies (average rating >= 8).

Display:
--------
    decade
    high_rating_count
    popularity_rank

Show the top 3 decades with the most highly rated movies.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+-------------------+-----------------+                                                                                                      
| decade | high_rating_count | popularity_rank |                                                                                                      
+--------+-------------------+-----------------+                                                                                                      
|   1990 |                 2 |               1 |                                                                                                      
|   1970 |                 1 |               2 |                                                                                                      
|   2000 |                 1 |               2 |                                                                                                      
|   2010 |                 1 |               2 |                                                                                                      
|   1980 |                 1 |               2 |                                                                                                      
|   2020 |                 1 |               2 |                                                                                                      
+--------+-------------------+-----------------+ 


*/

use mdb;

with decades as (
    select m.id, (FLOOR(m.release_year/10)*10) as decade from Movies m
),
rating_count as (
    select d.decade, COUNT(*) as high_rating_count from Reviews r join decades d on r.Movie_id = d.id group by r.Movie_id having AVG(r.rating) >= 8
),
decade_summary as (
    select decade, COUNT(*) as high_rating_count from rating_count group by decade
),
ranked as (
    select decade, high_rating_count, DENSE_RANK() OVER (ORDER BY high_rating_count DESC) as popularity_rank from decade_summary
)
select decade, high_rating_count, popularity_rank from ranked where popularity_rank <= 3;
