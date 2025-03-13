/* Find employees who earn more than the highest-paid employee in department 10.

Expected Output Columns:
------------------------
+-------+---------+--------+
| ename | sal     | deptno |
+-------+---------+--------+

*/
USE test;
select ename,sal,deptno from emp where sal > (select MAX(sal) from emp where deptno in (10));