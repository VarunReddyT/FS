/* Write a SQL query to sum the commissions for employees with salaries below 1500.

Expected Output Columns:
------------------------
+-----------------------+
| total_comm_low_salary |
+-----------------------+

*/
USE test;

select sum(comm) as total_comm_low_salary from emp where sal<1500;
