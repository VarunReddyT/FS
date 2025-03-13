/* Find employees whose salary is higher than the salary of ‘SCOTT’.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;
select ename, sal from emp where sal > (select max(sal) from emp where ename="SCOTT");