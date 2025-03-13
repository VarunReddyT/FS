/* Find departments that have at least one employee earning more than 3000.

Expected Output Columns:
------------------------
+--------+------------+
| deptno | dname      |
+--------+------------+

*/
USE test;
select e.deptno, d.dname from dept d join emp e on e.deptno=d.deptno where e.sal>3000;