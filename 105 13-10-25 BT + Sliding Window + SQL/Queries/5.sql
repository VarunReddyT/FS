/*
Write a SQL query to show each director’s name and the number of unique 
reviewers who have reviewed their movies. Sort the output in descending order 
of unique reviewers.

Hint: Use JOIN across Artists, Directions, and Reviews and apply COUNT(DISTINCT ...).

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+---------------+------------------+                                                                                                                  
| director_name | unique_reviewers |                                                                                                                  
+---------------+------------------+                                                                                                                  
| Alice Johnson |                3 |                                                                                                                  
| Jane Doe      |                3 |                                                                                                                  
| Bob Smith     |                2 |                                                                                                                  
| Chris Evans   |                2 |                                                                                                                  
| David Brown   |                2 |                                                                                                                  
| Olivia Taylor |                2 |                                                                                                                  
| Emma Stone    |                1 |                                                                                                                  
| John Doe      |                1 |                                                                                                                  
+---------------+------------------+ 


*/

use mdb;
select a.name as director_name, COUNT(DISTINCT r.id) as unique_reviewers from Artists a join Directions d on a.id = d.Artist_id join Reviews r on r.Movie_id = d.Movie_id group by a.name
order by unique_reviewers desc;