CREATE TABLE product (
   id INT AUTO_INCREMENT PRIMARY KEY,
   product_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR(30) NOT NULL,
   image_url VARCHAR NOT NULL,
   price DOUBLE NOT NULL,
   enabled BOOLEAN NOT NULL
);

