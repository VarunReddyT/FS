/*
Write a SQL query to display the name(s) and credit values of the course(s) 
that have the highest number of credits.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Actual Output:
--------------
course_name         credits                                                         
Database Systems    4                                                       
Operating Systems   4                                                       
Thermodynamics      4   

*/

use sndb;

-- write your query here
with highest as (
    select max(credits) as highest from courses
)

select course_name, credits from courses where credits = (select * from highest) order by course_name;
