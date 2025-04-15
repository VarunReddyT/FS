/* Write a SQL query to count the number of employees who have a manager.

Expected Output Columns:
------------------------
+------------------------+
| employees_with_manager |
+------------------------+

*/
USE test;
select COUNT(empno) as employees_with_manager from emp where mgr is not null;