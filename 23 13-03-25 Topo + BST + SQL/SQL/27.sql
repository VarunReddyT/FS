/* Find employees who were hired before all employees in department 30.

Expected Output Columns:
------------------------
+-------+------------+
| ename | hiredate   |
+-------+------------+

*/
USE test;
select ename, hiredate from emp where hiredate < all(select hiredate from emp where deptno=30);