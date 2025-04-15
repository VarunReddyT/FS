/* Write a SQL query to determine the minimum commission value, excluding NULLs.

Expected Output Columns:
------------------------
+----------------+
| min_commission |
+----------------+

*/
USE test;

select min(comm) as min_commission from emp where comm is not null; 