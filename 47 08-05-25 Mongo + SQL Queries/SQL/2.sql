/*
List names, categories, and descriptions of food items containing exactly 
one occurrence of the letter 'y' (case-insensitive) in their descriptions, 
which have been ordered more than once.

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
+-------------+-----------+--------------------------------------------+
| name        | category  | description                                |
+-------------+-----------+--------------------------------------------+
| Gulab Jamun | Desserts  | Sweet dessert balls soaked in sugar syrup. |
| Mango Lassi | Beverages | Sweet yogurt-based mango drink.            |
+-------------+-----------+--------------------------------------------+

*/

use GT;

-- Write your query here.
select f.name, f.category, f.description from FoodItems f join Orders o on f.food_id=o.food_id 
where f.description REGEXP '^[^y]*y[^y]*$'
group by o.food_id
having count(o.order_id)>1 ;