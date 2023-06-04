INSERT INTO transaction (transaction_code, paid, customer_username, customer_dni, customer_name, cinema_code, cinema_name, cinema_location, cinema_ticket_price, movie_code, movie_name, movie_schedule, movie_language, movie_number_of_tickets)
VALUES ('TR1', 't', 'Domingo7', '12345678', 'Domingo Negro', 'C01', 'CR Centro Cívico', 'Av. Arequipa 1212, Breña, Lima', 12, 'M001', 'Domingo 007', '5:30', 'Español', 3);

INSERT INTO product_data (product_code, product_name, product_price, product_amount, transaction_id)
VALUES ('P01', 'Popcorn Pequeño', 7, 2, 1);
INSERT INTO product_data (product_code, product_name, product_price, product_amount, transaction_id)
VALUES ('P05', 'Bebida Pequeña (8oz)', 6, 2, 1);
