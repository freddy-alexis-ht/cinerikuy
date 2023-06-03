-- TODO Creación de tabla que relaciona Transaction & ListaDeProductos
--CREATE TABLE city (
--   id INT AUTO_INCREMENT PRIMARY KEY,
--   city VARCHAR(20) NOT NULL UNIQUE
--);

CREATE TABLE transaction (
   id INT AUTO_INCREMENT PRIMARY KEY,
   transaction_code VARCHAR UNIQUE,
   customer_username VARCHAR(8) NOT NULL,
   customer_dni VARCHAR(8) NOT NULL,
   customer_name VARCHAR(40) NOT NULL,
   cinema_code VARCHAR NOT NULL,
   cinema_name VARCHAR(20) NOT NULL,
   cinema_location VARCHAR(60) NOT NULL,
   cinema_ticket_price INT NOT NULL,
   movie_code VARCHAR NOT NULL,
   movie_name VARCHAR NOT NULL,
   movie_schedule VARCHAR NOT NULL,
   movie_language VARCHAR NOT NULL,
   movie_number_of_tickets INT NOT NULL

);

