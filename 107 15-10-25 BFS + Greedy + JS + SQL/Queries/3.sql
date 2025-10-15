/*
Write a SQL query to get the highest grade achieved by each student in their 
latest semester.


================================================================================
courses: course_id, course_name, credits, teacher_id
results: result_id, student_id, course_id, grade, semester
students: student_id, name, enrollment_year, email
teachers: teacher_id, name, department, email
================================================================================

Output:
-------
+------------+-------------------+------------+---------------+                                                                                       
| student_id | name              | semester   | highest_grade |                                                                                       
+------------+-------------------+------------+---------------+                                                                                       
|          1 | Rohan Sharma      | Fall2022   | B+            |                                                                                       
|          2 | Sneha Reddy       | Spring2023 | B             |                                                                                       
|          3 | Vikram Rao        | Fall2022   | B+            |                                                                                       
|          4 | Aditi Singh       | Spring2023 | A-            |                                                                                       
|          5 | Manoj Deshmukh    | Fall2022   | B             |                                                                                       
|          6 | Ritika Jain       | Spring2023 | B+            |                                                                                       
|          7 | Karan Mehra       | Fall2022   | B             |                                                                                       
|          8 | Ankita Das        | Spring2023 | B+            |                                                                                       
|          9 | Tarun Patel       | Fall2022   | B-            |                                                                                       
|         10 | Megha Bhat        | Spring2023 | B+            |                                                                                       
|         11 | Nikhil Chatterjee | Fall2022   | B+            |                                                                                       
|         12 | Deepika Ghosh     | Spring2023 | A+            |                                                                                       
|         13 | Arjun Varma       | Fall2022   | B+            |                                                                                       
|         14 | Shruti Kulkarni   | Spring2023 | B+            |                                                                                       
|         15 | Harsh Vardhan     | Fall2022   | B+            |                                                                                       
+------------+-------------------+------------+---------------+


*/

use UDB;
select s.student_id, s.name, r.semester, MAX(r.grade) as highest_grade from results r right join students s on r.student_id = s.student_id
group by s.name, s.student_id, r.semester