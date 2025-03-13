/* Find the name and salary of the highest-paid employee.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;
select ename, sal from emp where sal = (select MAX(sal) from emp);