/*
Display full names and phone numbers of customers whose phone numbers start 
with '98' or '87' and who have placed at least one delivered order.



-- Customers Table
==================
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(15),
    address TEXT


-- FoodItems Table
==================
    food_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(8,2) NOT NULL,
    category VARCHAR(50),
    availability BOOLEAN DEFAULT TRUE


-- Orders Table
================
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    food_id INT NOT NULL,
    quantity INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Preparing', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    total_amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (food_id) REFERENCES FoodItems(food_id)


Sample Output:
==============
+-------------+------------+
| full_name   | phone      |
+-------------+------------+
| Amit Sharma | 9876543210 |
| Priya Singh | 8765432109 |
+-------------+------------+



*/

use GT;

-- Write your query here.
select CONCAT(c.first_name," ",c.last_name) as full_name, c.phone from Customers c join Orders o on c.customer_id=o.customer_id
where c.phone REGEXP "^(98|87)"
group by o.customer_id
having count(o.order_id)>1;
