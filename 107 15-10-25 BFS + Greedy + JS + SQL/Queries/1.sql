/*
Write a SQL query to show the course name and teacher name.



================================================================================
courses: course_id, course_name, credits, teacher_id
results: result_id, student_id, course_id, grade, semester
students: student_id, name, enrollment_year, email
teachers: teacher_id, name, department, email
================================================================================

Output:
-------
+---------------------+-------------------+                                                                                                           
| course_name         | teacher_name      |                                                                                                           
+---------------------+-------------------+                                                                                                           
| Data Structures     | Dr. Anjali Mehta  |                                                                                                           
| Calculus I          | Prof. Rajesh Iyer |                                                                                                           
| Classical Mechanics | Dr. Nisha Verma   |                                                                                                           
| Organic Chemistry   | Prof. Sameer Shah |                                                                                                           
| Genetics            | Dr. Kavita Rao    |                                                                                                           
| Circuits            | Prof. Amit Kumar  |                                                                                                           
| Thermodynamics      | Dr. Pooja Joshi   |                                                                                                           
| Structural Analysis | Prof. Anil Sharma |                                                                                                           
| Digital Systems     | Dr. Sneha Patil   |                                                                                                           
| Networks            | Prof. Deepak Nair |                                                                                                           
| Linear Algebra      | Dr. Priya Bansal  |                                                                                                           
| Quantum Physics     | Prof. Kiran Desai |                                                                                                           
| Algorithms          | Dr. Rahul Singh   |                                                                                                           
| Inorganic Chemistry | Prof. Neha Kapoor |                                                                                                           
| Microbiology        | Dr. Alok Jain     |                                                                                                           
+---------------------+-------------------+   


*/

use UDB;
select c.course_name, t.name as teacher_name from courses c join teachers t on c.teacher_id = t.teacher_id