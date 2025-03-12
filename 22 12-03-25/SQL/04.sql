/* Get the employee names and their commission details 
where the job is "CLERK" or the commission is more than 1000.

Expected Output Columns:
+--------+----------+---------+
| ename  | job      | comm    |
+--------+----------+---------+

*/
USE test;

select ename, job, comm from emp where job="CLERK" or comm>1000;