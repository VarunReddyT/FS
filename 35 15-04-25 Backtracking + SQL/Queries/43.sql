/* Write a SQL query to find the total commission paid to employees hired after 1995.

Expected Output Columns:
------------------------
+----------------------+
| total_comm_post_1995 |
+----------------------+

*/
USE test;

select sum(comm) as total_comm_post_1995 from emp where YEAR(hiredate) > 1995;