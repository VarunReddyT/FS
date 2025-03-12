/* Retrieve employees who are part of department numbers between 20 and 40.

Expected Output Columns:
+--------+----------+
| ename  | deptno   |
+--------+----------+

*/
USE test;
select ename, deptno from emp where deptno between 20 and 40;