/*

Show semester-wise grade distribution for the course ‘Algorithms’.


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
semester	grade	count
Fall2022	A+	    1



*/

use fstest;

select r.semester, r.grade, COUNT(r.student_id) as "count" from results r join courses c using(course_id)
where c.course_name = "Algorithms"
group by r.semester, r.grade;
