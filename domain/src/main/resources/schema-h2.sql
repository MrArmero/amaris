
DROP TABLE IF EXISTS price;

CREATE TABLE price (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP,
  priority int NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  currency VARCHAR(4) NOT NULL DEFAULT 'EUR',
  product_id INT NOT  NULL,
  brand_id INT NOT NULL,
  creation_date TIMESTAMP NOT NULL,
  modification_date TIMESTAMP DEFAULT NULL
);
