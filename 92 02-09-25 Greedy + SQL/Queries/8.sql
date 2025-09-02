/*
Write a query to find the maximum salary among employees who were hired after 
January 1, 2020.


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
name    hire_date       salary  max_recent_salary                               
Alice   2020-01-15      50000   71000                                           
Bob     2021-03-10      52000   71000                                           

*/
use univ;
select e.name,e.hire_date,e.salary,(select max(e1.salary) from employees e1 where e1.hire_date>"2020-01-01") as max_recent_salary from employees e 
where e.hire_date>"2020-01-01";