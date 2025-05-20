/*

 Find orders where the total cost is higher than the total amount spent by at
 least one other

Sample Output:
==============

OrderID CustomerID      OrderDate       TotalCost       Status                                                          
1001    1       2024-10-10      1250.00 Shipped                                                                         
1002    2       2024-10-12      850.00  Processing                                                                      
1005    4       2024-10-13      450.00  Shipped                                                                         
1006    5       2024-10-12      550.00  Processing                                                                      
1008    7       2024-10-15      1200.00 Delivered                                                                       
1009    4       2024-10-14      300.00  Processing                                                                      
1010    3       2024-10-15      950.00  Shipped                                                                         
                                                                                                      
                                                                                            


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
select * from Orders where TotalCost > (select min(totalspent) from 
(select CustomerID, SUM(TotalCost) as totalspent from Orders group by CustomerID) as tablespent);