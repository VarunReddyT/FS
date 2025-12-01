/*
Write a SQL query to display the names, department IDs, and dates of birth 
of students who are older than the average age of students in their department.
The result should be ordered by dept_id.

Concepts: Correlated Subquery + Date Functions (TIMESTAMPDIFF)

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );
--> enrollments (enrollment_id, student_id, course_id, semester, grade, enrollment_date);

Output:
-------
student_name    dept_id dob                                                     
Bob             1       2001-06-20                                                      
Frank           1       2000-11-09                                                      
Liam            1       2000-12-25                                                      
Jack            2       2001-02-03                                                      
Mia             2       2001-10-10

*/

use sndb;

-- write your query here
select s.student_name, s.dept_id, s.dob from students s where TIMESTAMPDIFF(Year,s.dob,curdate()) > 
(
select avg(TIMESTAMPDIFF(year,s1.dob,curdate())) from students s1 where s1.dept_id = s.dept_id 
) order by dept_id;