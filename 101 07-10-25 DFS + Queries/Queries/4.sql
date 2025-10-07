/*
Display the movie(s) that have the highest average rating across all reviews.
If multiple movies have the same top rating, display all of them.

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
| name               | avg_rating |                                                                                     
+--------------------+------------+                                                                                     
| Movie A            |       9.00 |                                                                                     
| The Quantum Code   |       9.00 |                                                                                     
| The Silent Network |       9.00 |                                                                                     
+--------------------+------------+ 


*/

use bcode;
select m.name as movie_name, ROUND(AVG(r.rating),2) as avg_rating from Movies m join Reviews r on m.id = r.Movie_id
group by m.name
having AVG(r.rating) = (select MAX(av_rating) from (select AVG(rating) as av_rating from Reviews group by Movie_id) as tmp)
order by avg_rating desc;
