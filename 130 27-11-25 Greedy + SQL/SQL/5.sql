
/*
Write a SQL query to display the third-ranked course (in descending order of 
average grade) that is still above the global average. Show the course name and 
its average grade point.

✅ Simplified Query (Nested + OFFSET)

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );
--> enrollments (enrollment_id, student_id, course_id, semester, grade, enrollment_date);

Output:
-------
course_name     avg_grade                                                       
Fluid Mechanics 3.6667

*/

use sndb;

-- write your query here

select c.course_name , 
avg(
    case
        when e.grade = 'A' then 4
        when e.grade = 'B' then 3
        when e.grade = 'C' then 2
        else 0
    end

)as avg_grade from 
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

) as avg_points from enrollments) order by avg_grade desc limit 2, 1;