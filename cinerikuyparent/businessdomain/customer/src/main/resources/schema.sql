CREATE TABLE cr_customer (
   id INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(8) NOT NULL UNIQUE,
   --password VARCHAR(60) NOT NULL,
   first_name VARCHAR(20) NOT NULL,
   last_name VARCHAR(20) NOT NULL,
   dni VARCHAR(8) NOT NULL UNIQUE,
   cellphone VARCHAR(9) NOT NULL,
   --role VARCHAR(8) NOT NULL,
   enabled BOOLEAN NOT NULL
);
