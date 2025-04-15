/* Write a SQL query to count the number of employees hired in each year.

Expected Output Columns:
------------------------
+-----------+----------------+
| hire_year | hires_per_year |
+-----------+----------------+

*/
USE test;

select YEAR(hiredate) as hire_year, count(YEAR(hiredate)) as hires_per_year from emp group by YEAR(hiredate);