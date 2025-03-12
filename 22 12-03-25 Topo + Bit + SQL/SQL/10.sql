/* List all salary grades where the grade is between 2 and 4.

Expected Output Columns:
+------+----------+------------+
|grade |    losal |   hisal    |
+------+----------+------------+

*/
USE test;
select grade, losal, hisal from salgrade where grade between 2 and 4;