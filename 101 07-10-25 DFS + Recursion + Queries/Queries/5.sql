/*
Write a query to find the movie(s) with the Nth highest average rating.
Use a variable @N to select which rank to display.
Show movie name and average rating.

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
+----------------+------------+                                                                                         
| name           | avg_rating |                                                                                         
+----------------+------------+                                                                                         
| Digital Mirage |       8.50 |                                                                                         
| Retro Future   |       8.50 |                                                                                         
+----------------+------------+ 


*/
use bcode;
select m.name, ROUND(AVG(r.rating),2) as avg_rating from Movies m join Reviews r on m.id = r.Movie_id
group by m.name
having AVG(r.rating) = (select distinct AVG(rating) from Reviews group by Movie_id order by AVG(rating) desc limit 1 offset 1)
order by avg_rating desc;
