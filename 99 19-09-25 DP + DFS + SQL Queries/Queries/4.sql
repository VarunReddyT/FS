/*

WAQ to find the warehouses that hold more than 40% of a product’s global stock (dominant holder)


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
  
Sample output:
==============

+--------------+------------+--------+--------+-----------+
| warehouse_id | name       | sku    | wh_qty | total_qty |
+--------------+------------+--------+--------+-----------+
|            1 | Central DC | KB-001 |    160 |       360 |
|            1 | Central DC | MS-002 |    240 |       360 |
|            1 | Central DC | MN-003 |     50 |        77 |
|            1 | Central DC | MN-004 |     20 |        43 |
|            1 | Central DC | HD-005 |    390 |       630 |
|            1 | Central DC | SD-007 |     50 |        90 |
|            1 | Central DC | RM-008 |    285 |       365 |
|            1 | Central DC | GC-010 |     18 |        36 |
|            1 | Central DC | NB-012 |      8 |        18 |
|            2 | North Hub  | SD-006 |    130 |       255 |
|            2 | North Hub  | CP-011 |     32 |        57 |
|            3 | South Hub  | MN-004 |     23 |        43 |
|            3 | South Hub  | SD-006 |    125 |       255 |
|            3 | South Hub  | RM-009 |     80 |        80 |
|            3 | South Hub  | CP-011 |     25 |        57 |
|            4 | West Hub   | SD-007 |     40 |        90 |
|            4 | West Hub   | GC-010 |     18 |        36 |
|            4 | West Hub   | NB-012 |     10 |        18 |
+--------------+------------+--------+--------+-----------+
*/

use inv_mgmt;

select w.warehouse_id, w.name, p.sku, tmp.wh_qty, tmp2.total_qty
from (select warehouse_id, product_id, sum(qty) as wh_qty from stock_movements group by warehouse_id, product_id) tmp
join (select product_id, sum(qty) as total_qty from stock_movements group by product_id) tmp2
on tmp.product_id = tmp2.product_id
join warehouses w on w.warehouse_id=tmp.warehouse_id
join products p on p.product_id = tmp.product_id
where tmp.wh_qty > 0.4*tmp2.total_qty
order by tmp.warehouse_id, p.sku;