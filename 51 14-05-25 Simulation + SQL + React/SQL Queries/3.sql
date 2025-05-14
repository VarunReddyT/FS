/*

List orders where the total cost is higher than the total cost of any other 
order made by the same customer:

Sample Output:
==============
OrderID CustomerID      TotalCost                                                                                       
1001    1       1250.00                                                                                                 
1002    2       850.00                                                                                                  
1005    4       450.00                                                                                                  
1006    5       550.00                                                                                                  
1007    6       250.00                                                                                                  
1008    7       1200.00                                                                                                 
1010    3       950.00      
                                                                                              


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
select o.OrderID, c.CustomerID, o.TotalCost from Customers c join Orders o on c.CustomerID=o.CustomerID
where o.TotalCost >= (select max(ord.TotalCost) from Orders ord where o.CustomerID=ord.CustomerID);