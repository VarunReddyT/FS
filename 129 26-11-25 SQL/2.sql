/*
Write a SQL query to find the youngest student in each department.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Actual Output:
--------------
student_name    dept_id dob                                                     
Diana           3       2003-01-25                                                      
Frank           1       2000-11-09                                                      
Jack            2       2001-02-03

*/

use sndb;

-- write your query here

SELECT s.student_name, s.dept_id, s.dob
FROM students s
JOIN (
    SELECT dept_id, MIN(dob) AS latest_dob
    FROM students
    GROUP BY dept_id
) x
ON s.dept_id = x.dept_id AND s.dob = x.latest_dob;
