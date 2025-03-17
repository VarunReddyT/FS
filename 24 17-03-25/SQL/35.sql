/* Retrieve employees who have a commission greater than their salary.

Expected Output Columns:
------------------------
+--------+-------+---------+---------+
| ename  | empno | sal     | comm    |
+--------+-------+---------+---------+

*/
USE test;
select ename, empno, sal, comm from emp where comm > sal;