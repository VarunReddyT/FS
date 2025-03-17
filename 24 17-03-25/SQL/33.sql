/* Find the department name where ‘JONES’ works.

Expected Output Columns:
------------------------
+----------+
| dname    |
+----------+

*/
USE test;
select d.dname from dept d join emp e on d.deptno = e.deptno and e.ename="JONES";