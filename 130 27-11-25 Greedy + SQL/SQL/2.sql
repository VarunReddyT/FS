/*
Write a SQL query to find the course(s) that have the maximum number of student 
enrollments. Display the course name and total number of enrolled students.
The result should be ordered by course_name.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );
--> enrollments (enrollment_id, student_id, course_id, semester, grade, enrollment_date);

Output:
-------
course_name         total_enrollments                                               
Computer Networks       4                                                       
Database Systems        4                                                       
Microprocessors         4                                                               
Operating Systems       4  

*/

use sndb;

-- write your query here

select c.course_name , count(e.student_id) as total_enrollments from courses c join enrollments e on c.course_id = e.course_id group by c.course_name 
having count(e.student_id) = (select count(e1.student_id) as st_count from enrollments e1 group by e1.course_id order by st_count desc limit 1 )
order by c.course_name; 