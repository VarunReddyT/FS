/*
Write a SQL query to display the names of all directors whose movies have 
an average rating higher than the overall average rating of all movies.

Hint: Use a nested subquery in the HAVING clause to compare the director’s 
average rating with the global average.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+---------------+                                                                                                                                     
| director_name |                                                                                                                                     
+---------------+                                                                                                                                     
| John Doe      |                                                                                                                                     
| Alice Johnson |                                                                                                                                     
| David Brown   |                                                                                                                                     
| Olivia Taylor |                                                                                                                                     
| Emma Stone    |                                                                                                                                     
+---------------+ 


*/

use mdb;
select a.name as director_name from Directions d join Artists a on d.Artist_id = a.id join Reviews r on d.Movie_id = r.Movie_id 
group by a.name
having AVG(r.rating) > (select avg(rating) from Reviews);