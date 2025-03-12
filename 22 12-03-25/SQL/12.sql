/* Retrieve employees who do not report to any manager.

Expected Output Columns:
+------+----------+------------+
|ename |    job   |   mgr      |
+------+----------+------------+

*/
USE test;
select ename, job, mgr from emp where mgr is null;