/* Retrieve which employees have names starting with 'C' or 'S'.

Expected Output Columns:
+-------+--------+----------+------+------------+---------+---------+--------+
| empno | ename  | job      | mgr  | hiredate   | sal     | comm    | deptno |
+-------+--------+----------+------+------------+---------+---------+--------+

*/
USE test;
select * from emp where ename LIKE "C%" or ename LIKE "S%";