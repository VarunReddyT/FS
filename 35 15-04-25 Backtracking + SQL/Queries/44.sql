/* Write a SQL query to find the maximum hire date (latest hire) in the emp table.

Expected Output Columns:
------------------------
+-------------+
| latest_hire |
+-------------+

*/
USE test;

select max(hiredate) as latest_hire from emp;