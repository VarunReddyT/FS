/*
Write a SQL query to list all students who belong to departments having more 
than one course with 4 credits.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Actual Output:
--------------
student_name                                                                    
Alice                                                                           
Bob                                                                             
Frank                                                                           
Isabella                                                                        
Liam                                                                            
Noah 

*/

use sndb;

-- write your query here
with DeptsFour as (
    select d.dept_id from departments d where (select count(c.course_id) from courses c where c.dept_id=d.dept_id and c.credits=4)>1
)

select student_name from students where dept_id in (select dept_id from DeptsFour);