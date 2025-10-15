/*
Write a SQL query to using a CTE, display each teacher’s total number of students 
across all their courses.


================================================================================
courses: course_id, course_name, credits, teacher_id
results: result_id, student_id, course_id, grade, semester
students: student_id, name, enrollment_year, email
teachers: teacher_id, name, department, email
================================================================================

Output:
-------
+-------------------+------------------+----------------+                                                                                             
| teacher_name      | department       | total_students |                                                                                             
+-------------------+------------------+----------------+                                                                                             
| Dr. Anjali Mehta  | Computer Science |              2 |                                                                                             
| Prof. Rajesh Iyer | Mathematics      |              2 |                                                                                             
| Dr. Nisha Verma   | Physics          |              2 |                                                                                             
| Prof. Sameer Shah | Chemistry        |              2 |                                                                                             
| Dr. Kavita Rao    | Biology          |              2 |                                                                                             
| Prof. Amit Kumar  | Electrical       |              2 |                                                                                             
| Dr. Pooja Joshi   | Mechanical       |              2 |                                                                                             
| Prof. Anil Sharma | Civil            |              2 |                                                                                             
| Dr. Sneha Patil   | Electronics      |              2 |                                                                                             
| Prof. Deepak Nair | Information Tech |              2 |                                                                                             
| Dr. Priya Bansal  | Mathematics      |              2 |                                                                                             
| Prof. Kiran Desai | Physics          |              2 |                                                                                             
| Dr. Rahul Singh   | Computer Science |              2 |                                                                                             
| Prof. Neha Kapoor | Chemistry        |              2 |                                                                                             
| Dr. Alok Jain     | Biology          |              2 |                                                                                             
+-------------------+------------------+----------------+ 


*/

use UDB;
with teachers_table as (
    select distinct teacher_id, name as teacher_name, department from teachers
)
select t.teacher_name, t.department, COUNT(r.student_id) as total_students from teachers_table t join courses c on t.teacher_id = c.teacher_id 
join results r on r.course_id = c.course_id
group by t.teacher_name, t.department