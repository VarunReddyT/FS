/* Write a SQL query to calculate the average salary of employees with a commission.

Expected Output Columns:
------------------------
+----------------------+
| avg_salary_with_comm |
+----------------------+

*/
USE test;

select avg(sal) as avg_salary_with_comm from emp where comm is not null;