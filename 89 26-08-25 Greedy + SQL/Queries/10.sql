/*
Write a query to find courses with atleast 10 students enrolled.


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
course_id   student_count                                                   
101             10                                                                      

*/

use univ;

select c.course_id, COUNT(e.student_id) as student_count from Courses c join Enrollments e using(course_id)
group by c.course_id
having student_count >= 10;