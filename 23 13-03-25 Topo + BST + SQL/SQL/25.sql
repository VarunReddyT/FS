/* Find employee who have the same job title as 'FORD'.

Expected Output Columns:
------------------------
+-------+---------+
| ename | job     |
+-------+---------+

*/
USE test;
select ename, job from emp where job in (select job from emp where ename="FORD");