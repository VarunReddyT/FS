/* Find employees who belong to a department with no employees.

Expected Output Columns:
------------------------
+---------+
| dname   |
+---------+

*/
USE test;
select dname from dept where deptno not in (select deptno from emp);