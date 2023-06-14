CREATE TABLE cr_city (
   id INT AUTO_INCREMENT PRIMARY KEY,
   city VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE cr_cinema (
   id INT AUTO_INCREMENT PRIMARY KEY,
   cinema_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR(20) NOT NULL,
   address VARCHAR(20) NOT NULL,
   district VARCHAR(20) NOT NULL,
   ticket_price DOUBLE NOT NULL,
   enabled BOOLEAN NOT NULL,
   city_id INT NOT NULL,
   foreign key (city_id) references cr_city(id)
);