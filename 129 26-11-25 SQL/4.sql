/*
Write a SQL query to list the names of departments that offer fewer courses 
than the department of the student ‘Alice’.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Actual Output:
--------------
dept_name                                                                       
Electronics                                                                     
Mechanical  

*/

use sndb;

-- write your query here

with AliceDept as (
    select dept_id from students where student_name="Alice"
),
filtered as (
    select d.dept_id, d.dept_name, count(c.course_id) as course_count from departments d left join courses c on d.dept_id=c.dept_id 
    group by d.dept_id,d.dept_name
)
select dept_name from filtered 
where course_count < 
(
select course_count from filtered where dept_id = (select dept_id from AliceDept)
);