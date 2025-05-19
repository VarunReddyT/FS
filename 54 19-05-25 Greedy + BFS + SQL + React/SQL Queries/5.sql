/*

Find the names of products that are only ordered by customers who live in a
specific city (e.g., 'New York')

Sample Output:
==============

Name                                                                                                                    
Keyboard                                                                                                          
                                                                                            


Customers:
==========
Field   Type    Null    Key     Default Extra                                                                           
CustomerID      int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Email   varchar(255)    YES             NULL                                                                            
Address varchar(255)    YES             NULL                                                                            
PhoneNumber     varchar(20)     YES             NULL                                                                    

Orders:
=======
Field   Type    Null    Key     Default Extra                                                                           
OrderID int     NO      PRI     NULL                                                                                    
CustomerID      int     YES     MUL     NULL                                                                            
OrderDate       date    YES             NULL                                                                            
TotalCost       decimal(10,2)   YES             NULL                                                                    
Status  varchar(20)     YES             NULL                                                                            

OrderItems:
============
Field   Type    Null    Key     Default Extra                                                                           
OrderItemID     int     NO      PRI     NULL                                                                            
OrderID int     YES     MUL     NULL                                                                                    
ProductID       int     YES     MUL     NULL                                                                            
Quantity        int     YES             NULL                                                                            
UnitPrice       decimal(10,2)   YES             NULL                                                                    

Products:
=========
Field   Type    Null    Key     Default Extra                                                                           
ProductID       int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Description     varchar(255)    YES             NULL                                                                    
Price   decimal(10,2)   YES             NULL  


*/

use fs;

-- Write your query below.
select p.Name from Customers c join Orders o on c.CustomerID=o.CustomerID
join OrderItems oi on o.OrderID=oi.OrderID
join Products p on oi.ProductID=p.ProductID
group by p.ProductID, p.Name
having sum(case when c.Address not like "%New York%" THEN 1 ELSE 0 END) = 0;

-- SELECT DISTINCT p.Name
-- FROM Customers c
-- JOIN Orders o ON c.CustomerID = o.CustomerID
-- JOIN OrderItems oi ON o.OrderID = oi.OrderID
-- JOIN Products p ON oi.ProductID = p.ProductID
-- WHERE c.Address LIKE '%New York%'
--   AND p.ProductID NOT IN (
--     SELECT DISTINCT oi2.ProductID
--     FROM Customers c2
--     JOIN Orders o2 ON c2.CustomerID = o2.CustomerID
--     JOIN OrderItems oi2 ON o2.OrderID = oi2.OrderID
--     WHERE c2.Address NOT LIKE '%New York%'
--   );
