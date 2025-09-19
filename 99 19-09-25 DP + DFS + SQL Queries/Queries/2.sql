/*

Find the warehouses whose inventory value > overall average warehouse inventory value


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

Output:
-------
+--------------+------------+-----------+
| warehouse_id | name       | inv_value |
+--------------+------------+-----------+
|            1 | Central DC | 163990.00 |
+--------------+------------+-----------+

*/

use inv_mgmt;

select w.warehouse_id, w.name, sum(s.qty*s.unit_cost) as inv_value from warehouses w join stock_movements s
on w.warehouse_id = s.warehouse_id
group by warehouse_id
having sum(s.qty*s.unit_cost) > (select avg(tmp.inv_value) from 
(select sum(qty*unit_cost) as inv_value from stock_movements group by warehouse_id) tmp)
