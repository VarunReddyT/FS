/* Find the department that has the most employees.

Expected Output Columns:
------------------------
+--------+----------------+
| deptno | employee_count |
+--------+----------------+

*/
USE test;
select deptno, COUNT(empno) as employee_count from emp group by deptno order by COUNT(empno) desc limit 1;