/*

WAQ to find the warehouses with at least 3 distinct categories stored


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
=======
+--------------+------------+------+
| warehouse_id | name       | cats |
+--------------+------------+------+
|            1 | Central DC |    5 |
|            2 | North Hub  |    4 |
|            3 | South Hub  |    4 |
|            4 | West Hub   |    4 |
+--------------+------------+------+

*/

use inv_mgmt;

select w.warehouse_id, w.name, COUNT(distinct p.category) as cats from warehouses w join stock_movements s on w.warehouse_id = s.warehouse_id
join products p on s.product_id = p.product_id 
group by w.warehouse_id
having COUNT(p.category) > 2;