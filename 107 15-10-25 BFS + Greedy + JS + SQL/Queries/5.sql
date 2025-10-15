/*
Write a SQL query to for each course, find the student(s) who scored the maximum grade.



================================================================================
courses: course_id, course_name, credits, teacher_id
results: result_id, student_id, course_id, grade, semester
students: student_id, name, enrollment_year, email
teachers: teacher_id, name, department, email
================================================================================

Output:
-------
+---------------------+-------------------+-------+                                                                                                   
| course_name         | student_name      | grade |                                                                                                   
+---------------------+-------------------+-------+                                                                                                   
| Algorithms          | Shruti Kulkarni   | B+    |                                                                                                   
| Calculus I          | Rohan Sharma      | B+    |                                                                                                   
| Circuits            | Vikram Rao        | B+    |                                                                                                   
| Classical Mechanics | Tarun Patel       | B-    |                                                                                                   
| Data Structures     | Ankita Das        | B+    |                                                                                                   
| Digital Systems     | Manoj Deshmukh    | B     |                                                                                                   
| Genetics            | Megha Bhat        | B+    |                                                                                                   
| Inorganic Chemistry | Karan Mehra       | B     |                                                                                                   
| Linear Algebra      | Ritika Jain       | A-    |                                                                                                   
| Microbiology        | Harsh Vardhan     | B+    |                                                                                                   
| Networks            | Arjun Varma       | B+    |                                                                                                   
| Organic Chemistry   | Sneha Reddy       | B     |                                                                                                   
| Quantum Physics     | Ritika Jain       | B+    |                                                                                                   
| Structural Analysis | Deepika Ghosh     | A+    |                                                                                                   
| Thermodynamics      | Nikhil Chatterjee | B+    |                                                                                                   
+---------------------+-------------------+-------+                                                                                                   
                                                     


*/
use UDB;

select c.course_name, s.name AS student_name, r.grade from results r join courses c on r.course_id = c.course_id join students s on r.student_id = s.student_id
where (r.course_id, r.grade) IN (
    select course_id, MAX(grade) from results group by course_id
)
order by c.course_name;
