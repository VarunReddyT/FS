/*
List email addresses of customers along with the names of food items they've 
ordered, where the food item's name contains exactly two consecutive vowels.



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
+-----------------------+----------------------+
| email                 | food_item            |
+-----------------------+----------------------+
| amit.sharma@gmail.com | Butter Naan          |
| arjun.gupta@gmail.com | Butter Naan          |
+-----------------------+----------------------+



*/

use GT;

-- Write your query here.
select c.email, f.name as food_item from Customers c join Orders o on o.customer_id=c.customer_id 
join FoodItems f on f.food_id=o.food_id 
where f.name REGEXP "[aeiouAEIOU][aeiouAEIOU]";