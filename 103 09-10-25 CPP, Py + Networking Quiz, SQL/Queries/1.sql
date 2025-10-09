/*
Write a SQL query using a CTE to display all movies whose average rating is 
greater than 8.
Your output should show:
    - movie_name
    - avg_rating (rounded to two decimal places)
    - Sort the results by avg_rating in descending order.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------

+--------------------+------------+                                                                                                                   
| movie_name         | avg_rating |                                                                                                                   
+--------------------+------------+                                                                                                                   
| Movie A            |       9.00 |                                                                                                                   
| The Quantum Code   |       9.00 |                                                                                                                   
| The Silent Network |       9.00 |                                                                                                                   
| Digital Mirage     |       8.50 |                                                                                                                   
| Retro Future       |       8.50 |                                                                                                                   
+--------------------+------------+  

*/

use mdb;
with ratings as (
    select ROUND(AVG(rating),2) as avg_rating, Movie_id from Reviews group by Movie_id
)
select m.name as movie_name, r.avg_rating from Movies m join ratings r on m.id = r.Movie_id
where r.avg_rating > 8
order by r.avg_rating desc;