CREATE TABLE city (
   id INT AUTO_INCREMENT PRIMARY KEY,
   city VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE cinema (
   id INT AUTO_INCREMENT PRIMARY KEY,
   cinema_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR(20) NOT NULL,
   address VARCHAR(20) NOT NULL,
   district VARCHAR(20) NOT NULL,
   ticket_price DOUBLE NOT NULL,
   enabled BOOLEAN NOT NULL,
   city_id INT,
   foreign key (city_id) references city(id)
);

