/*
Write a query to list all courses whose average grade point is greater than 
the overall (global) average grade of all courses. The grade scale is: A = 4, 
B = 3, C = 2, other grades = 0. 

Concepts: Nested Subquery + CASE + AVG + HAVING


Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );
--> enrollments (enrollment_id, student_id, course_id, semester, grade, enrollment_date);

Output:
-------
course_name         avg_points                                                      
Control Systems     3.67                                                            
Fluid Mechanics     3.67  
Operating Systems   3.50                                                    
Thermodynamics      4.00                                                            


*/

use sndb;

-- write your query here

select c.course_name , 
round(avg(
    case
        when e.grade = 'A' then 4
        when e.grade = 'B' then 3
        when e.grade = 'C' then 2
        else 0
    end

),2 )as avg_points from 
courses c join enrollments e on c.course_id = e.course_id
group by c.course_name
having avg(
    case
        when e.grade = 'A' then 4
        when e.grade = 'B' then 3
        when e.grade = 'C' then 2
        else 0
    end

) > (select avg(
    case
        when grade = 'A' then 4
        when grade = 'B' then 3
        when grade = 'C' then 2
        else 0
    end

) as avg_points from enrollments) order by c.course_name;