/* Write a SQL query to find the average commission for salesmen, excluding NULLs.

Expected Output Columns:
------------------------
+-------------------+
| avg_salesman_comm |
+-------------------+

*/
USE test;

select avg(comm) as avg_salesman_comm from emp where job in ("SALESMAN") and job is not null;