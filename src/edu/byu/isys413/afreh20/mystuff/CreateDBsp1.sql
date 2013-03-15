-- Note that comment lines need to end with a semicolon for CreateDB.java to work;

-- The primary keys (id) should really be CHAR(40), not VARCHAR(40), but;
-- to make life easier in testing, I've placed them as VARCHAR(40) for now;

-- First drop everything (order matters here for foreign keys!);

ALTER TABLE employee DROP FOREIGN KEY storeid;

DROP TABLE employee;
DROP TABLE storeprod;
DROP TABLE conceptualprod;
DROP TABLE physicalprod;
DROP TABLE store;
DROP TABLE product;
DROP TABLE customer;
DROP TABLE transaction;
DROP TABLE revenuesource;
DROP TABLE sale;
DROP TABLE commission;
DROP TABLE payment;
DROP TABLE journalentry;
DROP TABLE debitcredit;
DROP TABLE genledger;
DROP TABLE businessobject;




-- BUSINESSOBJECT TABLE (everything extends this);
CREATE TABLE businessobject (
  id           VARCHAR(40) PRIMARY KEY,
  botype       VARCHAR(250)
);

-- EMPLOYEE TABLE (extends PERSON table);
CREATE TABLE employee (
  id             VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  username       VARCHAR(250),
  -- password       VARCHAR(250),
  firstname    VARCHAR(250),
  lastname     VARCHAR(250),
  phone        VARCHAR(100),
  birthdate      Date,
  salary         NUMERIC(8,2)  DEFAULT 0,
  favoritenumber INT  DEFAULT 0,
  iq             INT  DEFAULT 0,
  distance       INT  DEFAULT 0,
  storeid     VARCHAR(40),
  empnum        INT  DEFAULT 0,
  hiredate      Date
);
INSERT INTO businessobject(id, botype) VALUES ('employee1', 'edu.byu.isys413.afreh20.mystuff.Employee');
-- INSERT INTO person(id, firstname, lastname, phone) VALUES ('employee1', 'Bart', 'Simpson', '801-555-0222');
INSERT INTO employee(id, firstname, lastname, phone) VALUES ('employee1', 'Bart', 'Simpson', '801-555-0222');
INSERT INTO businessobject(id, botype) VALUES ('employee2', 'edu.byu.isys413.afreh20.mystuff.Employee');
-- INSERT INTO person(id, firstname, lastname, phone) VALUES ('employee2', 'Homer', 'Simpson', '801-555-3456');
INSERT INTO employee(id, firstname, lastname, phone) VALUES ('employee2', 'Homer', 'Simpson', '801-555-3456');
INSERT INTO businessobject(id, botype) VALUES ('employee3', 'edu.byu.isys413.afreh20.mystuff.Employee');
INSERT INTO employee(id, username, firstname, lastname) VALUES ('employee3', 'afreh20', 'Anthony', 'Frehner');

CREATE TABLE store (
	id			VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	location 	VARCHAR(250),
	address 	VARCHAR(250),
	phone 		VARCHAR(50),
	salestaxrate NUMERIC(5,4)  DEFAULT 0,
	subnetid 	VARCHAR(250),
	managerid 	VARCHAR(40) REFERENCES employee(id),
  storenum INT  DEFAULT 0
);

ALTER TABLE employee ADD CONSTRAINT storeid FOREIGN KEY (storeid) REFERENCES store(id);

INSERT INTO businessobject(id, botype) VALUES ('store1', 'edu.byu.isys413.afreh20.mystuff.Store');
INSERT INTO store(id, address, phone, salestaxrate, managerid) VALUES ('store1', 'Bulldog Dr.', '801-422-4000', 0.0675, 'employee1');

UPDATE employee SET storeid='store1' WHERE id='employee1';
UPDATE employee SET storeid='store1' WHERE id='employee3';

CREATE TABLE product(
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  price DOUBLE DEFAULT 0,
  type VARCHAR(40),
  prod_num Integer,
  name VARCHAR(100)
);

CREATE TABLE conceptualprod
(
  id varchar(40) PRIMARY KEY REFERENCES product(id),
  -- name varchar(50),
  average_cost double  DEFAULT 0,
  manufacturer varchar(100),
  description varchar(50),
  cprod_num Integer REFERENCES product(prod_num),
  commission_rate double  DEFAULT 0,
  store_id VARCHAR(40) REFERENCES store(id)
);

INSERT INTO businessobject(id, botype) VALUES ('conceptual_product1', 'edu.byu.isys413.afreh20.mystuff.ConceptualProd');
INSERT INTO product(id, price, type, prod_num, name) VALUES ('conceptual_product1', '22.22', 'ConceptualProd', 1, 'ham');
INSERT INTO conceptualprod(id, description, commission_rate, store_id, cprod_num) VALUES ('conceptual_product1', 'delicious', .075, 'store1', 1);

CREATE TABLE storeprod
(
  id varchar(40) PRIMARY KEY REFERENCES businessobject(id),
  quantity double  DEFAULT 0,
  location varchar(50),
  store_id varchar(50) REFERENCES store(id),
  cprod_id varchar(50) REFERENCES conceptualprod(id)
);

INSERT INTO businessobject(id, botype) VALUES ('storeprod1', 'edu.byu.isys413.afreh20.mystuff.StoreProd');
INSERT INTO storeprod (id, quantity, location, store_id, cprod_id) VALUES ('storeprod1', 11, 'isle 2', 'store1', 'conceptual_product1');

CREATE TABLE physicalprod (
  id VARCHAR(40) PRIMARY KEY REFERENCES product(id),
  location VARCHAR(50),
  purchase_date DATE,
  cost DOUBLE  DEFAULT 0,
  status VARCHAR(50),
  commission_rate DOUBLE  DEFAULT 0,
  store_id VARCHAR(40) REFERENCES store(id),
  pprod_num Integer REFERENCES product(prod_num)
  -- cprod_id VARCHAR(40) REFERENCES conceptualprod(id)
);

INSERT INTO businessobject(id, botype) VALUES ('physprod1', 'edu.byu.isys413.afreh20.mystuff.PhysicalProd');
INSERT INTO product(id, price, type, prod_num, name) VALUES ('physprod1', 55.55, 'PhysicalProd', 2, 'camera');
INSERT INTO physicalprod(id, location, commission_rate, store_id, pprod_num) VALUES ('physprod1', 'isle 5', .078, 'store1', 2);

CREATE TABLE customer (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  firstname VARCHAR(40),
  lastname VARCHAR(40),
  phone VARCHAR(40),
  email VARCHAR(40),
  address VARCHAR(200),
  password VARCHAR(256),
  confirmation VARCHAR(40),
  isconfirmed BOOLEAN
);

INSERT INTO businessobject(id, botype) VALUES ('customer1', 'edu.byu.isys413.afreh20.mystuff.Customer');
INSERT INTO customer (id, firstname, lastname, phone, email, address) VALUES ('customer1', 'Bobby', 'Tables', '555-554-5555', 'bob@bob.com', '710 Home St.');
INSERT INTO businessobject(id, botype) VALUES ('anon', 'edu.byu.isys413.afreh20.mystuff.Customer');
INSERT INTO customer (id, firstname) VALUES ('anon', 'Anon Customer');

CREATE TABLE transaction (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  date DATE,
  subtotal DOUBLE  DEFAULT 0,
  tax DOUBLE  DEFAULT 0,
  total DOUBLE  DEFAULT 0,
  store_id VARCHAR(40) REFERENCES store(id),
  customer_id VARCHAR(40) REFERENCES customer(id),
  employee_id VARCHAR(40) REFERENCES employee(id),
  commissionTotal DOUBLE
);

INSERT INTO businessobject(id, botype) VALUES ('transaction1', 'edu.byu.isys413.afreh20.mystuff.Transaction');
INSERT INTO transaction (id, date, subtotal, tax, total, store_id, customer_id, employee_id) VALUES ('transaction1', '2012-12-12', 44.44, 2, 46.44, 'store1', 'customer1', 'employee1');

CREATE TABLE revenuesource (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  chargeamt DOUBLE  DEFAULT 0,
  type VARCHAR(40),
  transaction_id VARCHAR(40) REFERENCES transaction(id)
);

CREATE TABLE sale (
  id VARCHAR(40) PRIMARY KEY REFERENCES revenuesource(id),
  quantity DOUBLE  DEFAULT 0,
  product_id VARCHAR(40) REFERENCES product(id)
);

INSERT INTO businessobject(id, botype) VALUES ('sale1', 'edu.byu.isys413.afreh20.mystuff.Sale');
INSERT INTO revenuesource(id, chargeamt, type, transaction_id) VALUES ('sale1', 44.44, 'Sale', 'transaction1');
INSERT INTO sale (id, quantity, product_id) VALUES ('sale1', 2, 'conceptual_product1');

CREATE TABLE commission (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  employee_id VARCHAR(40) REFERENCES employee(id),
  amount DOUBLE  DEFAULT 0,
  date DATE,
  transaction_id VARCHAR(40) REFERENCES transaction(id)
);

INSERT INTO businessobject(id, botype) VALUES ('commission1', 'edu.byu.isys413.afreh20.mystuff.Commsission');
INSERT INTO commission (id, employee_id, amount, date) VALUES ('commission1', 'employee1', 5.55, '2012-12-12');

CREATE TABLE payment (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  amount DOUBLE  DEFAULT 0,
  pay_change DOUBLE  DEFAULT 0,
  type VARCHAR(40),
  transaction_id VARCHAR(40) REFERENCES transaction(id)
);

INSERT INTO businessobject(id, botype) VALUES ('payment1', 'edu.byu.isys413.afreh20.mystuff.Payment');
INSERT INTO payment (id, amount, pay_change, transaction_id) VALUES ('payment1', 50.00, 0, 'transaction1');

CREATE TABLE journalentry (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  date DATE,
  transaction_id VARCHAR(40) REFERENCES transaction(id)
);

INSERT INTO businessobject(id, botype) VALUES ('journalentry1', 'edu.byu.isys413.afreh20.mystuff.JournalEntry');
INSERT INTO journalentry (id, date, transaction_id) VALUES ('journalentry1', '2012-12-12', 'transaction1');

CREATE TABLE debitcredit (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  isdebit BOOLEAN,
  genledger_name VARCHAR(40),
  amount DOUBLE  DEFAULT 0,
  journalentry_id VARCHAR(40) REFERENCES journalentry(id),
  batchRun BOOLEAN
);

INSERT INTO businessobject(id, botype) VALUES ('debitcredit1', 'edu.byu.isys413.afreh20.mystuff.DebitCredit');
INSERT INTO debitcredit (id, isdebit, genledger_name, amount, journalentry_id, batchRun) VALUES ('debitcredit1', true, 'Cash', 55.55, 'journalentry1', true);

CREATE TABLE genledger (
  id VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  name VARCHAR(40),
  balance DOUBLE  DEFAULT 0,
  type VARCHAR(40)
);

INSERT INTO businessobject(id, botype) VALUES ('genledger1', 'edu.byu.isys413.afreh20.mystuff.GenLedger');
INSERT INTO genledger (id, name, balance) VALUES ('genledger1', 'Cash', 50000);
INSERT INTO businessobject(id, botype) VALUES ('genledger2', 'edu.byu.isys413.afreh20.mystuff.GenLedger');
INSERT INTO genledger (id, name, balance) VALUES ('genledger2', 'Sales Revenue', 500);
INSERT INTO businessobject(id, botype) VALUES ('genledger3', 'edu.byu.isys413.afreh20.mystuff.GenLedger');
INSERT INTO genledger (id, name, balance) VALUES ('genledger3', 'Sales Tax', 300);
INSERT INTO businessobject(id, botype) VALUES ('genledger4', 'edu.byu.isys413.afreh20.mystuff.GenLedger');
INSERT INTO genledger (id, name, balance) VALUES ('genledger4', 'Commission Expense', 50);
INSERT INTO businessobject(id, botype) VALUES ('genledger5', 'edu.byu.isys413.afreh20.mystuff.GenLedger');
INSERT INTO genledger (id, name, balance) VALUES ('genledger5', 'Commission Payable', 50);