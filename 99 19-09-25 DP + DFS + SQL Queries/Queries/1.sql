/*
Write a query to find the top 5 products by global on-hand quantity

Database: inv_mgmt

products:
=========
  product_id   INT PRIMARY KEY AUTO_INCREMENT,
  sku          VARCHAR(32) NOT NULL UNIQUE,
  name         VARCHAR(128) NOT NULL,
  category     VARCHAR(64)  NOT NULL,
  unit_price   DECIMAL(10,2) NOT NULL,
  reorder_point INT NOT NULL DEFAULT 0,
  discontinued TINYINT(1) NOT NULL DEFAULT 0


warehouses
===========
  warehouse_id INT PRIMARY KEY AUTO_INCREMENT,
  name         VARCHAR(64) NOT NULL,
  city         VARCHAR(64) NOT NULL,
  state        VARCHAR(64) NOT NULL,
  capacity     INT NOT NULL  -- conceptual: max on-hand units


stock_movements
================
  movement_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id    INT NOT NULL,
  warehouse_id  INT NOT NULL,
  movement_date DATE NOT NULL,
  movement_type ENUM('IN','OUT','ADJUST') NOT NULL,
  qty           INT NOT NULL,      -- positive for IN/ADJUST(add), negative for OUT/ADJUST(remove)
  unit_cost     DECIMAL(10,2) NULL,
  reference     VARCHAR(64) NULL,
  CONSTRAINT fk_sm_prod FOREIGN KEY (product_id) REFERENCES products(product_id),
  CONSTRAINT fk_sm_wh   FOREIGN KEY (warehouse_id) REFERENCES warehouses(warehouse_id),
  INDEX ix_sm_prod_wh_date (product_id, warehouse_id, movement_date),
  INDEX ix_sm_date (movement_date)
  
  
Output
------

+------------+--------+---------+                                                                                       
| product_id | sku    | on_hand |                                                                                       
+------------+--------+---------+                                                                                       
|          5 | HD-005 |     630 |                                                                                       
|          8 | RM-008 |     365 |                                                                                       
|          1 | KB-001 |     360 |                                                                                       
|          2 | MS-002 |     360 |                                                                                       
|          6 | SD-006 |     255 |                                                                                       
+------------+--------+---------+
  
*/

use inv_mgmt;

-- Method 1: Using JOIN

select p.product_id, p.sku, sum(s.qty) as on_hand from products p join stock_movements s on p.product_id=s.product_id
group by p.product_id, p.sku
order by on_hand desc, p.product_id
limit 5;

-- Method 2: Using Subquery

-- select p.product_id, p.sku, tmp.on_hand from (select product_id, sum(qty) as on_hand from stock_movements group by product_id) tmp
-- join products p on p.product_id = tmp.product_id 
-- order by tmp.on_hand desc, p.sku
-- limit 5;