/*

List each customer along with the number of orders they’ve placed.

Sample Output:
==============

CustomerID      Name    OrderCount                                                                                      
1       Alice Johnson   4                                                                                               
2       Bob Smith       3                                                                                               
3       Charlie Davis   3                                                                                               
4       Diana Williams  2                                                                                               
5       Ethan Brown     1                                                                                               
6       Fiona Adams     1                                                                                               
7       George Clark    1                                                                                               
8       Henry Taylor    0                                                                                               
9       Irene Green     0        

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
select c.CustomerID, c.Name, COUNT(o.OrderID) as OrderCount from Customers c left join Orders o on c.CustomerID=o.CustomerID
group by c.CustomerID, c.Name;