/* List employees with mgr 7788 or 7566 and sal < 3000

Expected Output Columns:
+----------+------+---------+
| Employee | Boss | Salary  |
+----------+------+---------+

*/
USE test;
select ename as Employee, mgr as Boss, sal as Salary from emp where mgr in (7788,7566) and sal<3000;