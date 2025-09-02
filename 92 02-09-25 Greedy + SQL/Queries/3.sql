/*
Write a query that assigns a unique row number to each employee in a department, 
ordered by their hire date.

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
name    department      hire_date       row_num                                 
Grace   Engineering     2018-12-05      1                                       
Charlie Engineering     2019-06-01      2 

*/
use univ;
select e.name,e.department,e.hire_date,(select count(e1.hire_date) from employees e1 where e1.department = e.department and e1.hire_date<e.hire_date)+1 as row_num from employees e 
order by e.department,row_num;