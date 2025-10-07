/*
Write a query to display each movie name and its average rating, sorted in 
descending order of average rating.

================================================================================

Database: bcode

Tables:

Artists
-------
| id | name | email | phone_number |

Movies
------
| id | name | release_year | budget |

Directions
----------
| id | Artist_id | Movie_id |

Reviews
-------
| id | date_of_review | Artist_id | Movie_id | rating |

================================================================================

Sample Output:
--------------
+--------------------+------------+                                                                                     
| movie_name         | avg_rating |                                                                                     
+--------------------+------------+                                                                                     
| Movie A            |       9.00 |                                                                                     
| The Quantum Code   |       9.00 |                                                                                     
| The Silent Network |       9.00 |                                                                                     
| Digital Mirage     |       8.50 |                                                                                     
| Retro Future       |       8.50 |                                                                                     
| Movie C            |       8.00 |                                                                                     
| Codebreakers       |       8.00 |                                                                                     
| Movie B            |       7.67 |                                                                                     
+--------------------+------------+


*/
use bcode;
select m.name as movie_name, ROUND(AVG(r.rating),2) as avg_rating from Movies m join Reviews r on m.id = r.Movie_id
group by m.name
order by avg_rating desc;
