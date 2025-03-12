/* Get all the details of employees who do not belong to department 20.

*/
USE test;
select * from emp where deptno not in (20);