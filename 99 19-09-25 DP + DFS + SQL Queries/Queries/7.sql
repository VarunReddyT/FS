/*

WAQ to find the products whose category price is above that category’s average (per-category)

Output:
=======

+------------+--------+---------------------+-------------+------------+---------------+--------------+
| product_id | sku    | name                | category    | unit_price | reorder_point | discontinued |
+------------+--------+---------------------+-------------+------------+---------------+--------------+
|          1 | KB-001 | Mechanical Keyboard | Peripherals |      79.99 |            30 |            0 |
|          4 | MN-004 | 34in Ultrawide      | Displays    |     499.00 |            10 |            0 |
|          7 | SD-007 | 2TB SSD             | Storage     |     179.00 |            15 |            0 |
|         10 | GC-010 | Mid-range GPU       | Components  |     349.00 |             8 |            0 |
|         11 | CP-011 | 8-core CPU          | Components  |     259.00 |            12 |            0 |
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

select * from products p
where p.unit_price > (select avg(p2.unit_price) from products p2 where p2.category = p.category);