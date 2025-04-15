/* Write a SQL query to calculate the average salary and average commission of employees.

Expected Output Columns:
------------------------
+----------------+--------------------+
| Average Salary | Average Commission |
+----------------+--------------------+

*/
USE test;
select avg(sal) as "Average Salary", avg(comm) as "Average Commission" from emp;