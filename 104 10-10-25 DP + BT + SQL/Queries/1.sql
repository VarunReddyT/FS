/*
Write a SQL query using CTEs and window functions to find the top 3 highest-rated 
movies in each decade. Display the following columns:
    decade
    movie_name
    avg_rating

The output should show the top 3 movies per decade based on average ratings, 
ordered by decade and descending rating.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+--------------------+------------+                                                                                                          
| decade | movie_name         | avg_rating |                                                                                                          
+--------+--------------------+------------+                                                                                                          
|   1970 | Movie A            |       9.00 |                                                                                                          
|   1980 | Retro Future       |       8.50 |                                                                                                          
|   1980 | Movie B            |       7.67 |                                                                                                          
|   1990 | The Silent Network |       9.00 |                                                                                                          
|   1990 | Movie C            |       8.00 |                                                                                                          
|   2000 | The Quantum Code   |       9.00 |                                                                                                          
|   2010 | Digital Mirage     |       8.50 |                                                                                                          
|   2020 | Codebreakers       |       8.00 |                                                                                                          
+--------+--------------------+------------+ 


*/

use mdb;

with decade_writing as (
    -- select id, name as movie_name, YEAR(release_year) as rel_year from Movies group by rel_year
    select Movie_id, avg(rating) as avg_rating from Reviews group by Movie_id
)
-- select (d.rel_year/10)*10 as decade, d.movie_name, avg(r.rating) from decade_writing d join Reviews r on d.id = r.Movie_id group by d.movie_name
select (FLOOR(m.release_year/10)*10) as decade, m.name as movie_name, ROUND(d.avg_rating,2) as avg_rating from decade_writing d join Movies m on d.Movie_id = m.id order by decade, avg_rating desc
