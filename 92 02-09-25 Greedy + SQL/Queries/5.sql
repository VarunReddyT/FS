/*
Write a query that displays each employee's name, department, salary, and 
the average salary of employees in the same department.


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
name    department      salary  avg_dept_salary                                 
Charlie Engineering     70000   70000.0000                                      
Diana   Engineering     71000   70000.0000 

*/
use univ;
select e.name,e.department,e.salary,(select avg(e1.salary) from employees e1 where e1.department = e.department) as avg_dept_salary from employees e 
order by e.department,avg_dept_salary;