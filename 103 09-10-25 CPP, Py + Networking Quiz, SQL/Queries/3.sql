/*
Write a SQL query using two CTEs — one for all directors and another for all 
reviewers, to find the artists who have both directed and reviewed at least one movie.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+---------------+                                                                                                                                     
| artist_name   |                                                                                                                                     
+---------------+                                                                                                                                     
| John Doe      |                                                                                                                                     
| Jane Doe      |                                                                                                                                     
| Bob Smith     |                                                                                                                                     
| Alice Johnson |                                                                                                                                     
| David Brown   |                                                                                                                                     
| Olivia Taylor |                                                                                                                                     
| Chris Evans   |                                                                                                                                     
| Emma Stone    |                                                                                                                                     
+---------------+ 


*/

use mdb;

with directors as (
    select artist_id from Directions group by artist_id
),
reviewed as (
    select artist_id from Reviews group by Artist_id
)
select a.name as artist_name from Artists a join reviewed r on a.id = r.Artist_id join directors d on a.id = d.Artist_id;
