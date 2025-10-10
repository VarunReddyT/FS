/*
Write a SQL query using CTEs and conditional aggregation to list all movies that
were reviewed by their own director and at least one other reviewer. 

Display:
--------
    movie_name
    total_reviewers
    director_reviews

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+------------+-----------------+------------------+                                                                                                   
| movie_name | total_reviewers | director_reviews |                                                                                                   
+------------+-----------------+------------------+                                                                                                   
| Movie B    |               3 |                1 |                                                                                                   
+------------+-----------------+------------------+  


*/

use mdb;

with directorReviews as (
    select r.Movie_id, count(*) as director_reviews from Directions d join Reviews r on d.Artist_id = r.Artist_id and d.Movie_id = r.Movie_id group by r.Movie_id
),
totalReviews as (
    select r.Movie_id, count(*) as total_reviewers from Directions d join Reviews r on d.Movie_id = r.Movie_id group by r.Movie_id
)
select m.name as movie_name, t.total_reviewers, d.director_reviews from Movies m join directorReviews d on m.id = d.Movie_id join totalReviews t on m.id = t.Movie_id