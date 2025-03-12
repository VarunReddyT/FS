/* Get employees who are not managers and have a salary below 2000.

Expected Output Columns:
+------+----------+------------+
|ename |    job   |   sal      |
+------+----------+------------+

*/
USE test;
select ename, job, sal from emp where job not in ("MANAGER") and sal<2000;