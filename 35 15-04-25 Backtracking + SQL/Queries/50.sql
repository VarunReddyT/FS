/* List employees who do not receive a commission but earn more than 2500.

Expected Output Columns:
------------------------
+-------+---------+------+
| ename | sal     | comm |
+-------+---------+------+

*/
USE test;

select ename, sal, comm from emp where comm is null and sal>2500;