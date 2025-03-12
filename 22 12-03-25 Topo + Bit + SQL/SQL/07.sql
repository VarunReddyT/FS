/* Retrieve employees who were hired between the years 1995 and 1999.

Expected Output Columns:
+--------+----------+
| ename  | hiredate |
+--------+----------+

*/
USE test;
select ename, hiredate from emp where YEAR(hiredate) between 1995 and 1999;