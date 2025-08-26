/*
Write a query that shows the number of students enrolled per course.


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
course_name         total_students                                                  
Database Systems        10                                                      
Operating Systems       9                                                       

*/

use univ;
select c.course_name, COUNT(e.student_id) as "total_students" from Courses c join Enrollments e using(course_id) 
group by c.course_name;

-- show tables;