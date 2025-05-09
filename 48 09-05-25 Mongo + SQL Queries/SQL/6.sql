/*
Categorize each customer's total spending on delivered orders as 'High' (>500), 
'Medium' (201-500), or 'Low' (≤200).



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

+-------------+-------------+-------------------+
| full_name   | total_spent | spending_category |
+-------------+-------------+-------------------+
| Amit Sharma |      730.00 | High              |
| Priya Singh |      120.00 | Low               |
| Rahul Verma |      610.00 | High              |
+-------------+-------------+-------------------+


*/

use GT;

-- Write your query here.
SELECT full_name, total_spent, (CASE WHEN total_spent > 500 THEN 'High' WHEN (total_spent >= 200 AND total_spent <= 500) THEN 'Medium' ELSE 'LOW' END )
AS spending_category
FROM 
(SELECT CONCAT(first_name, " ", last_name) as 'full_name', SUM(total_amount) as 'total_spent'
FROM Customers c JOIN Orders o On o.customer_id = c.customer_id
JOIN FoodItems f ON f.food_id = o.food_id
WHERE status = 'Delivered'
GROUP BY o.customer_id) AS t2