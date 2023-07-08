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
