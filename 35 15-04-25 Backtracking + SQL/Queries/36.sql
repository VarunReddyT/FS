/* Write a SQL query to find all employees who do not receive a commission.

Expected Output Columns:
------------------------
+-------+-------+------+
| ename | empno | comm |
+-------+-------+------+

*/
USE test;
select ename, empno, comm from emp where comm in (0.00) or comm is NULL;