/* Write a SQL query to determine the minimum salary for employees hired in the 1990s.

Expected Output Columns:
------------------------
+----------------+
| min_salary_90s |
+----------------+

*/
USE test;

select min(sal) as "min_salary_90s" from emp where YEAR(hiredate) in (1990,1991,1992,1993,1994,1995,1996,1997,1998,1999);