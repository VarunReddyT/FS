/*
Write a SQL query to find movies whose rating is greater than the average rating 
of that movie’s director’s other works. Display the movie name and rating.

Hint: Use a correlated subquery to dynamically calculate each director’s average.


================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+------------------+--------+                                                                                                                         
| movie_name       | rating |                                                                                                                         
+------------------+--------+                                                                                                                         
| Movie B          |      8 |                                                                                                                         
| Movie B          |      8 |                                                                                                                         
| Movie C          |      9 |                                                                                                                         
| The Quantum Code |     10 |                                                                                                                         
| The Quantum Code |     10 |                                                                                                                         
| Digital Mirage   |      9 |                                                                                                                         
| Retro Future     |      9 |                                                                                                                         
| Codebreakers     |     10 |                                                                                                                         
+------------------+--------+ 


*/

use mdb;

select m.name as movie_name, r.rating from Movies m join Directions d on m.id = d.Movie_id join Reviews r on m.id = r.Movie_id
where r.rating > 
(select avg(r2.rating) from Movies m2 join Directions d2 on m2.id = d2.Movie_id join Reviews r2 on m2.id = r2.Movie_id 
where d2.Artist_id = d.Artist_id)