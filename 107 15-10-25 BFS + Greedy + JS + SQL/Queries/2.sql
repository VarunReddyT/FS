/*
Write a SQL query to find the top-performing student (highest average grade) 
in each semester.



================================================================================
courses: course_id, course_name, credits, teacher_id
results: result_id, student_id, course_id, grade, semester
students: student_id, name, enrollment_year, email
teachers: teacher_id, name, department, email
================================================================================

Output:
-------
+------------+------------+-------------------+-----------+                                                                                           
| semester   | student_id | name              | avg_grade |                                                                                           
+------------+------------+-------------------+-----------+                                                                                           
| Fall2022   |          1 | Rohan Sharma      |         0 |                                                                                           
| Fall2022   |          3 | Vikram Rao        |         0 |                                                                                           
| Fall2022   |          5 | Manoj Deshmukh    |         0 |                                                                                           
| Fall2022   |          7 | Karan Mehra       |         0 |                                                                                           
| Fall2022   |          9 | Tarun Patel       |         0 |                                                                                           
| Fall2022   |         11 | Nikhil Chatterjee |         0 |                                                                                           
| Fall2022   |         13 | Arjun Varma       |         0 |                                                                                           
| Fall2022   |         15 | Harsh Vardhan     |         0 |                                                                                           
| Spring2023 |          2 | Sneha Reddy       |         0 |                                                                                           
| Spring2023 |          4 | Aditi Singh       |         0 |                                                                                           
| Spring2023 |          6 | Ritika Jain       |         0 |                                                                                           
| Spring2023 |          8 | Ankita Das        |         0 |                                                                                           
| Spring2023 |         10 | Megha Bhat        |         0 |                                                                                           
| Spring2023 |         12 | Deepika Ghosh     |         0 |                                                                                           
| Spring2023 |         14 | Shruti Kulkarni   |         0 |                                                                                           
+------------+------------+-------------------+-----------+


*/

use UDB;
select r.semester, s.student_id, s.name, AVG(r.grade) as avg_grade from results r join students s on r.student_id = s.student_id
group by r.semester, s.student_id
order by r.semester