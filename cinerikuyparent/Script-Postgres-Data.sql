--------------------
-- TABLE CREATION --
--------------------

DROP TABLE IF EXISTS cr_customer CASCADE;
CREATE TABLE cr_customer (
   id SERIAL PRIMARY KEY,
   username VARCHAR(8) NOT NULL UNIQUE,
   password VARCHAR(60) NOT NULL,
   first_name VARCHAR(20) NOT NULL,
   last_name VARCHAR(20) NOT NULL,
   dni VARCHAR(8) NOT NULL UNIQUE,
   cellphone VARCHAR(9) NOT NULL,
   role VARCHAR(8) NOT NULL,
   enabled BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS cr_product CASCADE;
CREATE TABLE cr_product (
   id SERIAL PRIMARY KEY,
   product_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR(30) NOT NULL,
   image_url VARCHAR NOT NULL,
   price REAL NOT NULL,
   enabled BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS cr_genre CASCADE;
CREATE TABLE cr_genre (
   id SERIAL PRIMARY KEY,
   genre VARCHAR(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS cr_language CASCADE;
CREATE TABLE cr_language (
   id SERIAL PRIMARY KEY,
   language VARCHAR(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS cr_situation CASCADE;
CREATE TABLE cr_situation (
   id SERIAL PRIMARY KEY,
   situation VARCHAR(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS cr_vote CASCADE;
CREATE TABLE cr_vote (
   id SERIAL PRIMARY KEY,
   vote VARCHAR(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS cr_movie CASCADE;
CREATE TABLE cr_movie (
   id SERIAL PRIMARY KEY,
   movie_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR NOT NULL,
   duration VARCHAR NOT NULL,
   image_url VARCHAR NOT NULL,
   image_cover VARCHAR NOT NULL,
   trailer_url VARCHAR NOT NULL,
   synopsis VARCHAR NOT NULL,
   peruvian BOOLEAN NOT NULL,
   director VARCHAR NOT NULL,
   actors VARCHAR NOT NULL,
   schedules VARCHAR NOT NULL,
   cinema_codes VARCHAR NOT NULL,
   enabled BOOLEAN NOT NULL,
   genre_id INT NOT NULL,
   language_id INT NOT NULL,
   situation_id INT NOT NULL,
   vote_id INT NOT NULL,
   FOREIGN KEY (genre_id) REFERENCES cr_genre(id),
   FOREIGN KEY (language_id) REFERENCES cr_language(id),
   FOREIGN KEY (situation_id) REFERENCES cr_situation(id),
   FOREIGN KEY (vote_id) REFERENCES cr_vote(id)
);

DROP TABLE IF EXISTS cr_voting CASCADE;
CREATE TABLE cr_voting (
    movie_id INT NOT NULL,
    username VARCHAR NOT NULL,
    FOREIGN KEY(movie_id) REFERENCES cr_movie(id),
    PRIMARY KEY(movie_id, username)
);

DROP TABLE IF EXISTS cr_city CASCADE;
CREATE TABLE cr_city (
   id SERIAL PRIMARY KEY,
   city VARCHAR(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS cr_cinema CASCADE;
CREATE TABLE cr_cinema (
   id SERIAL PRIMARY KEY,
   cinema_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR(20) NOT NULL,
   address VARCHAR(20) NOT NULL,
   district VARCHAR(20) NOT NULL,
   ticket_price REAL NOT NULL,
   enabled BOOLEAN NOT NULL,
   city_id INT NOT NULL,
   FOREIGN KEY (city_id) REFERENCES cr_city(id)
);

DROP TABLE IF EXISTS cr_transaction CASCADE;
CREATE TABLE cr_transaction (
   id SERIAL PRIMARY KEY,
   transaction_code VARCHAR(20) UNIQUE,
   paid BOOLEAN NOT NULL,
   customer_username VARCHAR(8) NOT NULL,
   customer_dni VARCHAR(8) NOT NULL,
   customer_name VARCHAR(40) NOT NULL,
   cinema_code VARCHAR,
   cinema_name VARCHAR(20),
   cinema_location VARCHAR(60),
   cinema_ticket_price REAL,
   movie_code VARCHAR,
   movie_name VARCHAR,
   movie_schedule VARCHAR,
   movie_language VARCHAR,
   movie_number_of_tickets INT
);

DROP TABLE IF EXISTS cr_product_data CASCADE;
CREATE TABLE cr_product_data (
   id SERIAL PRIMARY KEY,
   product_code VARCHAR,
   product_name VARCHAR,
   product_price REAL,
   product_amount INT,
   transaction_id INT NOT NULL,
   FOREIGN KEY (transaction_id) REFERENCES cr_transaction(id)
);

DROP TABLE IF EXISTS cr_billing CASCADE;
CREATE TABLE cr_billing (
   id SERIAL PRIMARY KEY,
   transaction_code VARCHAR NOT NULL,
   cinema_name VARCHAR NOT NULL,
   movie_name VARCHAR NOT NULL,
   movie_schedule VARCHAR NOT NULL,
   total_cost REAL NOT NULL,
   date TIMESTAMP NOT NULL,
   transaction_id INT NOT NULL,
   FOREIGN KEY (transaction_id) REFERENCES cr_transaction(id)
);

--------------------
-- DATA INSERTION --
--------------------

INSERT INTO cr_customer (username, password, first_name, last_name, dni, cellphone, role, enabled)
VALUES ('Domingo7', '$2a$10$SIF3MorTm.GeiarU.opNiOIfaBXSwCYHS.UmpmOrPrhLNBlea.v86', 'Domingo', 'Negro', '12345678', '987654321', 'Customer', 't');
INSERT INTO cr_customer (username, password, first_name, last_name, dni, cellphone, role, enabled)
VALUES ('Lucas2', '$2a$10$SIF3MorTm.GeiarU.opNiOIfaBXSwCYHS.UmpmOrPrhLNBlea.v86', 'Lucas', 'Barbón', '87654321', '987654322', 'Customer', 't');
INSERT INTO cr_customer (username, password, first_name, last_name, dni, cellphone, role, enabled)
VALUES ('Claudio3', '$2a$10$SIF3MorTm.GeiarU.opNiOIfaBXSwCYHS.UmpmOrPrhLNBlea.v86', 'Claudio', 'Gallo', '56784321', '987654323', 'Admin', 't');

INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P01', 'Popcorn Pequeño', 'https://media.informabtl.com/wp-content/uploads/2017/04/bigstock-Red-and-white-popcorn-box-isol-26091221-e1493245264752.jpg', 7, 't');
INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P02', 'Popcorn Mediano', 'https://s3.cine3.com/2020/06/popcorn.jpg', 10, 't');
INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P03', 'Popcorn Grande', 'https://media.mykaramelli.com/galeria/articulos/cubo-palomitas-rojo_5628_1.jpg', 12.5, 't');
INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P05', 'Bebida Pequeña (8oz)', 'https://media.istockphoto.com/id/964608132/es/vector/cristal-de-papel-3d-aislado-vaso-cart%C3%B3n-realista-ilustraci%C3%B3n-de-cola-red-vector.jpg?s=612x612&w=0&k=20&c=aR2n4ZEdwOEBh0l_lbtEktHJleYzlVUxbLYGCShvROE=', 6, 't');
INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P06', 'Bebida Mediana (10oz)', 'https://st2.depositphotos.com/2757384/12405/i/950/depositphotos_124059350-stock-photo-coca-cola-paper-cup-coca.jpg', 9, 't');
INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P07', 'Bebida Grande (12oz)', 'https://st2.depositphotos.com/1081688/5184/i/950/depositphotos_51848395-stock-photo-coca-cola-with-ice-cubes.jpg', 10.5, 't');
INSERT INTO cr_product (product_code, name, image_url, price, enabled)
VALUES ('P08', 'Hot-Dog', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/179?allowPlaceHolder=true', 6, 'f');

INSERT INTO cr_city (city) VALUES ('Lima');
INSERT INTO cr_city (city) VALUES ('Ica');
INSERT INTO cr_city (city) VALUES ('Arequipa');

INSERT INTO cr_cinema (cinema_code, name, address, district, ticket_price, city_id, enabled)
VALUES ('C01', 'CR Centro Cívico', 'Av. Arequipa 1212', 'Breña', 8, 1, 't');
INSERT INTO cr_cinema (cinema_code, name, address, district, ticket_price, city_id, enabled)
VALUES ('C02', 'CR Metro San Juan', 'Av. Próceres 1478', 'S.J.Lurigancho', 9, 1, 't');
INSERT INTO cr_cinema (cinema_code, name, address, district, ticket_price, city_id, enabled)
VALUES ('C03', 'CR El Quinde', 'Av. Central 1456', 'Molinos', 7, 2, 't');
INSERT INTO cr_cinema (cinema_code, name, address, district, ticket_price, city_id, enabled)
VALUES ('C04', 'CR Open Plaza', 'Av. Colorado 451', 'Cerro Colorado', 10, 3, 't');
INSERT INTO cr_cinema (cinema_code, name, address, district, ticket_price, city_id, enabled)
VALUES ('C05', 'CR Mall Plaza', 'Av. Yanahuara 4456', 'Yanahuara', 9, 3, 'f');

INSERT INTO cr_genre (genre) VALUES ('Acción');
INSERT INTO cr_genre (genre) VALUES ('Aventura');
INSERT INTO cr_genre (genre) VALUES ('Comedia');
INSERT INTO cr_genre (genre) VALUES ('Infantil');
INSERT INTO cr_genre (genre) VALUES ('Drama');
INSERT INTO cr_genre (genre) VALUES ('Terror');
INSERT INTO cr_genre (genre) VALUES ('Animación');
INSERT INTO cr_genre (genre) VALUES ('Documental');

INSERT INTO cr_language (language) VALUES ('Español');
INSERT INTO cr_language (language) VALUES ('Inglés (Sub)');
INSERT INTO cr_language (language) VALUES ('Quechua (Sub)');

INSERT INTO cr_situation (situation) VALUES ('Pasado');
INSERT INTO cr_situation (situation) VALUES ('Estreno');
INSERT INTO cr_situation (situation) VALUES ('Próximo');

INSERT INTO cr_vote (vote) VALUES ('Votación');
INSERT INTO cr_vote (vote) VALUES ('No-Votación');
INSERT INTO cr_vote (vote) VALUES ('Ganador');

-- ESTRENO (EN CARTELERA)
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M001', 'Rapidos y Furiosos X', '2hrs 30min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001614?referenceScheme=HeadOffice&allowPlaceHolder=true', 'https://i.ytimg.com/vi/O5BOxn8Go8U/maxresdefault.jpg', 'https://firebasestorage.googleapis.com/v0/b/appwallpaper-75b5c.appspot.com/o/trailer%2FFAST%20%26%20FURIOUS%20X%20Tr%C3%A1iler%202%20Espa%C3%B1ol%20(2023)%20(480p).mp4?alt=media&token=859986b9-672f-4a0d-9fc6-8c92ed95cc04', 'Dominic Toretto enfrenta un gran desafío que pone en peligro a sus compañeros. Toda la película se convierte en una lucha contra un villano bastante excéntrico, pero letal.', 'f', 'Louis Leterrier', 'Vin Diesel, Jason Momoa, Michelle Rodriguez, Brie Larson', '2:30,5:30,8:30', 'C01,C02,C03,C04', 't', 1, 1, 2, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M002', 'Spider-Man: A Través del Spider-Verso', '2hrs 20min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001547?referenceScheme=HeadOffice&allowPlaceHolder=true', 'https://res.cloudinary.com/dsrrzdwzq/image/upload/c_scale,w_448,h_252,dpr_2/f_auto,q_auto/v1684886514/spiderman-a-traves-del-spider-verso-nuevo-trailer-animado3.jpg?_i=AA', 'https://firebasestorage.googleapis.com/v0/b/appwallpaper-75b5c.appspot.com/o/trailer%2FSpider-Man_SpiderVerso.mp4?alt=media&token=b03356fa-d69c-4b23-96c6-9b62c20fd77a', 'Después de reunirse con Gwen Stacy, el amigable vecino de tiempo completo de Brooklyn Spiderman, es lanzado a través del multiverso, donde se encuentra a un equipo de gente araña encomendada con proteger su mera existencia.', 'f', 'Joaquim Dos Santos, Justin K. Thompson, Kemp Powers', 'Hailee Steinfeld, Shameik Moore, Oscar Isaac, Daniel Kaluuya', '2:45,5:30,8:15', 'C01,C02,C04', 't', 7, 1, 2, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M003', 'Transformers: El Despertar de las Bestias', '2hrs 10min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001615?referenceScheme=HeadOffice&allowPlaceHolder=true', 'https://phantom-marca-mx.unidadeditorial.es/68016166388901db4d9d297ea2d6c071/resize/828/f/jpg/mx/assets/multimedia/imagenes/2023/06/10/16864310596537.jpg', 'https://firebasestorage.googleapis.com/v0/b/appwallpaper-75b5c.appspot.com/o/trailer%2FTRANSFORMERS%207_%20EL%20DESPERTAR%20DE%20LAS%20BESTIAS%20Tr%C3%A1iler%20Espa%C3%B1ol%20Latino%20(2023)%20%E1%B4%B4%E1%B4%B0%20(480p).mp4?alt=media&token=d9258a9c-8f1e-4286-9638-179dff4c34d2', 'La batalla en la Tierra ya no es solo entre Autobots y Decepticons... Maximals, Predacons y Terrorcons se unen a Transformers: Rise of the Beasts.', 'f', 'Steven Caple Jr.', 'Ron Perlman, Michelle Yeoh, Anthony Ramos, Liza Koshy', '3:00,4:30,6:00,7:30,9:00', 'C01,C02,C03,C04', 't', 1, 1, 2, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M004', 'La Sirenita', '2hrs 15min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001565?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/KblOcFJkeBo', 'Anhelando conocer el mundo más allá del mar, una joven sirena visita la superficie y se enamora del apuesto príncipe Eric. Siguiendo su corazón, hace un trato con la malvada bruja del mar, Úrsula, para experimentar la vida en tierra.', 'f', 'Rob Marshall', 'Halle Bailey, Javier Bardem, Jonah Hauer-King, Melissa McCarthy', '3:00,6:00,9:00', 'C02,C03,C04', 't', 4, 1, 2, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M005', 'Soltera, Casada, Viuda, Divorciada', '1hrs 50min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001621?referenceScheme=HeadOffice&allowPlaceHolder=true', 'https://i.ytimg.com/vi/g_yqks0Rk2g/maxresdefault.jpg?sqp=-oaymwEmCIAKENAF8quKqQMa8AEB-AH-CYACpAWKAgwIABABGGUgYShBMA8=&rs=AOn4CLAMrOMCUmiKFUIVB7eevePNaY5smw', 'https://firebasestorage.googleapis.com/v0/b/appwallpaper-75b5c.appspot.com/o/trailer%2FSOLTERA%2C%20CASADA%2C%20VIUDA%2C%20DIVORCIADA%20-%20Trailer%20oficial%20(480p).mp4?alt=media&token=3cfca6d6-2a80-42ea-8206-a1af1dac6ff2', 'Comedia que cuenta la historia del reencuentro de 4 amigas de la infancia que la vida y los años se encargó de separar. Una soltera – Lorena (40) -, una casada – Daniela (40) -, una viuda – Cecilia (41) - y una divorciada – Conny (42). La muerte del esposo de Cecilia las une en un inesperado viaje a Pacasmayo.', 't', 'Ani Alva Helfer', 'Gianella Neyra, Diego Bertie, Milene Vásquez, Patricia Portocarrero, Katia Condos', '2:00,5:00', 'C01,C03', 't', 3, 1, 2, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M006', 'Boogeyman: Tu miedo es real', '1hrs 40min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001697?referenceScheme=HeadOffice&allowPlaceHolder=true', 'https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2023/05/boogeyman-hombre-saco-2023-3046494.jpg?tf=3840x', 'https://firebasestorage.googleapis.com/v0/b/appwallpaper-75b5c.appspot.com/o/trailer%2FBoogeyman_%20Tu%20Miedo%20Es%20Real%20(2023)%20Tr%C3%A1iler%20Oficial%20%232%20Espa%C3%B1ol%20Latino%20(480p).mp4?alt=media&token=4d2097ae-2b27-4d40-acb4-d583f3206ca4', 'Basada en uno de los cortos relatos de Stephen King, esta película es una adaptación libre que narra una historia paralela a la ya conocida en el libro Night Shift.', 'f', 'Rob Savage', 'Sophie Thatcher, David Dastmalchian, Madison Hu, Chris Messina', '2:00,5:00,8:00', 'C01,C04', 't', 5, 2, 2, 2);
-- PASADO (AUN EN CARTELERA)
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M007', 'Guardianes de la Galaxia VOL. 3', '2hrs 30min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001564?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/lOTB5HZKEvQ', 'Sigue a Star-Lord, todavía recuperándose de la pérdida de Gamora, que debe reunir a su equipo para defender el universo junto con la protección de uno de los suyos. Una misión que, si no se completa, podría llevar al final de los Guardianes tal como los conocemos.', 'f', 'James Gunn', 'Chris Pratt, Zoe Saldaña, Sean Gunn, Karen Gillan, Dave Bautista', '2:30,5:30,8:30', 'C01,C02,C04', 't', 1, 1, 1, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M008', 'Cuando Ellas Quieren Más', '1hrs 50min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001613?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/4k9ugJLdDQc', 'Cuatro mujeres llevan su club de lectura a Italia para el divertido viaje de chicas que nunca tuvieron. Cuando las cosas se salen de control y se revelan algunos secretos, sus relajantes vacaciones se convierten en una aventura única en la vida.', 'f', 'Bill Holderman', 'Diane Keaton, Jane Fonda, Mary Steenburgen, Candice Bergen', '2:30,5:30,8:30', 'C01,C03,C04', 't', 3, 1, 1, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M009', 'Super Mario Bros: La Película', '1hrs 32min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001611?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/U09rlkyW3nw', 'Dos hermanos plomeros, Mario y Luigi, caen por las alcantarillas y llegan a un mundo subterráneo mágico en el que deben enfrentarse al malvado Bowser para rescatar a la princesa Peach, quien ha sido forzada a aceptar casarse con él.', 'f', 'Aaron Horvath, Michael Jelenic', 'Jack Black, Anya Taylor-Joy, Chris Pratt, Charlie Day, Seth Rogen', '2:15,5:15,8:15', 'C02,C03,C04', 't', 7, 1, 1, 2);
-- PRÓXIMO ESTRENO
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M010', 'The Flash (15 junio 2023)', '2hrs 30min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001555?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/USX4IVV9E0s', 'Los mundos chocan en "Flash" cuando Barry usa sus superpoderes para viajar en el tiempo y cambiar los eventos del pasado. Pero cuando su intento de salvar a su familia altera el futuro sin darse cuenta, Barry queda atrapado en una realidad en la que el general Zod ha regresado, amenazando con la aniquilación, y no hay superhéroes a los que recurrir.', 'f', 'Andrés Muschietti', 'Ezra Miller, Michael Keaton, Ben Affleck, Sasha Calle, Michael Shannon', '2:00,5:45,7:30', 'C01,C02,C03,C04', 't', 1, 1, 3, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M011', 'Elemental (22 junio 2023)', '2hrs 0min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001566?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/7_x-Mv2Nbfs', 'Elemental es una próxima película de fantasía romántica animada por computadora estadounidense producida por Walt Disney Pictures y Pixar Animation Studios y distribuida por Walt Disney Studios Motion Pictures.', 'f', 'Peter Sohn', 'Leah Lewis, Mamoudou Athie, Catherine OHara, Joe Pera', '-', 'C02,C03', 't', 7, 1, 3, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M012', 'Kraken y Sirenas: Conoce a los Gillman (29 junio 2023)', '2hrs 0min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001657?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://www.youtube.com/watch?v=9njwwO5eXnA', 'Sumérgete en las turbulentas aguas de una escuela con una divertida y sincera comedia de acción sobre una tímida adolescente que descubre que forma parte de un legendario linaje real de míticos krakens marinos y que su destino, en las profundidades de los océanos, es más grande de lo que jamás había soñado.', 'f', 'Kirk DeMicco', 'Annie Murphy, Lana Condor, Jane Fonda, Blue Chapman', '-', '-', 't', 7, 1, 3, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M013', 'Indiana Jones y el Dial del destino (29 junio 2023)', '2hrs 0min', 'https://latecla-repos-dw6.aplinews.com/archivos/noticias/fotografias/161262_3.jpg', '-', 'https://youtu.be/dC1E_E78R48', 'Quinta película de la saga que sigue las aventuras del arqueólogo Indiana Jones.', 'f', 'James Mangold', 'Harrison Ford, Mads Mikkelsen, Phoebe Waller-Bridge, Antonio Banderas', '-', 'C01,C02,C03,C04', 't', 1, 1, 3, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M014', 'La Pampa (29 junio 2023)', '1hrs 50min', 'https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/FilmPosterGraphic/HO00001590?referenceScheme=HeadOffice&allowPlaceHolder=true', '-', 'https://youtu.be/eT13MMKG5F0', 'Juan huye de la Justicia y de la tragedia que lo marcó de por vida. Reina, una adolescente, huye de los abusos sexuales y morales que ha sufrido desde niña en los campamentos de explotación sexual de La Pampa.', 't', 'Dorian Fernández-Moris', 'Mayella Lloclla, Gonzalo Molina, Óscar Carrillo, Antonieta Pari, Silvia Majo', '-', 'C01,C03', 't', 5, 1, 3, 2);
-- VOTACIÓN (PELÍCULAS PERUANAS)
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M015', 'Retablo (2019)', '1hrs 40min', 'https://pics.filmaffinity.com/Retablo-896515213-large.jpg', '-', '-', 'En un aislado pueblo de Perú, la vida de un joven de 14 años se transforma radicalmente cuando descubre un secreto de su padre.', 't', 'Álvaro Delgado Aparicio', 'Amiel Cayo, Mauro Chuchón, Magaly Solier, Ubaldo Huamán, Júnior Béjar', '-', '-', 't', 5, 1, 1, 1);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M016', 'Días de Santiago (2004)', '1hrs 23min', 'https://m.media-amazon.com/images/M/MV5BOTExMmYxMzMtZjJjNi00NmU5LWEyYWItY2QzNDZhMzQ5MDkzXkEyXkFqcGdeQXVyMjUxOTQ5MzA@._V1_.jpg', '-', '-', 'Un ex SEAL (Pietro Sibille) de violentas tendencias tiene dificultades para adaptarse a la vida civil en los barrios bajos de Lima, Perú.', 't', 'Josué Méndez', 'Pietro Sibille, Milagros Vidal, Alheli Castillo, Lili Urbina, Marisela Puicón', '-', '-', 't', 5, 1, 1, 1);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M017', 'Pacificum, el retorno al océano (2017)', '1hrs 15min', 'https://pics.filmaffinity.com/Pacificum_El_retorno_al_ocaeano-720593553-large.jpg', '-', '-', 'Documental que estudia la historia, desarrollo, fauna y flora de la costa peruana. Un paleóntologo, un especialista en urbanismo prehispánico, un biólogo marino y una experta en ecoturismo conjugan sus conocimientos con las espectaculares imágenes.', 't', 'Mariana Tschudi', 'Rodolfo Salas, Milene Vásquez, Belén Alcorta, Jose Canziani', '-', '-', 't', 8, 1, 1, 1);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M018', 'Asu Mare (2013)', '1hrs 40min', 'https://pics.filmaffinity.com/aAsu_Mare-357198135-large.jpg', '-', '-', 'Una comedia que narra las aventuras del artista Carlos Alcántara en su camino hacia la fama, mostrando su infancia en la Unidad Vecinal de Mirones en Lima, sus años en la juventud y la relación con su madre.', 't', 'Ricardo Maldonado', 'Carlos Alcántara, Carlos Carlín, Emilia Drago, Franco Cabrera', '-', '-', 't', 3, 1, 1, 1);
-- NO-VOTACIÓN
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M019', 'Wiñaypacha (2017)', '1hrs 30min', 'https://pics.filmaffinity.com/winaypacha-927144275-large.jpg', '-', '-', 'Willka y Phaxsi, una pareja de ancianos de más de ochenta años que viven abandonados en un lugar remoto de los Andes del Perú, enfrentan la miseria y el paso del tiempo mientras esperan que llegue su único hijo a rescatarlos.', 't', 'Óscar Catacora', 'Vicente Catacora, Rosa Nina', '-', '-', 't', 5, 1, 1, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M020', 'Soltera codiciada (2018)', '1hrs 44min', 'https://pics.filmaffinity.com/Soltera_codiciada-602399099-large.jpg', '-', '-', 'Una escritora intenta superar una ruptura amorosa al escribir un blog sobre los altibajos de la vida como soltera.', 't', 'Joanna Lombardi, Bruno Ascenzo', 'Gisela Ponce de León, Jely Reátegui, Karina Jordán, Christopher Uckermann', '-', '-', 't', 3, 1, 1, 2);
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M021', 'Hasta que nos volvamos a encontrar (2022)', '1hrs 36min', 'https://pics.filmaffinity.com/Soltera_codiciada-602399099-large.jpg', '-', '-', 'Salvador Campodónico es un exitoso empresario español, cuya familia es dueña de la corporación hotelera más grande de toda España. Para la construcción de su primer proyecto internacional eligen a una maravilla mundial: Cusco. Allí, conoce a Ariana.', 't', 'Bruno Ascenzo', 'Maxi Iglesias, Stephanie Cayo, Carlos Carlín, Nicolás Galindo', '-', '-', 't', 5, 1, 1, 2);
-- NO-VOTACIÓN
INSERT INTO cr_movie (movie_code, name, duration, image_url, image_cover, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M022', 'Magallanes (2015)', '1hrs 49min', 'https://pics.filmaffinity.com/Magallanes-908587551-large.jpg', '-', '-', 'La vida de un taxista da un giro inesperado el día que una mujer que conoció hace 25 años, en los violentos años en que fue un soldado del Ejército peruano y luchaba contra el grupo terrorista Sendero Luminoso, se sube a su vehículo, teniendo un reencuentro inesperado con su pasado oscuro.', 't', 'Salvador del Solar', 'Damián Alcázar, Magaly Solier, Federico Luppi, Bruno Odar, Christian Meier', '-', '-', 't', 5, 1, 1, 3);


INSERT INTO cr_voting (movie_id, username)
VALUES (2, 'Domingo7');

INSERT INTO cr_transaction (transaction_code, paid, customer_username, customer_dni, customer_name, cinema_code, cinema_name, cinema_location, cinema_ticket_price, movie_code, movie_name, movie_schedule, movie_language, movie_number_of_tickets)
VALUES ('TR1', 't', 'Domingo7', '12345678', 'Domingo Negro', 'C01', 'CR Centro Cívico', 'Av. Arequipa 1212, Breña, Lima', 12, 'M001', 'Domingo 007', '5:30', 'Español', 3);
INSERT INTO cr_transaction (transaction_code, paid, customer_username, customer_dni, customer_name, cinema_code, cinema_name, cinema_location, cinema_ticket_price, movie_code, movie_name, movie_schedule, movie_language, movie_number_of_tickets)
VALUES ('TR2', 'f', 'Domingo7', '12345678', 'Domingo Negro', 'C01', 'CR Centro Cívico', 'Av. Arequipa 1212, Breña, Lima', 10, 'M001', 'Domingo 007', '5:30', 'Español', 4);

INSERT INTO cr_product_data (product_code, product_name, product_price, product_amount, transaction_id)
VALUES ('P01', 'Popcorn Pequeño', 7, 2, 1);
INSERT INTO cr_product_data (product_code, product_name, product_price, product_amount, transaction_id)
VALUES ('P05', 'Bebida Pequeña (8oz)', 6, 2, 1);

INSERT INTO cr_billing (transaction_code, cinema_name, movie_name, movie_schedule, total_cost, date, transaction_id)
VALUES ('TR1', 'CR Centro Cívico', 'Domingo 007', '5:30', 62, '2022-12-31 23:59:59', 1);
