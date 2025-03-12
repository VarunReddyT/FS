/* List all employees whose job title is either "MANAGER" or "ANALYST".

Expected Output Columns:
+------------+-----------+
| ename      | job       |
+------------+-----------+


*/
USE test; 
select ename, job from emp where job in ("MANAGER","ANALYST");
