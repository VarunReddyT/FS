/* Find employees who have the same manager as ‘BLAKE’.

Expected Output Columns:
------------------------
+-------+------+
| ename | mgr  |
+-------+------+

*/
USE test;
select ename, mgr from emp where mgr = (select mgr from emp where ename="BLAKE") and ename!="BLAKE";