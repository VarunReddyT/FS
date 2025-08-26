/*
Find students enrolled in more than 2 courses using GROUP BY and HAVING.


---------------
Database Schema
---------------

Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100),
    enrollment_year INT
);

Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    instructor VARCHAR(50)
);

Enrollments (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade CHAR(1),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);


Sample Output:
--------------
student_id  course_count                                                    
1               3                                                                       
2               3                                                                       


*/

use univ;

select s.student_id, COUNT(e.course_id) as course_count from Students s join Enrollments e using(student_id) 
group by s.student_id
having course_count > 2;