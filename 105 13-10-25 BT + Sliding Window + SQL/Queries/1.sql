/*
Write a query to find all movies that have received more than two reviews.
For each movie, display:
    Movie name
    Total number of reviews received

Hint: Use GROUP BY and HAVING after joining Movies and Reviews.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+------------------+---------------+                                                                                                                  
| movie_name       | total_reviews |                                                                                                                  
+------------------+---------------+                                                                                                                  
| Movie B          |             3 |                                                                                                                  
| The Quantum Code |             3 |                                                                                                                  
+------------------+---------------+  


*/

use mdb;
select m.name as movie_name, COUNT(r.id) as total_reviews from Reviews r join Movies m on r.Movie_id=m.id
group by m.name
having COUNT(r.id) > 2;