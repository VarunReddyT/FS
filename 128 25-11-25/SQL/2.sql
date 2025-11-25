/*
Write a query to find the total number of students in each department.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
dept_name           total_students                                                  
Computer Science    6                                                       
Electronics         5                                                               
Mechanical          4
*/

use sndb;

-- write your query here

select d.dept_name, COUNT(s.student_id) as total_students from students s join departments d on s.dept_id = d.dept_id
group by d.dept_name;