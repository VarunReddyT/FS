/*
How many students enrolled in each year? Write a query using GROUP BY


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
enrollment_year student_count                                                   
2023            12                                                                      

*/

use univ;

select enrollment_year, COUNT(student_id) as student_count from Students s
group by enrollment_year;