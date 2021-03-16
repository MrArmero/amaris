
INSERT INTO brand (name, creation_date, modification_date) VALUES
  ('ZARA', TO_DATE('1974-12-24', 'yyyy-MM-dd'), CURRENT_TIMESTAMP),
  ('Bershka', TO_DATE('1998-04-01', 'yyyy-MM-dd'), CURRENT_TIMESTAMP),
  ('Oysho', TO_DATE('1977-03-01', 'yyyy-MM-dd'), CURRENT_TIMESTAMP);

INSERT INTO product (description, creation_date, modification_date) VALUES
  ('Camiseta blanca', PARSEDATETIME('2020-06-01-00.00.00', 'yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP),
  ('Cartera de piel', PARSEDATETIME('2018-05-08-11.56.48', 'yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP),
  ('Zapatos negros', PARSEDATETIME('2019-07-16-16.51.23', 'yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP);

INSERT INTO price (start_date, end_date, priority, amount, currency, product_id, brand_id, creation_date, modification_date) VALUES
  (PARSEDATETIME('2020-06-14-00.00.00','yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-12-31-23.59.59','yyyy-MM-dd-HH.mm.ss'),0,'35.50','EUR',35455,1,PARSEDATETIME('2020-06-01-12.00.00','yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP),
  (PARSEDATETIME('2020-06-14-15.00.00','yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-06-14-18.30.00','yyyy-MM-dd-HH.mm.ss'),1,'25.45','EUR',35455,1,PARSEDATETIME('2020-06-01-12.00.00','yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP),
  (PARSEDATETIME('2020-06-15-00.00.00','yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-06-15-11.00.00','yyyy-MM-dd-HH.mm.ss'),1,'30.50','EUR',35455,1,PARSEDATETIME('2020-06-01-12.00.00','yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP),
  (PARSEDATETIME('2020-06-15-16.00.00','yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-12-31-23.59.59','yyyy-MM-dd-HH.mm.ss'),1,'38.95','EUR',35455,1,PARSEDATETIME('2020-06-01-12.00.00','yyyy-MM-dd-HH.mm.ss'), CURRENT_TIMESTAMP);
