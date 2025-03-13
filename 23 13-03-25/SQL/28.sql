/* Find employees who belong to departments located in 'Dallas'.

Expected Output Columns:
------------------------
+-------+--------+
| ename | deptno |
+-------+--------+

*/
USE test;
select e.ename, d.deptno from emp e join dept d on e.deptno=d.deptno where d.location in ("Dallas"); 