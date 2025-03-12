/* Retrieve employees who earn a commission between 300 and 1000.

Expected Output Columns:
+--------+----------+
| ename  | comm     |
+--------+----------+

*/
USE test;
select ename, comm from emp where comm between 300 and 1000;