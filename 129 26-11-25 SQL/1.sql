/*
Write a SQL query to list the names and dates of birth of students who have 
birthdays in the current month.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Actual Output:
--------------
student_name    dob                                                             
Frank           2000-11-09 

*/

use sndb;

-- write your query here
select student_name, dob from students where Month(dob)=month(curdate());