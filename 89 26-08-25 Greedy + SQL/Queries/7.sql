/*
Write a query to find the number of courses each student is enrolled in.


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
name    courses_enrolled                                                        
Alice   3                                                                       
Bob     3


*/

use univ;

select s.name, COUNT(e.course_id) as courses_enrolled from Students s join Enrollments e using(student_id)
group by name;