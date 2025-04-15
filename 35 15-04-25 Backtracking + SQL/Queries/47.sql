/* Write a SQL query to find the total salary of employees whose names start with 'K'.

Expected Output Columns:
------------------------
+----------------+
| total_salary_k |
+----------------+

*/
USE test;

select sum(sal) as total_salary_k from emp where ename LIKE 'K%';