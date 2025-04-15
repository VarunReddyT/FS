/* Write a SQL query to find the difference between the highest and second highest salary.

Expected Output Columns:
------------------------
+-------------------+
| salary_difference |
+-------------------+

*/
USE test;
select MAX(sal) - (select MAX(sal) from emp where sal < (select MAX(sal) from emp)) as salary_difference from emp;