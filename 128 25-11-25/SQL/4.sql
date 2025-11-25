/*
List the names of all female students who belong to the “Computer Science” 
department.

Schema:
--> departments ( dept_id, dept_name );
--> students (student_id, student_name, gender, dob, dept_id);
--> courses ( course_id, course_name, credits, dept_id );

Output:
-------
student_name                                                                    
Alice                                                                           
Isabella 

*/

use sndb;

-- write your query here
select s.student_name from students s join departments d on s.dept_id = d.dept_id 
where s.gender in ("Female") and d.dept_name in ("Computer Science")