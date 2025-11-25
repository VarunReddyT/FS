/*
Display all courses offered by each department, along with the number 
of credits for each course.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
dept_name           course_name         credits                                         
Computer Science    Database Systems        4                               
Computer Science    Computer Networks       3                               
Computer Science    Operating Systems       4   

*/

use sndb;

-- write your query here
select d.dept_name, c.course_name, c.credits from courses c join departments d on c.dept_id = d.dept_id;