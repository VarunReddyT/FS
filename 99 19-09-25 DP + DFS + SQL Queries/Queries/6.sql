/*

WAQ to find the products that never had an ADJUST movement

Output:
=======

+------------+--------+---------------------+-------------+------------+---------------+--------------+
| product_id | sku    | name                | category    | unit_price | reorder_point | discontinued |
+------------+--------+---------------------+-------------+------------+---------------+--------------+
|          1 | KB-001 | Mechanical Keyboard | Peripherals |      79.99 |            30 |            0 |
|          2 | MS-002 | Wireless Mouse      | Peripherals |      24.50 |            40 |            0 |
|          4 | MN-004 | 34in Ultrawide      | Displays    |     499.00 |            10 |            0 |
|          7 | SD-007 | 2TB SSD             | Storage     |     179.00 |            15 |            0 |
|          9 | RM-009 | 32GB DDR5           | Components  |      89.00 |            30 |            0 |
|         10 | GC-010 | Mid-range GPU       | Components  |     349.00 |             8 |            0 |
|         11 | CP-011 | 8-core CPU          | Components  |     259.00 |            12 |            0 |
|         12 | NB-012 | 15in Laptop         | Systems     |     899.00 |             5 |            0 |
+------------+--------+---------------------+-------------+------------+---------------+--------------+


Database:  inv_mgmt

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
*/

use inv_mgmt;

-- select p.product_id, p.sku, p.name, p.category, p.unit_price, p.reorder_point, p.discontinued 
-- from products p join stock_movements s on p.product_id = s.product_id
-- where movement_type NOT IN ('ADJUST')
-- group by p.product_id;
select p.*
from products p left join stock_movements s on p.product_id = s.product_id and s.movement_type='ADJUST'
where s.product_id is null