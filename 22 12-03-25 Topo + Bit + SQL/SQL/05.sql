/* Get the list of employees who do not have the job title "CLERK" or "SALESMAN".

Expected Output Columns:
+--------+----------+
| ename  | job      |
+--------+----------+

*/
USE test;
select ename, job from emp where job not in ("CLERK","SALESMAN");