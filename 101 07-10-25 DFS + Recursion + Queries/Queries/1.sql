/*
Write a query to display the names of all artists who have directed at least 
one movie and have also written at least one review (for any movie).

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
+---------------+                                                                                                       
| name          |                                                                                                       
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
use bcode;

select name from Artists where id in (select Artist_id from Directions) and id in (select Artist_id from Reviews);

