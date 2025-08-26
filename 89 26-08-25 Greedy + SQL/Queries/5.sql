/*

List students who scored the highest grade in each course.


-- 1. Teachers Table Fields
(
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    email VARCHAR(100) UNIQUE
);

-- 2. Students Table Fields
 (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    enrollment_year YEAR,
    email VARCHAR(100) UNIQUE
);

-- 3. Courses Table Fields
 (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100),
    credits INT,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)
);

-- 4. Results Table Fileds
 (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    grade CHAR(2),
    semester VARCHAR(10),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

Sample output:
--------------
name	        course_name	        grade
Rohan Sharma	Calculus I	        B+
Sneha Reddy	    Organic Chemistry	B
Vikram Rao	    Circuits	        B+




*/

use fstest;

select s.name, c.course_name, r.grade from students s join results r using(student_id) join courses c using(course_id) 
where r.grade = (select max(r2.grade) from results r2 where r2.course_id=r.course_id)
group by s.name, c.course_name, r.grade