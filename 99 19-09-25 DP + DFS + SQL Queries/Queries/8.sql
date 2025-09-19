/*

WAQ to pick warehouses where no other warehouse has strictly greater on-hand 
for that product

Output:
=======
+------------+--------+--------------+------------+
| product_id | sku    | warehouse_id | name       |
+------------+--------+--------------+------------+
|         10 | GC-010 |            1 | Central DC |
|          5 | HD-005 |            1 | Central DC |
|          1 | KB-001 |            1 | Central DC |
|          3 | MN-003 |            1 | Central DC |
|          2 | MS-002 |            1 | Central DC |
|          8 | RM-008 |            1 | Central DC |
|          7 | SD-007 |            1 | Central DC |
|         11 | CP-011 |            2 | North Hub  |
|          6 | SD-006 |            2 | North Hub  |
|          4 | MN-004 |            3 | South Hub  |
|          9 | RM-009 |            3 | South Hub  |
|         10 | GC-010 |            4 | West Hub   |
|         12 | NB-012 |            4 | West Hub   |
+------------+--------+--------------+------------+


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

select p.product_id, p.sku, w.warehouse_id, w.name from products p join stock_movements s on p.product_id=s.product_id
join warehouses w on s.warehouse_id=w.warehouse_id
group by p.product_id, p.sku, w.warehouse_id, w.name
having sum(s.qty) = (select max(tmp.total_qty) from 
(select sum(s2.qty) as total_qty from stock_movements s2 where s2.product_id = p.product_id group by s2.warehouse_id) tmp)
order by w.warehouse_id, p.sku;