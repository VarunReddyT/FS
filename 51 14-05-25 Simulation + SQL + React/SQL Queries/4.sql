/*

Find the customers who placed orders that include more items than any other 
order they’ve placed:
Sample Output:
==============

OrderID Name    ItemCount                                                                                               
1010    Charlie Davis   2                                                                                               
1005    Diana Williams  2                                                                                               
1006    Ethan Brown     2                                                                                               
1007    Fiona Adams     1                                                                                               
1008    George Clark    2                                                                                               
                                                                                             


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
-- select o.OrderID, c.Name, count(*) as ItemCount from Customers c join Orders o on c.CustomerID=o.CustomerID 
-- join OrderItems oi on oi.OrderID=o.OrderID
-- group by c.CustomerID, o.OrderId
-- having count(*) >= all (select count(*) from Orders o1 join OrderItems i1 on i1.OrderID = o1.OrderID where o1.CustomerID = c.CustomerID and o1.OrderID = o.OrderID
-- group by o1.OrderID
-- )
-- (select max(itemc) from (select count(*) as itemc from Orders ord 
-- join OrderItems ordi on ord.OrderID=ordi.OrderID where ord.CustomerID=c.CustomerID
-- group by ord.OrderID
-- ) as table_cnt);

select oi.OrderID, c.Name, count(*) as ItemCount from OrderItems oi 
join Orders o on  oi.OrderID = o.OrderID
join Customers c on c.CustomerID = o.CustomerID
group by oi.OrderID,c.Name,o.CustomerID
having ItemCount > all
(select count(*) from OrderItems oi2 
join Orders o2 on oi2.OrderID=o2.OrderID where 
o2.customerID = o.CustomerID and 
oi2.OrderID!=oi.OrderID group by 
oi2.OrderID)