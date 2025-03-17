/* Write a SQL query to find employees whose name contains ‘A’.

Expected Output Columns:
------------------------
+--------+-------+
| ename  | empno |
+--------+-------+

*/
USE test;
select ename, empno from emp where ename like "%A%";