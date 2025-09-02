/*
Write a query to show each employee and the total number of employees in their 
department.


---------------
Database : univ
---------------

employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary INT,
    hire_date DATE
);

projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100),
    department VARCHAR(50)
);

employee_projects (
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);


Sample Output:
--------------
name    department      dept_count                                              
Charlie Engineering     3                                                       
Diana   Engineering     3                                                       

*/
use univ;
select e.name,e.department,(select count(e1.department) from employees e1 where e1.department=e.department) as dept_count from employees e order by e.department;