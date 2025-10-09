/*
Write a query using a CTE to find the average movie rating for each director 
(artist who directed at least one movie).

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+---------------+---------------------+                                                                                                               
| director_name | avg_directed_rating |                                                                                                               
+---------------+---------------------+                                                                                                               
| John Doe      |                9.00 |                                                                                                               
| Alice Johnson |                9.00 |                                                                                                               
| Emma Stone    |                9.00 |                                                                                                               
| David Brown   |                8.50 |                                                                                                               
| Olivia Taylor |                8.50 |                                                                                                               
| Bob Smith     |                8.00 |                                                                                                               
| Chris Evans   |                8.00 |                                                                                                               
| Jane Doe      |                7.67 |                                                                                                               
+---------------+---------------------+   


*/

use mdb;
with avg_movie_rating as (
    select ROUND(AVG(r.rating),2) as avg_rating, d.Artist_id from Directions d join Reviews r on d.Movie_id = r.Movie_id 
    group by d.Artist_id
)
select a.name as director_name, r.avg_rating as avg_directed_rating from Artists a join avg_movie_rating r on a.id = r.Artist_id order by r.avg_rating desc;