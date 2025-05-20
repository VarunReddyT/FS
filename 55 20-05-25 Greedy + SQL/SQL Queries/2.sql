/*

Find customers who have ordered the most expensive product.

Sample Output:
==============

CustomerID      Name    Email   Address PhoneNumber                                                                     
1       Alice Johnson   alice.johnson@example.com       123 Apple St, New York, NY      123-456-7890                    
7       George Clark    george.clark@example.com        213 Birch St, San Francisco, CA 555-666-7777                                                                              


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
select c.CustomerID, c.Name, c.Email, c.Address, c.PhoneNumber from Customers c join Orders o
on c.CustomerID=o.CustomerID
join OrderItems oi on o.OrderID=oi.OrderID
where oi.UnitPrice = (select max(Price) from Products);