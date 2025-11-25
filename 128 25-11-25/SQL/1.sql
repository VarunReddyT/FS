/*
Write a SQL query to display the names of all students along with their 
department names.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
student_name    dept_name                                                       
Alice   Computer Science                                                        
Bob     Computer Science                                                        
Frank   Computer Science                                                        
*/

use sndb;

-- write your query here

select s.student_name, d.dept_name from students s join departments d on s.dept_id = d.dept_id;