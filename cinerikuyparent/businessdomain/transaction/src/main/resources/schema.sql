CREATE TABLE cr_transaction (
   id INT AUTO_INCREMENT PRIMARY KEY,
   transaction_code VARCHAR UNIQUE,
   paid BOOLEAN NOT NULL,
   customer_username VARCHAR(8) NOT NULL,
   customer_dni VARCHAR(8) NOT NULL,
   customer_name VARCHAR(40) NOT NULL,
   cinema_code VARCHAR,
   cinema_name VARCHAR(20),
   cinema_location VARCHAR(60),
   cinema_ticket_price DOUBLE,
   movie_code VARCHAR,
   movie_name VARCHAR,
   movie_schedule VARCHAR,
   movie_language VARCHAR,
   movie_number_of_tickets INT
);

CREATE TABLE cr_product_data (
   id INT AUTO_INCREMENT PRIMARY KEY,
   product_code VARCHAR,
   product_name VARCHAR,
   product_price DOUBLE,
   product_amount INT,
   transaction_id INT NOT NULL,
   foreign key (transaction_id) references cr_transaction(id)
);

CREATE TABLE cr_billing (
   id INT AUTO_INCREMENT PRIMARY KEY,
   transaction_code VARCHAR NOT NULL,
   cinema_name VARCHAR NOT NULL,
   movie_name VARCHAR NOT NULL,
   movie_schedule VARCHAR NOT NULL,
   total_cost DOUBLE NOT NULL,
   date TIMESTAMP NOT NULL,
   transaction_id INT NOT NULL,
   foreign key (transaction_id) references cr_transaction(id)
);
